package com.codinginflow.batman.adapter.rating_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codinginflow.batman.R;
import com.codinginflow.batman.model.model.model_movie_detail.Rating;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterRating extends RecyclerView.Adapter<ViewHolderRating> {

    private List<Rating> genreList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolderRating onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rating_item, viewGroup, false);
        return new ViewHolderRating(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRating viewHolder, int i) {
        Rating rating = genreList.get(i);
        viewHolder.txtRatingTitle.setText(rating.getSource());
        viewHolder.txtRatingValue.setText(rating.getValue());
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public void updateList(List<Rating> genreList) {
        this.genreList.addAll(genreList);
        notifyDataSetChanged();
    }
}
