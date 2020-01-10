package com.codinginflow.batman.view_model;

import android.app.Application;

import com.codinginflow.batman.model.repository.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import static com.codinginflow.batman.utils.AppConstants.API_KEY;
import static com.codinginflow.batman.utils.AppConstants.MOVIE_LIST;

public class DetailMovieViewModel extends AndroidViewModel {

    private final Repository articleRepository;

    public DetailMovieViewModel(@NonNull Application application) {
        super(application);
        articleRepository = new Repository();
    }

    public LiveData<Object> getMovieListResponseLiveData() {
        return articleRepository.getMovieListArticles(API_KEY, MOVIE_LIST);
    }
}
