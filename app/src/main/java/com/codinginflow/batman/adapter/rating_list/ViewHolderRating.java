package com.codinginflow.batman.adapter.rating_list;

import android.view.View;
import android.widget.TextView;

import com.codinginflow.batman.R;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

class ViewHolderRating extends RecyclerView.ViewHolder {
        TextView txtRatingTitle;
        TextView txtRatingValue;

        ViewHolderRating(@NonNull View itemView) {
            super(itemView);
            txtRatingTitle= itemView.findViewById(R.id.txt_rating_title);
            txtRatingValue= itemView.findViewById(R.id.txt_rating_value);
        }
    }