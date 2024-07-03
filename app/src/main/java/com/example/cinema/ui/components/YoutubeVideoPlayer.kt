package com.example.cinema.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubeVideoPlayer(
    youtubeVideoId:String,
    lifecycleOwner: LifecycleOwner
) {
    AndroidView(factory = {context->
        YouTubePlayerView(context).apply {
            lifecycleOwner.lifecycle.addObserver(this)

            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(youtubeVideoId, 0f)
                }
            })
        }
    })
}

@Preview(showBackground = true)
@Composable
fun PreviewYoutubeVideoPLayer(){
    YoutubeVideoPlayer(youtubeVideoId = "asasasasa", lifecycleOwner = LocalLifecycleOwner.current)
}