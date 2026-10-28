package com.example.lab3;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public Button start;
    public Button stop;
    public TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        status = findViewById(R.id.Status);
    }


    public void monitor(View view) {
        status.setText("Currently: Monitor");
        Intent intent = new Intent(this, Monitor.class);
        startService(intent);

    }

    public void stopMonitor(View view) {
        status.setText("Currently: Not Monitor");
        Intent intent = new Intent(this, Monitor.class);
        stopService(intent);
    }
}