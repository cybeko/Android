package com.example.proj1_test;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStream;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    private String[] songParts;
    private int currentId = 0;
    private Toast currentToast;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.bleed_me_an_ocean);
        mediaPlayer.setLooping(true);

        try {
            InputStream is = getResources().openRawResource(R.raw.bleed_me_an_ocean_lyrics);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            is.close();

            String[] allLines = new String(bytes).split("\\r?\\n");

            ArrayList<String> filtered = new ArrayList<>();
            for (String line : allLines) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("[")) {
                    filtered.add(line);
                }
            }
            songParts = filtered.toArray(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
            songParts = new String[]{"Error loading lyrics"};
        }
    }
    public void f(View view) {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        if (currentToast != null) currentToast.cancel();

        int bgColor = Color.rgb((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));

        currentToast = Toasty.custom(
                this,
                songParts[currentId],
                null,
                bgColor,
                Color.WHITE,
                Toast.LENGTH_SHORT,
                false,
                true
        );
        currentToast.show();
        currentId = (currentId + 1) % songParts.length;
    }
}
