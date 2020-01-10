package com.codinginflow.batman;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codinginflow.batman.adapter.MovieArticleAdapter;
import com.codinginflow.batman.utils.HandelErrorTools;
import com.codinginflow.batman.utils.ScreenLayoutTools;
import com.codinginflow.batman.view_model.MovieListViewModel;
import com.codinginflow.batman.widget.ViewLoadingDotsBounce;

import java.util.Objects;

public class MovieListFragment extends Fragment {


    private MovieArticleAdapter adapter;
    private MovieListViewModel movieListViewModel;

    @BindView(R.id.prg_movie_list)
    ViewLoadingDotsBounce prgMovieList;

    @BindView(R.id.rc_movie_list)
    RecyclerView rcMovieList;

    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        try {
            View view= inflater.inflate(R.layout.fragment_movie_list, container, false);
            initView();
            initAdapter();
            getMovieList();
            return view;
        } catch (Exception e) {
            HandelErrorTools.getInstance().setHandelError(getContext(), e);
        }
        return null;
    }

    private void initView(View view) {
        ButterKnife.bind(view,this);
    }

    private void initAdapter() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(getSpanCount(), StaggeredGridLayoutManager.VERTICAL);
        rcMovieList.setLayoutManager(staggeredGridLayoutManager);
        rcMovieList.setHasFixedSize(true);
        adapter = new MovieArticleAdapter(getContext());
        rcMovieList.setAdapter(adapter);

        // View Model
        movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
    }

    private int getSpanCount() {
        return ScreenLayoutTools.getInstance().screenLayout(Objects.requireNonNull(getContext()));
    }


    private void getMovieList() {
        movieListViewModel.getMovieListResponseLiveData().observe(this, movieList -> {
            if (movieList != null) {
                prgMovieList.setVisibility(View.GONE);
                adapter.updateList(movieList);
            }
        });
    }

}
