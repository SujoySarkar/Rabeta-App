package com.onlinegfinfdghfrfgfbfevafran.rabetaapp.Categori4;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.onlinegfinfdghfrfgfbfevafran.ViewHolder;
import com.onlinegfinfdghfrfgfbfevafran.rabetaapp.Categori_3.Detailsssss;
import com.onlinegfinfdghfrfgfbfevafran.rabetaapp.R;
import com.squareup.picasso.Picasso;

public class Viewholderfour extends RecyclerView.ViewHolder {
    View mView;

    public Viewholderfour(@NonNull View itemView) {
        super(itemView);
        mView = itemView;

        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////////////////////////////////////////////////
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
        //item long click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });

    }


    //set details to recycler view row
    public void setDetails(Context ctx, String image0q, String name0q, String year0q, String rule0q, String number0q){
        //Views(mtitletv
        ImageView imgq = mView.findViewById(R.id.imageq);
        TextView namq= mView.findViewById(R.id.nameq);
        TextView yearq = mView.findViewById(R.id.yearq);
        TextView ruleq = mView.findViewById(R.id.ruleq);
        TextView numq = mView.findViewById(R.id.numberq);



        //set data to views
        Picasso.get().load(image0q).into(imgq);
        namq.setText(name0q);
        yearq.setText(year0q);
        ruleq.setText(rule0q);
        numq.setText(number0q);

        //on click listener




    }

    private ViewHolder.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}