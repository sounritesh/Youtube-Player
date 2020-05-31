package com.curiositymeetsminds.youtubeplayer

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    private val tag = "YoutubeActivity"
    private val playerView by lazy {YouTubePlayerView(this)}
    private val dialogRequestCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_youtube)

        val layout = layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)

        playerView.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youtubePlayer: YouTubePlayer?,
        wasRestored: Boolean
    ) {
//        TODO("Not yet implemented")
        Log.d(tag, "onInitializationSuccess: provider is ${provider?.javaClass}" )
        Log.d(tag, "onInitializationSuccess: youtubePlayer is ${youtubePlayer?.javaClass}" )
        Toast.makeText(this, "The player initialized successfully", Toast.LENGTH_SHORT).show()

        youtubePlayer?.setPlaybackEventListener(playbackEventListener)
        youtubePlayer?.setPlayerStateChangeListener(playerStateChangeListener)

        if (!wasRestored) {
            youtubePlayer?.loadVideo(VIDEO_ID)
        } else {
            youtubePlayer?.play()
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        youTubeInitializationResult: YouTubeInitializationResult?
    ) {
        if (youTubeInitializationResult?.isUserRecoverableError == true) {
            youTubeInitializationResult.getErrorDialog(this, dialogRequestCode).show()
        } else {
            val errorMessage = "There was an error initializing the YoutubePlayer: $youTubeInitializationResult"
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private val playbackEventListener = object: YouTubePlayer.PlaybackEventListener {
        override fun onSeekTo(p0: Int) {
        }

        override fun onBuffering(p0: Boolean) {
        }

        override fun onPlaying() {
            Toast.makeText(this@YoutubeActivity, "The video is playing okay.", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
        }

        override fun onPaused() {
            Toast.makeText(this@YoutubeActivity, "The video is paused.", Toast.LENGTH_SHORT).show()
        }
    }

    private val playerStateChangeListener = object: YouTubePlayer.PlayerStateChangeListener {
        override fun onAdStarted() {
            Toast.makeText(this@YoutubeActivity, "Click ad to support creator.", Toast.LENGTH_SHORT).show()
        }

        override fun onLoading() {
        }

        override fun onVideoStarted() {
            Toast.makeText(this@YoutubeActivity, "The video has started.", Toast.LENGTH_SHORT).show()
        }

        override fun onLoaded(p0: String?) {
        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity, "Congrats, video ended.", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(tag, "onActivityResult called with response $resultCode for request $requestCode.")

        if (requestCode == dialogRequestCode) {
            Log.d(tag, intent?.toString())
            Log.d(tag, intent?.extras.toString())

            playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
        }
    }
}
