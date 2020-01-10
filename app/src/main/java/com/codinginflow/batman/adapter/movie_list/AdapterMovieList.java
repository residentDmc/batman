package com.codinginflow.batman.adapter.movie_list;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codinginflow.batman.R;
import com.codinginflow.batman.interfaces.AddOnItemTouchListener;
import com.codinginflow.batman.model.model.model_movie_list.Movie;
import com.codinginflow.batman.utils.GlideTools;

import java.util.ArrayList;
import java.util.List;

public class AdapterMovieList extends RecyclerView.Adapter<ViewHolderMovieList> {

    private Context context;
    private List<Movie> movieList = new ArrayList<>();
    private AddOnItemTouchListener addOnItemTouchListener;

    public AdapterMovieList(Context context) {
        this.context = context;
    }

    public void setItemClickListener(AddOnItemTouchListener addOnItemTouchListener) {

        this.addOnItemTouchListener = addOnItemTouchListener;
    }

    @NonNull
    @Override
    public ViewHolderMovieList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item_list, viewGroup, false);
        return new ViewHolderMovieList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMovieList viewHolder, int i) {
        Movie movie = movieList.get(i);
        viewHolder.txtTitle.setText(String.format("%s %s", movie.getTitle(), movie.getYear()));
        GlideTools.getInstance().displayImageOriginal(viewHolder.imgPoster,movie.getPoster());
        viewHolder.vItemParent.setOnClickListener(v -> addOnItemTouchListener.onItemTouchListener(movie.getImdbID()));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void updateList(List<Movie> movieList) {
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }
}
