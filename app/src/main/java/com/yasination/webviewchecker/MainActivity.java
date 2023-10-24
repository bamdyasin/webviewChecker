package com.yasination.webviewchecker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    NavigationView navigationView;
    EditText edEnterLink;
    Button btnDemo;
     AdView mAdView;
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        mAdView = findViewById(R.id.adView);
        AdRequest bannerAdRequest = new AdRequest.Builder().build();
        mAdView.loadAd(bannerAdRequest);


        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,"ca-app-pub-6113259884508103/1304077493", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });


        drawerLayout = findViewById(R.id.drawerLayout);
        materialToolbar = findViewById(R.id.materialToolbar);
        navigationView = findViewById(R.id.navigationView);
        edEnterLink = findViewById(R.id.edEnterLink);
        btnDemo = findViewById(R.id.btnDemo);


        //================================drawer bridge create================================
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,materialToolbar,R.string.drawer_open ,R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);


        btnDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goWebsite();
            }
        });


        //================================Right Side navigationView ================================
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()== R.id.home){
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);

                }
                else if (item.getItemId()== R.id.buyApp){

                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(MainActivity.this);
                    } else {
                        Toast.makeText(MainActivity.this, "Poor Network", Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(MainActivity.this,BuyApp.class);
                    startActivity(intent);

                  //  openWhatsAppUser();
                    drawerLayout.closeDrawer(GravityCompat.START);

                }
                else if (item.getItemId()== R.id.rateUsButton){
                    openGooglePlayForRating();
                    drawerLayout.closeDrawer(GravityCompat.START);

                }
                else if (item.getItemId()== R.id.shareAppButton){
                    shareApp();
                    drawerLayout.closeDrawer(GravityCompat.START);

                }
                return true;
            }
        });   //================= ===Right Side navigationView END ====== =====================


    }//================ setContentView END ====================
    private void goWebsite(){

        String validURL = "";

        String userLink = edEnterLink.getText().toString().toLowerCase();
        String urlPattern = ".*\\..*";
        Pattern pattern = Pattern.compile(urlPattern);
        Matcher matcher = pattern.matcher(userLink);
        String[] words = userLink.split(".");

        if (matcher.matches() || words.length > 1){
            if (userLink.isEmpty() || userLink.contains(" ")){
                edEnterLink.setError( "Enter Valid Link");
            }else {
                if (userLink.contains("https://www.")){
                    validURL =  userLink.split("https://www.")[1];
                }
                else if (userLink.contains("http://www.")){
                    validURL =  userLink.split("http://www.")[1];
                }
                else if (userLink.contains("https://")){
                    validURL =  userLink.split("https://")[1];
                }
                else if (userLink.contains("http://")) {
                    validURL =  userLink.split("http://")[1];
                }
                else if (userLink.contains("www.")){
                    validURL =  userLink.split("www.")[1];
                }else validURL = userLink;

                DemoSplash.userWebLINK = validURL;


                webtoapp.webURL = "https://www."+validURL;

                DemoSplash.otherActivityClass = webtoapp.class;
                Intent intent = new Intent(MainActivity.this,DemoSplash.class);
                startActivity(intent);
            }

        } else {
            edEnterLink.setError( "Enter Valid Link please");
        }

    }


    public void navItem(String navURL){
        webtoapp.webURL = navURL;

        DemoSplash.otherActivityClass = webtoapp.class;
        Intent intent = new Intent(MainActivity.this,DemoSplash.class);
        startActivity(intent);

    }


    //============================Open WhatsApp user Link=======================
    public void openWhatsAppUser() {
        // Replace "1234567890" with the phone number or contact you want to open
        String phoneNumber = "+8801868966864";

        // Create a WhatsApp URL using the "https://wa.me" URL scheme
        String url = "https://wa.me/" + phoneNumber;

        // Create an Intent to open the URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        // Check if there's an app that can handle this intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // WhatsApp is not installed, you can handle this situation here
            Toast.makeText(this, "WhatsApp is not installed.", Toast.LENGTH_SHORT).show();
        }
    }

    //===================Rate us on google play======================
    public void openGooglePlayForRating( ) {
        // Replace "your.package.name" with your app's package name
        String appPackageName = getPackageName();

        try {
            // Open the Google Play Store with your app's page
            Uri uri = Uri.parse("market://details?id=" + appPackageName);
            Intent rateIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(rateIntent);
        } catch (android.content.ActivityNotFoundException e) {
            // If Google Play Store app is not installed, open the Play Store in a web browser
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName);
            Intent rateIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(rateIntent);
        }
    }

    //========================Share this app====================
    public void shareApp() {
        // Create an intent to share text
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);

        // Set the message you want to share, like a link to your app
        String appLink = "Check out my awesome app: https://play.google.com/store/apps/details?id="+getPackageName();
        shareIntent.putExtra(Intent.EXTRA_TEXT, appLink);

        // Define the MIME type
        shareIntent.setType("text/plain");

        // Start the chooser to select an app for sharing
        startActivity(Intent.createChooser(shareIntent, "Share This App via"));
    }


}//==========================================