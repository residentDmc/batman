package com.codinginflow.batman.view_model;

import android.app.Application;

import com.codinginflow.batman.model.repository.MovieDetailRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import static com.codinginflow.batman.utils.AppConstants.API_KEY;

public class MovieDetailViewModel extends AndroidViewModel {

    private final MovieDetailRepository movieDetailRepository;

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        movieDetailRepository = new MovieDetailRepository();
    }

    public LiveData<Object> getMovieDetailResponseLiveData(String itemId) {
        return movieDetailRepository.getMovieDetailArticles(API_KEY, itemId);
    }
}
