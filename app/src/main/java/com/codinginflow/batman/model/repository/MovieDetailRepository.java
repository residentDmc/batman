package com.codinginflow.batman.model.repository;

import android.annotation.SuppressLint;

import com.codinginflow.batman.model.model.model_movie_detail.MovieDetail;
import com.codinginflow.batman.model.network.api.ApiRequest;
import com.codinginflow.batman.model.network.retrofit.RetrofitRequest;
import com.codinginflow.batman.model.room.MovieDatabase;
import com.codinginflow.batman.model.room.MovieDetailDao;
import com.codinginflow.batman.utils.NetworkTools;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailRepository {

    private static final String TAG = MovieDetailRepository.class.getSimpleName();
    private ApiRequest apiRequest;
    private MovieDatabase appDatabase;
    private MovieDetailDao movieDetailDao;
    private MovieDetail movieDetailDatabase;

    public MovieDetailRepository() {
        appDatabase = MovieDatabase.getInstance();
        movieDetailDao = appDatabase.movieDetailDao();
        apiRequest = RetrofitRequest.getApiRequest();
    }

    @SuppressLint("CheckResult")
    public LiveData<Object> getMovieDetailArticles(String key, String imdb_id) {
        final MutableLiveData<Object> data = new MutableLiveData<>();
        movieDetailDatabase = movieDetailDao.getDetailMovie(imdb_id);
        if (NetworkTools.getInstance().isNetworkAvailable() && movieDetailDatabase != null)
            data.setValue(movieDetailDatabase);
        else
            apiRequest.movieDetail(key, imdb_id).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(movieDetailNetwork -> {
                        if (movieDetailNetwork != null) {
                            movieDetailDao.insert(movieDetailNetwork).subscribeOn(Schedulers.io()).subscribe();
                            data.setValue(movieDetailNetwork);
                        }
                    }, data::setValue);
        return data;
    }
}
