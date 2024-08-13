package com.pos.data.model

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("result") val result: Boolean,
    @SerializedName("msg") val msg: String,
)