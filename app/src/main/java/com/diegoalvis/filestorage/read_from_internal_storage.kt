package com.diegoalvis.filestorage

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.read_from_external_storage.*
import kotlinx.android.synthetic.main.read_from_internal_storage.*
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException

class read_from_internal_storage : AppCompatActivity() {

    val fileName = "testFile.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.read_from_internal_storage)
        val fileContent = readFileInternalStorage(fileName, this)
        file_content_I.text = fileContent
    }

    private fun readFileInternalStorage(fileName: String, context: Context): String {
        var stringToReturn = ""
        try {
            val inputStream = context.openFileInput(fileName)
            if (inputStream != null) {
                stringToReturn = inputStream.bufferedReader().use(BufferedReader::readText)
            }
        } catch (e: FileNotFoundException) {
            Log.e("TAG", "The file was not found : " + e.toString())
        } catch (e: IOException) {
            Log.e("TAG", "Cannot read file: " + e.toString())
        }
        return stringToReturn
    }
}
