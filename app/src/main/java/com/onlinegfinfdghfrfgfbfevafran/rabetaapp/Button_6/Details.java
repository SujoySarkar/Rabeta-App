package com.onlinegfinfdghfrfgfbfevafran.rabetaapp.Button_6;

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
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;


        import com.google.android.material.floatingactionbutton.FloatingActionButton;
        import com.onlinegfinfdghfrfgfbfevafran.rabetaapp.R;
        import com.squareup.picasso.Picasso;



public class Details extends AppCompatActivity {

    TextView ldetails, laddress,lprice,lphone,Pdate;
    ImageView mImageIv;
    Bitmap bitmap;
    FloatingActionButton fab,fab2,fab3,fab4;
    private static final int REQUEST_CALL = 1;

    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        //Actionbar title
        actionBar.setTitle("Detail");
        //set back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_details);



        //initialize views

        laddress = findViewById(R.id.address60);
        lphone = findViewById(R.id.phone60);
        lprice = findViewById(R.id.price60);

        mImageIv = findViewById(R.id.image60);


        //get data from intent
        String image = getIntent().getStringExtra("image");
        String address2 = getIntent().getStringExtra("add1");
        String phone2 = getIntent().getStringExtra("phn1");
        String price2 = getIntent().getStringExtra("pri1");


///////////////////////////fab 2 call
        fab3=findViewById(R.id.flot2);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCallfab2();
            }
        });

        ///////////
        fab2=findViewById(R.id.flotmeg1);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                message();
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

        laddress.setText(address2);
        lphone.setText(phone2);
        lprice.setText(price2);

        Picasso.get().load(image).into(mImageIv);

        String gg=lphone.getText().toString().trim();



//floating action button for call 1
        fab=findViewById(R.id.flot1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }
        });







//floating action button for meg 1











    }

    private void message2() {

        String number3 = lprice.getText().toString();
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

        String number1 = lprice.getText().toString();
        if (number1.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(Details.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Details.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {



                String dial = "tel:" + number1;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }

    }

    //////////
    private void message() {

        String number = lphone.getText().toString();
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

        String number = lphone.getText().toString();
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(Details.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Details.this,
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