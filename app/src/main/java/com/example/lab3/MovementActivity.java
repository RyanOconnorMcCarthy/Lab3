package com.example.lab3;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;

public class MovementActivity extends AppCompatActivity {
    TextView movementDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement);

        movementDetails = findViewById(R.id.movementDetails);

        long time = getIntent().getLongExtra("time", 0);

        movementDetails.setText("Movement detected at: " + new Date(time));
    }

}
