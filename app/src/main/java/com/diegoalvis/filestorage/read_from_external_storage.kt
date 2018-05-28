package com.diegoalvis.filestorage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import kotlinx.android.synthetic.main.read_from_external_storage.*
import java.io.*


class read_from_external_storage : AppCompatActivity() {
    val fileName = "ExtFile.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.read_from_external_storage)
        val fileContent = readFileExternalStorage(fileName)
        file_content_E.text = fileContent
    }

    private fun readFileExternalStorage(fileName: String): String {
        var result = ""
        try {
            if (CommonUtil.isSdReadable()) {
                val sdcard = Environment.getExternalStorageDirectory()
                val file = File(sdcard, fileName)
                val br = BufferedReader(FileReader(file))
                result = br.use(BufferedReader::readText)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }
}