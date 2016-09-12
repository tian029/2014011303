package com.example.alana.exercise28;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="myTag";
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mediaPlayer=new MediaPlayer();

        final TextView textView=(TextView)findViewById(R.id.textView);
        final Button buttonStart=(Button)findViewById(R.id.start);
        final Button buttonPause=(Button)findViewById(R.id.pause);
        final Button buttonStop=(Button)findViewById(R.id.stop);
        final Button buttonLoop=(Button)findViewById(R.id.loop);

        buttonPause.setEnabled(false);
        buttonStop.setEnabled(false);
        buttonLoop.setEnabled(false);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Log.v(TAG,"start");
                    mediaPlayer.reset();
                    AssetManager assetManager=getAssets();
                    AssetFileDescriptor assetFileDescriptor=assetManager.openFd("abc.mp3");
                    mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                            assetFileDescriptor.getStartOffset(),assetFileDescriptor.getLength());
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    buttonLoop.setEnabled(true);
                    buttonPause.setEnabled(true);
                    buttonStop.setEnabled(true);
                }catch (IllegalArgumentException e){
                    e.printStackTrace();
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    buttonPause.setText("Play");
                    mediaPlayer.pause();
                }
                else{
                    buttonPause.setText("Pause");
                    mediaPlayer.start();
                }
            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    buttonPause.setEnabled(false);
                    buttonStop.setEnabled(false);
                    buttonLoop.setEnabled(false);
                }
            }
        });
        buttonLoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"Looping");
                boolean loop=mediaPlayer.isLooping();
                mediaPlayer.setLooping(!loop);
                if(!loop)
                    textView.setText("循环播放");
                else
                    textView.setText("一次播放");

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
