package com.example.lotto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText[] userInputs;
    TextView matchCountText, drawCountText;
    LinearLayout drawnNumbersLayout;
    int drawCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInputs = new EditText[]{
                findViewById(R.id.num1), findViewById(R.id.num2),
                findViewById(R.id.num3), findViewById(R.id.num4),
                findViewById(R.id.num5), findViewById(R.id.num6)
        };

        matchCountText = findViewById(R.id.matchCount);
        drawCountText = findViewById(R.id.drawCount);
        drawnNumbersLayout = findViewById(R.id.drawnNumbersLayout);

        Button drawButton = findViewById(R.id.drawButton);
        Button resetButton = findViewById(R.id.resetButton);

        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawNumbers();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    void drawNumbers() {
        List<Integer> userNums = getUserNumbers();
        if (userNums == null) return;

        Set<Integer> drawn = new HashSet<>();
        Random rand = new Random();
        while (drawn.size() < 6) {
            drawn.add(rand.nextInt(49) + 1);
        }

        showDrawnNumbers(new ArrayList<>(drawn));

        int matches = 0;
        for (int num : userNums) {
            if (drawn.contains(num)) matches++;
        }

        matchCountText.setText("Liczba trafień: " + matches);
        drawCount++;
        drawCountText.setText("Liczba losowań: " + drawCount);
    }

    List<Integer> getUserNumbers() {
        Set<Integer> numbers = new HashSet<>();
        for (EditText input : userInputs) {
            String text = input.getText().toString().trim();
            if (text.isEmpty()) {
                Toast.makeText(this, "Wprowadź wszystkie liczby", Toast.LENGTH_SHORT).show();
                return null;
            }
            int num = Integer.parseInt(text);
            if (num < 1 || num > 49) {
                Toast.makeText(this, "Liczby muszą być w zakresie 1–49", Toast.LENGTH_SHORT).show();
                return null;
            }
            if (!numbers.add(num)) {
                Toast.makeText(this, "Liczby muszą być unikalne", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        return new ArrayList<>(numbers);
    }

    void showDrawnNumbers(List<Integer> numbers) {
        drawnNumbersLayout.removeAllViews();
        for (int num : numbers) {
            TextView tv = new TextView(this);
            tv.setText(String.valueOf(num));
            tv.setTextSize(18);
            tv.setPadding(10, 10, 10, 10);
            drawnNumbersLayout.addView(tv);
        }
    }

    void resetGame() {
        for (EditText input : userInputs) {
            input.setText("");
        }
        drawnNumbersLayout.removeAllViews();
        matchCountText.setText("Liczba trafień: 0");
        drawCount = 0;
        drawCountText.setText("Liczba losowań: 0");
    }
}
