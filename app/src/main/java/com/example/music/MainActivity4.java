package com.example.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.IOException;

public class MainActivity4 extends AppCompatActivity {
    private ListView listView;
    private Button button;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ANHXA();

      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String url="https://mp3.zing.vn";
              MediaPlayer mediaPlayer=new MediaPlayer();
              //show progress
              progressBar.setVisibility(View.VISIBLE);
              mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
               try {
                   mediaPlayer.setDataSource(url);
                   mediaPlayer.prepareAsync();
                   mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                       @Override
                       public void onPrepared(MediaPlayer mp) {
                           progressBar.setVisibility(View.GONE);
                           mp.start();
                       }
                   });
               } catch (IOException e) {
                   e.printStackTrace();
               }
          }
      });
    }

    private void ANHXA() {
        listView=findViewById(R.id.listview);
        button=findViewById(R.id.btn);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }
}