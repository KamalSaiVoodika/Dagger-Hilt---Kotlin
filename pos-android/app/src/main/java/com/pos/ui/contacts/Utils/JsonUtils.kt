package com.pos.ui.contacts.Utils

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.pos.ui.contacts.data.Names
import java.io.IOException

object JsonUtils {

    private const val SOURCE_FILE_NAME = "sample_data.json"

    fun getItems(context: Context): List<Names> {

        val jsonString: String
        try {
            jsonString =
                context.assets.open(SOURCE_FILE_NAME).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        return GsonBuilder().create()
            .fromJson(jsonString, object : TypeToken<List<Names>>() {}.type)
    }
}