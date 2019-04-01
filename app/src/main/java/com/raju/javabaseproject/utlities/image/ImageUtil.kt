package com.raju.javabaseproject.utlities.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.content.FileProvider
import android.text.TextUtils
import android.util.Base64

import com.raju.javabaseproject.R
import com.raju.javabaseproject.utlities.Constants
import com.raju.javabaseproject.utlities.Util

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

import timber.log.Timber

object ImageUtil {

    @Throws(IOException::class)
    fun createImageFile(context: Context): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        var storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (storageDir != null) {
            if (!storageDir.exists()) {
                storageDir.mkdir()
            }
        } else {
            storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        }
        val image = File.createTempFile(imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        )

        return image
    }

    fun getFileUri(context: Context, file: File): Uri? {
        var uri: Uri? = FileProvider.getUriForFile(context, context.resources.getString(R.string.fileprovider), file)
        if (uri == null) {
            uri = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                Uri.fromFile(file)
            } else {
                FileProvider.getUriForFile(context, context.resources.getString(R.string.fileprovider), file)
            }
        }
        return uri
    }

    fun decodeSampledBitmap(context: Context, imageUri: Uri): Bitmap? {
        val reqHeight = Util.screenHeight.toInt() / 2
        val reqWidth = Util.screenWidth.toInt() / 2

        // First decode with inJustDecodeBounds=true to check dimensions
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            BitmapFactory.decodeStream(context.contentResolver.openInputStream(imageUri), null, options)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            Timber.e(e.message)
        }

        // preserve ExifInterface
        val matrix = Matrix()
        try {
            val exif = ExifInterface(RealPathUtil.getRealPathFromUri(context, imageUri))
            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)

            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90f)
                ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180f)
                ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270f)
                else -> {
                }
            }

        } catch (e: IOException) {
            Timber.e(e.message)
            e.printStackTrace()
        }

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false
        try {
            val bmp = BitmapFactory.decodeStream(context.contentResolver.openInputStream(imageUri), null, options)
            val newBitmap = Bitmap.createBitmap(bmp!!, 0, 0, options.outWidth, options.outHeight, matrix, false)
            return newBitmap
        } catch (e: FileNotFoundException) {
            Timber.e(e.message)
            e.printStackTrace()
        }

        return null
    }

    fun getBase64Image(bitmap: Bitmap?): String? {
        if (bitmap == null) return null

        var baos = ByteArrayOutputStream()
        var b: ByteArray? = null
        var base64 = ""
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos)
            b = baos.toByteArray()
            System.gc()
            base64 = Base64.encodeToString(b, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
            Timber.e(e.message)
        } catch (e: OutOfMemoryError) {
            baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
            b = baos.toByteArray()
            base64 = Base64.encodeToString(b, Base64.DEFAULT)
            Timber.e(e.message)
        }

        bitmap.recycle()
        return base64
    }

    fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 2

        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }

        Timber.d("inSampleSize: $inSampleSize")
        return inSampleSize
    }
}
