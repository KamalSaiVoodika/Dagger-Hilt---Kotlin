package com.pos.ui.contacts.data

import com.google.gson.annotations.SerializedName


class Names(
    @SerializedName("name")
    val name: String,
    @SerializedName("number")
    val number: String,
)