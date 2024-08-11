package com.example.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer;
    private Button btnStart, btnStop, btnReset;
    private Handler handler = new Handler();
    private long startTime = 0;
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTimer = findViewById(R.id.tvTimer);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnReset);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = System.currentTimeMillis();
                handler.post(runnable);
                running = true;
                btnStart.setEnabled(false);
                btnStop.setEnabled(true);
                btnReset.setEnabled(false);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                running = false;
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                btnReset.setEnabled(true);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTimer.setText("00:00:00");
                startTime = 0;
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                btnReset.setEnabled(false);
            }
        });

        // Initially, Stop and Reset buttons should be disabled
        btnStop.setEnabled(false);
        btnReset.setEnabled(false);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            long elapsedMillis = System.currentTimeMillis() - startTime;
            int seconds = (int) (elapsedMillis / 1000);
            int minutes = seconds / 60;
            int hours = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;

            tvTimer.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
            handler.postDelayed(this, 1000);
        }
    };
}
