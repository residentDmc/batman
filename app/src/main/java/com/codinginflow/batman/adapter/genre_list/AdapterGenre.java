package com.codinginflow.batman.adapter.genre_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codinginflow.batman.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterGenre extends RecyclerView.Adapter<ViewHolderGenre> {

    private List<String> genreList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolderGenre onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.genre_item, viewGroup, false);
        return new ViewHolderGenre(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGenre viewHolder, int i) {
        String genre = genreList.get(i);
        viewHolder.txtGenre.setText(genre);
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public void updateList(List<String> genreList) {
        this.genreList.addAll(genreList);
        notifyDataSetChanged();
    }
}
