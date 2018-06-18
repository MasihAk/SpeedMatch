package com.example.masih.speedmatch;

import android.content.Intent;
import android.os.Binder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button whichoneStartButton;
    private Button speedMatchStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        configureViews();
    }

    private void findViews(){
        whichoneStartButton = (Button) findViewById(R.id.whichone_is_bigger_button);
        speedMatchStartButton = (Button) findViewById(R.id.speed_match_button);
    }

    private void configureViews(){
        whichoneStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,WhichOneIsBiggerActivity.class));
            }
        });

        speedMatchStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SpeedMatchActivity.class));
            }
        });
    }
}
