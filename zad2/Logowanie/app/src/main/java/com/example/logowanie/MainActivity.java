package com.example.logowanie;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText emailInput, passwordInput;
    TextView messageText;
    Button loginButton;

    final String correctEmail = "admin@example.com";
    final String correctPassword = "secret123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        messageText = findViewById(R.id.messageText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString();

            if (!email.contains("@")) {
                messageText.setText("Nieprawidłowy format loginu (e-mail).");
            } else if (!email.equals(correctEmail)) {
                messageText.setText("Brak użytkownika o podanym loginie.");
            } else if (!password.equals(correctPassword)) {
                messageText.setText("Błędne hasło.");
            } else {
                messageText.setText("Zalogowano pomyślnie!");
            }
        });
    }
}
