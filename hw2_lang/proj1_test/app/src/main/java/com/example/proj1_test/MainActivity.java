package com.example.proj1_test;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView flagImage = findViewById(R.id.flagImage);

        TextView helloText = findViewById(R.id.helloText);

        String language = Locale.getDefault().getLanguage();

        String flagUrl;
        switch (language) {
            case "uk":
                flagUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Flag_of_Ukraine.svg/512px-Flag_of_Ukraine.svg.png";
                break;
            case "en":
                flagUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/a/a4/Flag_of_the_United_States.svg/512px-Flag_of_the_United_States.svg.png";
                break;
            case "fr":
                flagUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/c/c3/Flag_of_France.svg/512px-Flag_of_France.svg.png";
                break;
            default:
                flagUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Europe.svg/512px-Flag_of_Europe.svg.png";
        }

        helloText.setText("Language: " + language + "\nFlag URL: " + flagUrl);

        Glide.with(this).load(flagUrl).into(flagImage);
    }
}