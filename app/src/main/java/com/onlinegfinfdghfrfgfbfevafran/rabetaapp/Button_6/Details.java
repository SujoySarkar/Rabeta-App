package com.onlinegfinfdghfrfgfbfevafran.rabetaapp.Button_6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.onlinegfinfdghfrfgfbfevafran.rabetaapp.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class Details extends AppCompatActivity {

    TextView ldetails, laddress,lprice,lphone,Pdate;
    ImageView mImageIv;
    Bitmap bitmap;
    FloatingActionButton fab;
    private static final int REQUEST_CALL = 1;

    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        //Actionbar title
        actionBar.setTitle("Post Detail");
        //set back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_details);



        //initialize views
        ldetails = findViewById(R.id.details60);
        laddress = findViewById(R.id.address60);
        lphone = findViewById(R.id.phone60);
        lprice = findViewById(R.id.price60);
        Pdate = findViewById(R.id.date60);
        mImageIv = findViewById(R.id.image60);


        //get data from intent
        String image = getIntent().getStringExtra("image");
        String details2 = getIntent().getStringExtra("del1");
        String address2 = getIntent().getStringExtra("add1");
        String phone2 = getIntent().getStringExtra("phn1");
        String price2 = getIntent().getStringExtra("pri1");
        String date2 = getIntent().getStringExtra("date1");



        //set data to views
        ldetails.setText(details2);
        laddress.setText(address2);
        lphone.setText(phone2);
        lprice.setText(price2);
        Pdate.setText(date2);
        Picasso.get().load(image).into(mImageIv);

        String gg=lphone.getText().toString().trim();



//floating action button
        fab=findViewById(R.id.flot1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }
        });





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





    private void setImgWallpaper() {
        bitmap = ((BitmapDrawable)mImageIv.getDrawable()).getBitmap();
        WallpaperManager myWallManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            myWallManager.setBitmap(bitmap);
            Toast.makeText(this, "Wallpaper set...", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void shareImage() {
        try {
            bitmap = ((BitmapDrawable)mImageIv.getDrawable()).getBitmap();
            //get title and description and save in string s
            String s = ldetails.getText().toString() + "\n" + laddress.getText().toString()+ "\n" + lphone.getText().toString()+ "\n" + lprice.getText().toString();

            File file = new File(getExternalCacheDir(), "sample.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true,false);
            //intent to share image and text
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_TEXT, s); // put the text
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent, "Share via"));
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveImage() {
        bitmap = ((BitmapDrawable)mImageIv.getDrawable()).getBitmap();
        //time stamp, for image name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(System.currentTimeMillis());
        //path to external storage
        File path = Environment.getExternalStorageDirectory();
        //create folder named "Firebase"
        File dir = new File(path+"/Firebase/");
        dir.mkdirs();
        //image name
        String imageName = timeStamp + ".PNG";
        File file = new File(dir, imageName);
        OutputStream out;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(this, imageName+" saved to"+ dir, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            //failed saving image
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
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