package com.onlinegfinfdghfrfgfbfevafran.rabetaapp.Categori_3;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.onlinegfinfdghfrfgfbfevafran.ViewHolder;
import com.onlinegfinfdghfrfgfbfevafran.rabetaapp.R;
import com.squareup.picasso.Picasso;

public class Viewholdercat extends RecyclerView.ViewHolder {
    View mView;

    public Viewholdercat(@NonNull View itemView) {
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
    public void setDetails(Context ctx, String image01, String name01, String year01,String rule01, String number01){
        //Views(mtitletv
        ImageView imgp = mView.findViewById(R.id.imagep);
        TextView namp= mView.findViewById(R.id.namep);
        TextView yearp = mView.findViewById(R.id.yearp);
        TextView rulep = mView.findViewById(R.id.rulep);
        TextView nump = mView.findViewById(R.id.numberp);


        //set data to views
        Picasso.get().load(image01).into(imgp);
        namp.setText(name01);
        yearp.setText(year01);
        rulep.setText(rule01);
        nump.setText(number01);



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