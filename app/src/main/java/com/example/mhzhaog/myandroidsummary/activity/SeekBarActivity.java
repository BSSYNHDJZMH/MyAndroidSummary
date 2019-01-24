package com.example.mhzhaog.myandroidsummary.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import com.example.mhzhaog.myandroidsummary.R;

import java.util.Timer;
import java.util.TimerTask;

public class SeekBarActivity extends AppCompatActivity {

    Timer timer;
    private int recLen = 0;
    private TimerTask task;
    private SeekBar seekBar;
    private Button buttonPlay;
    private Button buttonPause;
    private ProgressBar progressBar;
    private int saveTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

        initView();
    }

    private void initView() {
        seekBar = findViewById(R.id.seekBar);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonPause = findViewById(R.id.buttonPause);
        progressBar = findViewById(R.id.progressBar);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countTime();
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != timer){
                    timer.cancel();
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(null != timer){
                    timer.cancel();
                }
            }

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        recLen = seekBar.getProgress();
                        countTime();
                    }
                };
                handler.postDelayed(runnable,2000);


            }
        });
    }

    private void countTime() {
        seekBar.setMax(60);
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recLen++;
                        seekBar.setProgress(recLen);
                        if (recLen == 60) {
                            timer.cancel();
                            recLen = 0;
                        }
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(task, 0, 1000);       // timeTask
    }
}
