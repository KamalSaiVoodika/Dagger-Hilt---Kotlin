package com.pos.utils

object Config {
    const val PrefName = "pos_preference"

    const val PASSWORD_PATTERN = "[\\D\\d.]{6,16}"
    const val PASSWORD_LENGTH = 8

    const val CODE_REQUEST_PERMISSION_SETTING = 12
    var CAMERA_PATH: String? = null

    const val ARG_ADDRESS = "ARG_ADDRESS"
}

