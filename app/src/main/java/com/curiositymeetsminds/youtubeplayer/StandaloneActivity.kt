package com.curiositymeetsminds.youtubeplayer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_standalone.*
import java.lang.IllegalArgumentException

class StandaloneActivity: AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

        btnPlaySingle.setOnClickListener(this)
        btnPlaylist.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val intent = when(v.id) {
            R.id.btnPlaySingle -> YouTubeStandalonePlayer.createVideoIntent(this,getString(R.string.GOOGLE_API_KEY), VIDEO_ID)
            R.id.btnPlaylist -> YouTubeStandalonePlayer.createVideoIntent(this, getString(R.string.GOOGLE_API_KEY), PLAYLIST_ID)
            else -> throw IllegalArgumentException("Unassigned button was clicked")
        }
        startActivity(intent)
    }
}