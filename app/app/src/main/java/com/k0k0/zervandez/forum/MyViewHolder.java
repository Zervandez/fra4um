package com.k0k0.zervandez.forum;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView dateofPost;
    TextView content;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        content = itemView.findViewById(R.id.contentID);
        dateofPost = itemView.findViewById(R.id.datePost);
    }
}
