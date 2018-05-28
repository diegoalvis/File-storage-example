package com.diegoalvis.filestorage

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        write_file_to_internal_storage.setOnClickListener({
            startActivity(Intent(this, write_to_internal_storage::class.java))
        })

        write_file_to_external_storage.setOnClickListener({
            startActivity(Intent(this, write_to_external_storage::class.java))
        })

        read_file_from_internal_storage.setOnClickListener({
            startActivity(Intent(this, read_from_internal_storage::class.java))
        })

        read_file_from_external_storage.setOnClickListener({
            startActivity(Intent(this, read_from_external_storage::class.java))
        })
    }
}


