package com.codinginflow.batman.adapter.genre_list;

import android.view.View;
import android.widget.TextView;

import com.codinginflow.batman.R;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

class ViewHolderGenre extends RecyclerView.ViewHolder {
        TextView txtGenre;

        ViewHolderGenre(@NonNull View itemView) {
            super(itemView);
            txtGenre= itemView.findViewById(R.id.txt_genre);
        }
    }