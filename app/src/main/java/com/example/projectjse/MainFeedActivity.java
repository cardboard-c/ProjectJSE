package com.example.projectjse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainFeedActivity extends AppCompatActivity {
    private Button setting;
    private Button profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_feed);
        setting = (Button) findViewById(R.id.settingsButton);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }

        });
        profile = (Button) findViewById(R.id.profileButton);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

    }
    public void openActivity(){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void openActivity2(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}