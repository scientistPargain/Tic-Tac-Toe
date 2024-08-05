package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView dp;
    int images[]={R.drawable.me1,R.drawable.me2};
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To remove status bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }


    public void Activity2(View view){
        Intent intent = new Intent(this,gameActivity.class);
        startActivity(intent);
    }


    public void imageClick(View view){
        dp = findViewById(R.id.imageView9);
        dp.setImageResource(images[i]);
        i++;
        if(i==2)
            i=0;

    }
}

