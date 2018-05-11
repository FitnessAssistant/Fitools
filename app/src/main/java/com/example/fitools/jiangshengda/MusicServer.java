package com.example.fitools.jiangshengda;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;

import com.example.fitools.R;

import static com.example.fitools.jiangshengda.RunningActivity.con_music;

public class MusicServer extends Service {
    private String data = null;
    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        if (mediaPlayer == null) {
            if (con_music == true){
                // R.raw.mmp是资源文件，MP3格式的
                mediaPlayer = MediaPlayer.create(this,R.raw.slow_music);
                //设置循环
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            }else {
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(this,R.raw.fast_music);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            }


        }
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId){
        data = intent.getStringExtra("avgspeed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mediaPlayer.stop();
    }
}
