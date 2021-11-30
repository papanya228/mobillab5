package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoPlayer;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoPlayer = (VideoView) findViewById(R.id.videoView);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
        videoPlayer.setVideoURI(videoUri);
        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopPlayingAudio();
            }
        });
    }
    public void startPlayingAudio(View view){
        if(videoPlayer.isPlaying()){
            videoPlayer.stopPlayback();
            videoPlayer.resume();
        }
        mediaPlayer.start();
    }

    public void stopPlayingAudio(View view){

        stopPlayingAudio();
    }
    private void stopPlayingAudio(){
        mediaPlayer.stop();
        try{
            mediaPlayer.prepare();
            mediaPlayer.seekTo(0);
        }catch (Throwable t){
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void playVideo(View view){
        if(mediaPlayer.isPlaying()){
            stopPlayingAudio();
        }
        videoPlayer.start();
    }

    public void stopVideo(View view){
        videoPlayer.stopPlayback();
        videoPlayer.resume();
    }


}