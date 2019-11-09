package com.onlinegfinfdghfrfgfbfevafran.rabetaapp.Book;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.onlinegfinfdghfrfgfbfevafran.rabetaapp.R;


public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView mName;TextView mLink;
    Button mDownload;
    ImageView mmimageView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        mName=itemView.findViewById(R.id.name1);
        mLink=itemView.findViewById(R.id.link);
        mDownload=itemView.findViewById(R.id.down);


    }
}