package com.onlinegfinfdghfrfgfbfevafran.rabetaapp.Button_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.onlinegfinfdghfrfgfbfevafran.rabetaapp.R;

public class Categori_5 extends AppCompatActivity {
    FloatingActionButton fabcall,fabmeg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("তথ্য সংশোধন");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categori_5);

        //fab call
        fabcall=findViewById(R.id.fab1call);
        fabcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makePhoneCall();

            }
        });

        //fab message
        fabmeg=findViewById(R.id.fab1message);
        fabmeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messagedialouge();
            }
        });

    }

    private void messagedialouge()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) // At least KitKat
        {
            String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); // Need to change the build to API 19

            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra("address","01753954995");

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
            smsIntent.putExtra("address","01753954995");
            startActivity(smsIntent);
        }
    }

    private void makePhoneCall()
    {

        Intent callintent=new Intent(Intent.ACTION_DIAL);
        callintent.setData(Uri.parse("tel:01753954995"));
        startActivity(callintent);
    }
}
