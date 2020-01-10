package com.codinginflow.batman.view_model;

import android.app.Application;

import com.codinginflow.batman.model.repository.MovieListRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import static com.codinginflow.batman.utils.AppConstants.API_KEY;
import static com.codinginflow.batman.utils.AppConstants.MOVIE_LIST;

public class MovieListViewModel extends AndroidViewModel {

    private final MovieListRepository articleMovieListRepository;

    public MovieListViewModel(@NonNull Application application) {
        super(application);
        articleMovieListRepository = new MovieListRepository();
    }

    public LiveData<Object> getMovieListResponseLiveData() {
        return articleMovieListRepository.getMovieListArticles(API_KEY, MOVIE_LIST);
    }
}
