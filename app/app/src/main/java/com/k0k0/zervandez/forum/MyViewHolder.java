package com.k0k0.zervandez.forum;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView content;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        content = itemView.findViewById(R.id.contentID);
    }
}
