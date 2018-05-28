package com.diegoalvis.filestorage

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.write_to_external_storage.*

class write_to_internal_storage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.write_to_internal_storage)
        save.setOnClickListener({
            val fileName = enter_filename.text.toString()
            val content = enter_file_content.text.toString()
            writeFileInternalStorage(content, this, fileName)
        })
    }

    private fun writeFileInternalStorage(content: String, context: Context, fileName: String) {
        try {
            val fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            fos.write(content.toByteArray())
            fos.flush()
            fos.close()
            Toast.makeText(context, "File Written to internal memory", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Error occurred in writing File", Toast.LENGTH_LONG).show()
        }
    }
}

