package com.raju.javabaseproject.utlities.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Base64;

import com.raju.javabaseproject.R;
import com.raju.javabaseproject.utlities.Constants;
import com.raju.javabaseproject.utlities.Util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import timber.log.Timber;

public class ImageUtil {

    public static File createImageFile(Context context) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (storageDir != null) {
            if (!storageDir.exists()) {
                storageDir.mkdir();
            }
        } else {
            storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        }
        File image = File.createTempFile(imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        );

        return image;
    }

    public static Uri getFileUri(Context context, File file) {
        Uri uri = FileProvider.getUriForFile(context, context.getResources().getString(R.string.fileprovider), file);
        if(uri == null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                uri = Uri.fromFile(file);
            } else {
                uri = FileProvider.getUriForFile(context, context.getResources().getString(R.string.fileprovider), file);
            }
        }
        return uri;
    }

    public static Bitmap decodeSampledBitmap(Context context, Uri imageUri) {
        int reqHeight = (int) Util.getScreenHeight() / 2;
        int reqWidth = (int) Util.getScreenWidth() / 2;

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeStream(context.getContentResolver().openInputStream(imageUri), null, options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Timber.e(e.getMessage());
        }

        // preserve ExifInterface
        Matrix matrix = new Matrix();
        try {
            ExifInterface exif = new ExifInterface(RealPathUtil.getRealPathFromUri(context, imageUri));
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    matrix.postRotate(90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    matrix.postRotate(180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    matrix.postRotate(270);
                    break;
                default:
                    break;
            }

        } catch (IOException e) {
            Timber.e(e.getMessage());
            e.printStackTrace();
        }
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        try {
            Bitmap bmp = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(imageUri), null, options);
            Bitmap newBitmap = Bitmap.createBitmap(bmp, 0, 0, options.outWidth, options.outHeight, matrix, false);
            return newBitmap;
        } catch (FileNotFoundException e) {
            Timber.e(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String getBase64Image(Bitmap bitmap) {
        if (bitmap == null) return null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = null;
        String base64 = "";
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            b = baos.toByteArray();
            System.gc();
            base64 = Base64.encodeToString(b, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            Timber.e(e.getMessage());
        } catch (OutOfMemoryError e) {
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            b = baos.toByteArray();
            base64 = Base64.encodeToString(b, Base64.DEFAULT);
            Timber.e(e.getMessage());
        }
        bitmap.recycle();
        return base64;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 2;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        Timber.d("inSampleSize: " + inSampleSize);
        return inSampleSize;
    }
}
