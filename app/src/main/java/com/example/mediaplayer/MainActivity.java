package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="mytag";
    private MediaPlayer mediaPlayer;
    private Handler handler;
    Random r=new Random();
    String list[]=new String[]{"林俊杰 - 交换余生.mp3","林俊杰 - 幸存者.mp3","林俊杰 - 暂时的记号.mp3",
    "林俊杰 - 最好是.mp3","林俊杰 - 最向往的地方.mp3"};
    int current=0;

    public static SeekBar seek=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=new MediaPlayer();
        final TextView name=(TextView)findViewById(R.id.name);
        final Button play=(Button) findViewById(R.id.play);
        final Button pause=(Button) findViewById(R.id.pause);
        final Button last=(Button) findViewById(R.id.last);
        final Button next=(Button) findViewById(R.id.next);
        final Button suiji=(Button) findViewById(R.id.suiji);

        pause.setEnabled(false);
        last.setEnabled(false);
        next.setEnabled(false);
        suiji.setEnabled(false);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mediaPlayer.reset();
                    AssetManager assetManager=getAssets();
                    AssetFileDescriptor assetFileDescriptor=assetManager.openFd("林俊杰 - 交换余生.mp3");
                    mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                            assetFileDescriptor.getStartOffset(),assetFileDescriptor.getLength());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    pause.setEnabled(true);
                    last.setEnabled(true);
                    next.setEnabled(true);
                    suiji.setEnabled(true);
                    name.setText("林俊杰 - 交换余生");
                }catch(IllegalArgumentException e){
                    e.printStackTrace();
                }
                catch(IllegalStateException e){
                    e.printStackTrace();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
        pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mediaPlayer.isPlaying()){
                    pause.setText("播放");
                    mediaPlayer.pause();
                }
                else{
                    pause.setText("暂停");
                    mediaPlayer.start();
                }
            }
        });
            last.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current--;
                    if (current <= -1) {
                        current = 4;
                    }
                    try {
                        mediaPlayer.reset();
                        AssetManager assetManager = getAssets();
                        AssetFileDescriptor assetFileDescriptor = assetManager.openFd(list[current]);
                        mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                                assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        name.setText(list[current]);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current++;
                    if (current >= 5) {
                        current = 0;
                    }
                    try {
                        mediaPlayer.reset();
                        AssetManager assetManager = getAssets();
                        AssetFileDescriptor assetFileDescriptor = assetManager.openFd(list[current]);
                        mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                                assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        name.setText(list[current]);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            suiji.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int a=r.nextInt(4);
                    try {
                        mediaPlayer.reset();
                        AssetManager assetManager = getAssets();
                        AssetFileDescriptor assetFileDescriptor = assetManager.openFd(list[a]);
                        mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                                assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        name.setText(list[a]);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


    }
}