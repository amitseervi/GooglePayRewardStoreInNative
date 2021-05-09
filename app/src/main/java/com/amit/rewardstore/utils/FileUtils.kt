package com.amit.rewardstore.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

object FileUtils {

    fun readFileFromAsset(fileName: String, context: Context): String {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(InputStreamReader(context.assets.open(fileName)))
            return reader.readText()
        } finally {
            reader?.close()
        }
    }
}