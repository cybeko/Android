package com.example.proj3_game;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button[] buttons = new Button[9];
    boolean isXturn = true;
    int[][] winCombinations = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    @SuppressLint("SetTextI18n")
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

        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v -> {
            resetGame();
            btnStart.setText("Restart Game");
        });
        buttons[0] = findViewById(R.id.b1);
        buttons[1] = findViewById(R.id.b2);
        buttons[2] = findViewById(R.id.b3);
        buttons[3] = findViewById(R.id.b4);
        buttons[4] = findViewById(R.id.b5);
        buttons[5] = findViewById(R.id.b6);
        buttons[6] = findViewById(R.id.b7);
        buttons[7] = findViewById(R.id.b8);
        buttons[8] = findViewById(R.id.b9);

        for (Button b : buttons) {
            b.setEnabled(false);
        }
        for (Button b : buttons) {
            b.setOnClickListener(this::onCellClicked);
        }
        btnStart.setOnClickListener(v -> resetGame());
    }

    private void onCellClicked(View view) {
        Button b = (Button) view;

        if (!b.getText().toString().isEmpty()) return;

        if (isXturn) {
            b.setText("X");
        } else {
            b.setText("0");
        }

        String winner = checkWin();
        if (winner != null) {
            Toast.makeText(this, winner + " won!", Toast.LENGTH_LONG).show();
            disableButtons();
            return;
        }

        if (isTie()) {
            Toast.makeText(this, "It's a tie!", Toast.LENGTH_LONG).show();
            disableButtons();
            return;
        }
        isXturn = !isXturn;
    }
    private String checkWin() {
        for (int[] combo : winCombinations) {
            String s1 = buttons[combo[0]].getText().toString();
            String s2 = buttons[combo[1]].getText().toString();
            String s3 = buttons[combo[2]].getText().toString();

            if (!s1.isEmpty() && s1.equals(s2) && s1.equals(s3)) {
                return s1;
            }
        }
        return null;
    }
    private boolean isTie() {
        for (Button b : buttons) {
            if (b.getText().toString().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    private void disableButtons() {
        for (Button b : buttons) {
            b.setEnabled(false);
        }
    }
    private void resetGame() {
        for (Button b : buttons) {
            b.setText("");
            b.setEnabled(true);
        }
        isXturn = true;
    }
}
