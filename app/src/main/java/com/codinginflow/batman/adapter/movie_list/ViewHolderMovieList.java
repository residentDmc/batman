package com.codinginflow.batman.adapter.movie_list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codinginflow.batman.R;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

class ViewHolderMovieList extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView txtTitle;
        View vItemParent;

        ViewHolderMovieList(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.img_poster);
            txtTitle= itemView.findViewById(R.id.txt_title);
            vItemParent= itemView.findViewById(R.id.v_item_parent);
        }
    }