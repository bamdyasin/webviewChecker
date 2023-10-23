package com.yasination.webviewchecker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.tabs.TabLayout;

public class BuyApp extends AppCompatActivity {
    Toolbar toolbar;
    Button basicPackage, standardPackage, premiumPackage;

    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_app);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
       toolbar.setTitle(R.string.buyClassTitle);


        basicPackage = findViewById(R.id.basicPackage);
        standardPackage = findViewById(R.id.standardPackage);
        premiumPackage = findViewById(R.id.premiumPackage);


        basicPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                copyTextToClipboard("I want to buy Basic Package");
                openWhatsAppProfile();


            }
        });

        standardPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyTextToClipboard("I want to buy Standard Package");
                openWhatsAppProfile();


            }
        });

        premiumPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyTextToClipboard("I want to buy Premium Package");
                openWhatsAppProfile();


            }
        });


    }//============== on create end===================
    public void openWhatsAppProfile() {
        try {
            String phoneNumber = "+8801868966864";
            String url = "https://api.whatsapp.com/send?phone=" + phoneNumber;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);

            // Show a toast message to provide feedback
            Toast.makeText(this, "Connecting to seller ...", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

            try {
                String userName ="YasiiNatiion";
                String url = "https://t.me/"+userName;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);

            }catch (Exception ee){
                Toast.makeText(this, "Telegram not installed", Toast.LENGTH_SHORT).show();

            }
            // Handle exceptions (e.g., WhatsApp not installed)
            Toast.makeText(this, "WhatsApp is not installed on your device.", Toast.LENGTH_SHORT).show();
        }
    }

    public void copyTextToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", text);
        clipboard.setPrimaryClip(clip);
    }
}