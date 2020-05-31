package com.curiositymeetsminds.youtubeplayer

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_standalone.*
import java.lang.Exception
import java.lang.IllegalArgumentException

class StandaloneActivity: AppCompatActivity(), View.OnClickListener {
    private val tag = "StandaloneActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

        btnPlaySingle.setOnClickListener(this)
        btnPlaylist.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val intent = when(v.id) {
            R.id.btnPlaySingle -> YouTubeStandalonePlayer.createVideoIntent(this,getString(R.string.GOOGLE_API_KEY), VIDEO_ID, 0, true, true)
            R.id.btnPlaylist -> YouTubeStandalonePlayer.createPlaylistIntent(this, getString(R.string.GOOGLE_API_KEY), PLAYLIST_ID, 0, 0, true, true)
            else -> throw IllegalArgumentException("Unassigned button was clicked")
        }
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Log.e(tag, "Error occurred: ${e.message}")
            Toast.makeText(this, "Error Occurred: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

}