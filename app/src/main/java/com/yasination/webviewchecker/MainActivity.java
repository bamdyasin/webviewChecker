package com.yasination.webviewchecker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                } else if (item.getItemId() == R.id.blogger) {
                    navItem( "https://www.brandingdone.com");
                    Toast.makeText(MainActivity.this, "Welcome to Blogger Website", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (item.getItemId() == R.id.travel) {
                    navItem( "https://www.obokash.com/");
                    Toast.makeText(MainActivity.this, "Welcome to Traveling Website", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);

                }
                else if (item.getItemId() == R.id.ecommerce) {
                    Toast.makeText(MainActivity.this, "Welcome to e-Commerce Website", Toast.LENGTH_SHORT).show();
                    navItem( "https://www.bdstall.com");
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (item.getItemId() == R.id.ecommerce2) {
                    navItem( "https://www.startech.com.bd/");
                    Toast.makeText(MainActivity.this, "Welcome to e-Commerce Website", Toast.LENGTH_SHORT).show();
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



}//==========================================