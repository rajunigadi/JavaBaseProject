package com.raju.javabaseproject.utlities


object Constants {
    val AUTH_TYPE = "password"
    val CLIENT_ID = "8d3a76625479b2ca888bd69d51bc15bf2c56dc7e"
    val CLIENT_SECRET = "7a130c5ddc57d621e90d5a23b27eea94f98d8496"

    val ANIM_DURATION_TOOLBAR = 300
    val AVATAR_ = 300
    val CAMERA = 1000
    val GALLERY = 2000
    val TERMS = 3000
    val PROFILE = 4000
    val REQUEST_PERMISSION_STORAGE = 1005

    object PrefsKeys {
        val KEY_FCM_TOKEN = "key_fcm_token"
        val KEY_AUTH_CODE = "key_auth_code"
        val KEY_REFRESH_TOKEN = "key_refresh_token"
        val KEY_AUTH_EXPIRES = "key_auth_expires"
        val KEY_VEHICLE_HEADER = "key_vehicle_header"

        val KEY_TERMS = "key_terms"
    }

    object Bundle {
        val KEY_HOME = "key_home"
    }

    object AvatarSize {
        val SMALL = 60
        val NORMAL = 120
        val MEDIUM = 240
        val LARGE = 360
        val EXTRA_LARGE = 512
    }
}
