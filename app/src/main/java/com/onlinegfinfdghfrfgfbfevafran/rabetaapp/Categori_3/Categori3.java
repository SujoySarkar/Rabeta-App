package com.onlinegfinfdghfrfgfbfevafran.rabetaapp.Categori_3;

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

public class Categori3 extends AppCompatActivity {
    LinearLayoutManager mLayoutManager; //for sorting
    SharedPreferences mSharedPref; //for saving sort settings
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ProgressBar p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Cat3");
        setContentView(R.layout.activity_categori3);
        p = findViewById(R.id.pt);

        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);



        //set layout as LinearLayout
        mRecyclerView.setLayoutManager(mLayoutManager);

        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("three");

        isOnline();
    }








    ///////finish

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(Categori3.this, "Turn on Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }




    //load data into recycler view onStart
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Modelcat, Viewholdercat> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Modelcat, Viewholdercat>(
                        Modelcat.class,
                        R.layout.samplecat,
                        Viewholdercat.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(Viewholdercat viewHolder, Modelcat model, int position) {
                        p.setVisibility(View.INVISIBLE);
                        viewHolder.setDetails(getApplicationContext(), model.getImagedd(), model.getNamedd(),model.getYeardd(),model.getRuledd(),model.getNumberdd());
                    }

                    @Override
                    public Viewholdercat onCreateViewHolder(ViewGroup parent, int viewType) {



                        Viewholdercat viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Views
                                //get data from views
                                String mImage99 = getItem(position).getImagedd();
                                String name99 = getItem(position).getNamedd();
                                String year99 = getItem(position).getYeardd();
                                String rule99 = getItem(position).getRuledd();
                                String number99 = getItem(position).getNumberdd();


                                //pass this data to new activity
                                Intent intent = new Intent(view.getContext(), Detailsssss.class);
                                intent.putExtra("im7",mImage99); //put bitmap image as array of bytes

                                intent.putExtra("nam7", name99); //put add
                                intent.putExtra("year7", year99); //put phn
                                intent.putExtra("rule7", rule99); //put pri
                                intent.putExtra("num7", number99); //put pri

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



        //handle other action bar item clicks here
        if (id == R.id.action_sort) {
            //display alert dialog to choose sorting
            showSortDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSortDialog() {
        //options to display in dialog
        String[] sortOptions = {" Newest", " Oldest"};
        //create alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort by") //set title
                .setIcon(R.drawable.ic_launcher_background) //set icon
                .setItems(sortOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position of the selected item
                        // 0 means "Newest" and 1 means "oldest"
                        if (which == 0) {
                            //sort by newest
                            //Edit our shared preferences
                            SharedPreferences.Editor editor = mSharedPref.edit();
                            editor.putString("Sort", "newest"); //where 'Sort' is key & 'newest' is value
                            editor.apply(); // apply/save the value in our shared preferences
                            recreate(); //restart activity to take effect
                        } else if (which == 1) {
                            {
                                //sort by oldest
                                //Edit our shared preferences
                                SharedPreferences.Editor editor = mSharedPref.edit();
                                editor.putString("Sort", "oldest"); //where 'Sort' is key & 'oldest' is value
                                editor.apply(); // apply/save the value in our shared preferences
                                recreate(); //restart activity to take effect
                            }
                        }
                    }
                });
        builder.show();
    }

}