package com.curiositymeetsminds.youtubeplayer


import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val VIDEO_ID = "S20NcDLF_t4"
const val PLAYLIST_ID = "PLMC9KNkIncKtPzgY-5rmhvj7fax8fdxoj"

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_youtube)

        val layout = layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
//        TODO("Not yet implemented")
        
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        youTubeInitializationResult: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 0

        if (youTubeInitializationResult?.isUserRecoverableError() == true) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE)
        } else {
            val errorMessage = "There was an error initializing the YoutubePlayer: $youTubeInitializationResult"

            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG)
        }
    }
}
