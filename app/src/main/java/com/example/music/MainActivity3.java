package com.example.music;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    private TextView tenbai,time1,time2;
    private SeekBar tua;
    private ImageView trolai, dung, tiep,chay,list,avt;

    ArrayList<Song> arrayList;
    int  position = 0;
    MediaPlayer mediaPlayer;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        anhxa();
        Addsong̣̣̣̣̣̣̣̣̣̣̣̣̣();
        khoitaoMedia();
        SetTimeTotal();
        UpdateTimeSong();
        animation= AnimationUtils.loadAnimation(this,R.anim.disc_rotate);

        trolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                if(position < 0){
                    position = arrayList.size() - 1;
                }
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                khoitaoMedia();
                mediaPlayer.start();
               chay.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
                SetTimeTotal();
            }
        });

        tiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position++;
                if(position > arrayList.size() - 1){
                    position = 0 ;
                }
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                khoitaoMedia(); 
                mediaPlayer.start();
                tiep.setImageResource(R.drawable.ic_baseline_skip_next_24);
                SetTimeTotal();
            }
        });

        dung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                chay.setImageResource(R.drawable.ic_baseline_stop_24);
                khoitaoMedia();
                SetTimeTotal();
                UpdateTimeSong();
                avt.startAnimation(animation);
            }
        });
       chay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    chay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                }else {
                    mediaPlayer.start();
                    chay.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
                }
                SetTimeTotal();
                UpdateTimeSong();
                avt.startAnimation(animation);
            }
        });
        tua.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(tua.getProgress());
            }
        });
      list.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
               Intent it=new Intent(MainActivity3.this,MainActivity4.class);
//                Bundle bundle=new Bundle();
//                Integer.parseInt(tenbai.getText().toString());
//                bundle.putInt("tbh",1);
                startActivity(it);
            }
        });
    }
    private void UpdateTimeSong() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDanggio  = new SimpleDateFormat("mm:ss");
                time1.setText(dinhDanggio.format(mediaPlayer.getCurrentPosition()));
                tua.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 500);
            }
        },100);
    }
    private void SetTimeTotal(){
        SimpleDateFormat dinhdanggio = new SimpleDateFormat("mm:ss");
        time2.setText(dinhdanggio.format(mediaPlayer.getDuration()));
        tua.setMax(mediaPlayer.getDuration());
    }

    private void khoitaoMedia() {
        mediaPlayer = MediaPlayer.create(MainActivity3.this, arrayList.get(position).getFile());
        tenbai.setText(arrayList.get(position).getTiler());
    }
    private void Addsong̣̣̣̣̣̣̣̣̣̣̣̣̣() {
        arrayList = new ArrayList<>();
        arrayList.add(new Song("Ai chung tinh duoc mai", R.raw.aichungtinhduocmai));
        arrayList.add(new Song("Co anh o day roi", R.raw.coanhodayroi));
        arrayList.add(new Song("De vuong", R.raw.devuong));
        arrayList.add(new Song("Noi em nghe mai mai la bao lau", R.raw.noiemnghemaimailabaolau));
        arrayList.add(new Song("The nhe anh", R.raw.thenhanh));
        arrayList.add(new Song("ve day em lo", R.raw.vedayemlo));
    }

    private void anhxa() {
        tenbai = findViewById(R.id.tbh);
        tua = findViewById(R.id.seekBar);
        chay = findViewById(R.id.chay);
        time1 = findViewById(R.id.first);
       time2 = findViewById(R.id.late);
       trolai = findViewById(R.id.trolai);
        dung = findViewById(R.id.dung);
        tiep = findViewById(R.id.tiep);
        list=findViewById(R.id.imageView3);
        avt=findViewById(R.id.profile_image);
    }
}