package com.onlinegfinfdghfrfgfbfevafran.rabetaapp.Categori_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.onlinegfinfdghfrfgfbfevafran.rabetaapp.R;
import com.squareup.picasso.Picasso;

public class Detailsssss extends AppCompatActivity {


    TextView name61, year61,rule61,number61;
    ImageView image61;
    Bitmap bitmap;
    FloatingActionButton fab3,fab4;
    private static final int REQUEST_CALL = 1;

    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();
        //Actionbar title
        actionBar.setTitle("Detail");
        //set back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsssss);






        //initialize views
        image61= findViewById(R.id.image61);
        name61 = findViewById(R.id.name61);
        year61= findViewById(R.id.year61);
         rule61= findViewById(R.id.rule61);
         number61= findViewById(R.id.phone61);




        //get data from intent
        String imagess = getIntent().getStringExtra("im7");
        String namess = getIntent().getStringExtra("nam7");
        String yearss = getIntent().getStringExtra("year7");
        String ruless = getIntent().getStringExtra("rule7");
        String numberss = getIntent().getStringExtra("num7");


///////////////////////////fab 2 call
        fab3=findViewById(R.id.flot2);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCallfab2();
            }
        });



/////fab4 meg
        fab4=findViewById(R.id.flotmeg2);
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                message2();

            }
        });
        ////////////////////
        //set data to views
        Picasso.get().load(imagess).into(image61);
        name61.setText(namess);
        year61.setText(yearss);
        rule61.setText(ruless);
        number61.setText(numberss);



        String gg=number61.getText().toString().trim();



//floating action button for call 1







//floating action button for meg 1











    }

    private void message2() {

        String number3 = number61.getText().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) // At least KitKat
        {
            String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); // Need to change the build to API 19

            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra("address",number3);

            if (defaultSmsPackageName != null)// Can be null in case that there is no default, then the user would be able to choose
            // any app that support this intent.
            {
                sendIntent.setPackage(defaultSmsPackageName);
            }
            startActivity(sendIntent);

        }
        else // For early versions, do what worked for you before.
        {
            Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address",number3);
            startActivity(smsIntent);
        }


    }

    private void makePhoneCallfab2() {

        String number1 = number61.getText().toString();
        if (number1.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(Detailsssss.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Detailsssss.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {



                String dial = "tel:" + number1;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }

    }

    //////////
    private void message() {

        String number = number61.getText().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) // At least KitKat
        {
            String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); // Need to change the build to API 19

            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra("address",number);

            if (defaultSmsPackageName != null)// Can be null in case that there is no default, then the user would be able to choose
            // any app that support this intent.
            {
                sendIntent.setPackage(defaultSmsPackageName);
            }
            startActivity(sendIntent);

        }
        else // For early versions, do what worked for you before.
        {
            Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address",number);
            startActivity(smsIntent);
        }


    }

    private void makePhoneCall() {

        String number = number61.getText().toString();
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(Detailsssss.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Detailsssss.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {



                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }
    }









    //handle onBackPressed(go to previous activity)
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }

    }
}



