package com.onlinegfinfdghfrfgfbfevafran;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.onlinegfinfdghfrfgfbfevafran.rabetaapp.R;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder{

    View mView;

    public ViewHolder(@NonNull View itemView) {
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
    public void setDetails(Context ctx,String image00,String name00,String phoneo00,String phonet00){
        //Views(mtitletv
        TextView namemat= mView.findViewById(R.id.name9);
        TextView phn1mat = mView.findViewById(R.id.numberone9);
        TextView phn2mat = mView.findViewById(R.id.numbertwo9);
        ImageView imgmat = mView.findViewById(R.id.imageshowid9);

        //set data to views
        namemat.setText(name00);
        phn1mat.setText(phoneo00);
        phn2mat.setText(phonet00);
        Picasso.get().load(image00).into(imgmat);

    }

    private ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ClickListener clickListener){
        mClickListener = clickListener;
    }
}