package com.diegoalvis.filestorage

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.write_to_external_storage.*
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class write_to_external_storage : AppCompatActivity() {

    val PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.write_to_external_storage)
        save.setOnClickListener({
            val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                proceedToWriteFile()
            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        })
    }

    private fun proceedToWriteFile() {
        val fileName = enter_filename.text.toString()
        val content = enter_file_content.text.toString()
        writeFileExternalStorage(content, this, fileName)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    proceedToWriteFile()
                } else {
                    // permission denied
                }
                return
            }
        }
    }

    private fun writeFileExternalStorage(strWrite: String, context: Context, fileName: String) {
        try {
            if (CommonUtil.isSdReadable()) {
                val fullPath = Environment.getExternalStorageDirectory().absolutePath
                val myFile = File(fullPath + File.separator + "/" + fileName)
                val fOut = FileOutputStream(myFile)
                val myOutWriter = OutputStreamWriter(fOut)
                myOutWriter.append(strWrite)
                myOutWriter.close()
                fOut.close()
                Toast.makeText(context, "File Written to external memory", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Error occured in writing File", Toast.LENGTH_LONG).show()
        }
    }
}