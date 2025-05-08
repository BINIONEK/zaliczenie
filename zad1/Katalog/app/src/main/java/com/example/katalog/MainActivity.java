package com.example.katalog;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int favCount = 0;
    TextView favText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        favText = findViewById(R.id.favCounterText);
        Button addButton = findViewById(R.id.addButton);
        Button removeButton = findViewById(R.id.removeButton);

        addButton.setOnClickListener(v -> {
            favCount++;
            updateFavText();
        });

        removeButton.setOnClickListener(v -> {
            if (favCount > 0) favCount--;
            updateFavText();
        });
    }

    private void updateFavText() {
        favText.setText("Liczba w ulubionych: " + favCount);
    }
}

