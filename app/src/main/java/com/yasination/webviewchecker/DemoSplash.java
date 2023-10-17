package com.yasination.webviewchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class DemoSplash extends AppCompatActivity {
    public static  Class<? extends Activity> otherActivityClass = MainActivity.class;

    public static String userWebLINK = "";

    TextView userText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_splash);
        userText = findViewById(R.id.userText);

        userText.setText(userWebLINK);
        // Create a delay to show the splash screen for a specific duration
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start your main activity or another activity
                Intent intent = new Intent(getApplicationContext(), otherActivityClass);
                startActivity(intent);
                finish();
            }
        }, 1000); // Delay in milliseconds (e.g., 3 seconds)








    }
}