package com.example.fitools.jiangshengda;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;

import com.example.fitools.R;

import java.io.File;


public class MusicServer extends Service {
    private String data = null;
    private MediaPlayer mediaPlayer;
//    private ArrayList<Music> list;
    private String path = "";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
//        list = new ArrayList<Music>();
//        //读取SD卡
//        if (Environment.getExternalStorageState().equals(
//                Environment.MEDIA_MOUNTED)) {
//            File file = Environment.getExternalStorageDirectory();
//            path = file.getAbsolutePath();
//        }
//        list.add(new Music("男人歌", path + "/nanrenge.mp3"));
//        list.add(new Music("夜色", path + "/yese.mp3"));
//        list.add(new Music("漂洋过海来看你", path + "/piaoyang.mp3"));
//        list.add(new Music("兄弟无数", path + "/xiongdiwushu.mp3"));
//        //播放完成后自定播放下一曲
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId){
        //data = intent.getStringExtra("avgspeed");
        int m = intent.getIntExtra("pause",0);
        int con_music = intent.getIntExtra("change_music",0);
        if (mediaPlayer == null) {
            if (con_music == 0){
                // R.raw.mmp是资源文件，MP3格式的
                mediaPlayer = MediaPlayer.create(this,R.raw.slow_music);
                //设置循环
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
                if (m == 1){
                    mediaPlayer.pause();
                }
            }else {
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(this,R.raw.fast_music);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
                if (m == 1){
                    mediaPlayer.pause();
                }
            }


        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mediaPlayer.stop();
    }
}
