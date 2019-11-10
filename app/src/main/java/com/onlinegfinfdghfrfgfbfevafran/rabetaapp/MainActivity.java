package com.onlinegfinfdghfrfgfbfevafran.rabetaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.onlinegfinfdghfrfgfbfevafran.rabetaapp.Book.Book;
import com.onlinegfinfdghfrfgfbfevafran.rabetaapp.Button_5.Categori_5;
import com.onlinegfinfdghfrfgfbfevafran.rabetaapp.Categori_1.CategoriOne;
import com.onlinegfinfdghfrfgfbfevafran.rabetaapp.Categori_3.Categori3;

public class MainActivity extends AppCompatActivity {
    Button b5, b6, b1,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("রাবেতা");
        //for card 3
        b3=findViewById(R.id.btn3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Categori3.class);
                startActivity(intent);

            }
        });

        // for cardview 5

        b5 = findViewById(R.id.btn5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Categori_5.class);
                startActivity(intent);
            }
        });

        //// for cardview 6
        b6 = findViewById(R.id.btn6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Book.class);
                startActivity(intent);
            }
        });
        //card1
        b1 = findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoriOne.class);
                startActivity(intent);
            }
        });
    }
}