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
    public void setDetails(Context ctx, String details21, String address21,String phone21,String price21,String image,String date21){
        //Views(mtitletv
        TextView details22 = mView.findViewById(R.id.roomdetailstextviewid4);
        TextView address22 = mView.findViewById(R.id.addresstextviewid4);
        TextView phone22 = mView.findViewById(R.id.phonetextviewid4);
        TextView price22 = mView.findViewById(R.id.pricetextviewid4);

        ImageView mImageIv = mView.findViewById(R.id.imageshowid4);
        TextView date22 = mView.findViewById(R.id.datetextviewid4);
        //set data to views
        details22.setText(details21);
        address22.setText(address21);
        phone22.setText(phone21);
        price22.setText(price21);

        Picasso.get().load(image).into(mImageIv);
        date22.setText(date21);
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