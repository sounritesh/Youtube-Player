package com.curiositymeetsminds.youtubeplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSingle.setOnClickListener(this)
        btnStandalone.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val intent = when(v.id) {
            R.id.btnSingle -> Intent(this, YoutubeActivity::class.java)
            R.id.btnStandalone -> Intent(this, StandaloneActivity::class.java)
            else -> throw IllegalArgumentException("Unassigned button was clicked")
        }
        startActivity(intent)
    }
}
