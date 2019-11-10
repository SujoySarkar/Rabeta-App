package com.onlinegfinfdghfrfgfbfevafran.rabetaapp.Categori_1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.onlinegfinfdghfrfgfbfevafran.Model;
import com.onlinegfinfdghfrfgfbfevafran.ViewHolder;
import com.onlinegfinfdghfrfgfbfevafran.rabetaapp.Button_6.Details;
import com.onlinegfinfdghfrfgfbfevafran.rabetaapp.R;

public class CategoriOne extends AppCompatActivity {


    LinearLayoutManager mLayoutManager; //for sorting
    SharedPreferences mSharedPref; //for saving sort settings
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ProgressBar p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categori_one);
////////////////



        p=findViewById(R.id.pt);
        //Actionbar
        ActionBar actionBar = getSupportActionBar();
        //set title
        mSharedPref = getSharedPreferences("SortSettings", MODE_PRIVATE);
        String mSorting = mSharedPref.getString("Sort", "newest"); //where if no settingsis selected newest will be default

        if (mSorting.equals("newest")) {
            mLayoutManager = new LinearLayoutManager(this);
            //this will load the items from bottom means newest first
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
        } else if (mSorting.equals("oldest")) {
            mLayoutManager = new LinearLayoutManager(this);
            //this will load the items from bottom means oldest first
            mLayoutManager.setReverseLayout(false);
            mLayoutManager.setStackFromEnd(false);
        }
        //progress

        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);



        //set layout as LinearLayout
        mRecyclerView.setLayoutManager(mLayoutManager);

        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("one");

        isOnline();
    }








    ///////finish

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(CategoriOne.this, "Turn on Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }




    //load data into recycler view onStart
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.samplehomepage,
                        ViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                        p.setVisibility(View.INVISIBLE);
                        viewHolder.setDetails(getApplicationContext(), model.getImage(), model.getName(),model.getNumber1(),model.getNumber2());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Views
                                //get data from views

                                String nameo = getItem(position).getName();
                                String phoneo = getItem(position).getNumber1();
                                String phonet = getItem(position).getNumber2();
                                String mImage = getItem(position).getImage();


                                //pass this data to new activity
                                Intent intent = new Intent(view.getContext(), Details.class);
                                intent.putExtra("add1", nameo); //put add
                                intent.putExtra("phn1", phoneo); //put phn
                                intent.putExtra("pri1", phonet); //put pri
                                intent.putExtra("image",mImage); //put bitmap image as array of bytes

                                startActivity(intent); //start activity



                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                                //TODO do your own implementaion on long item click
                            }
                        });

                        return viewHolder;
                    }

                };

        //set adapter to recyclerview
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }



}