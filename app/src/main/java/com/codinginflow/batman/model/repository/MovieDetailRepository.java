package com.codinginflow.batman.model.repository;

import android.annotation.SuppressLint;

import com.codinginflow.batman.model.remote_data_source.api.ApiRequest;
import com.codinginflow.batman.model.remote_data_source.retrofit.RetrofitRequest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieListRepository {

    private static final String TAG = MovieListRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public MovieListRepository() {
        apiRequest = RetrofitRequest.getApiRequest();
    }

    @SuppressLint("CheckResult")
    public LiveData<Object> getMovieListArticles(String key,String movieListParam) {
        final MutableLiveData<Object> data = new MutableLiveData<>();
        apiRequest.moveList(key,movieListParam).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieList -> {
                    if (movieList != null)
                        data.setValue(movieList);

                }, data::setValue);
        return data;
    }
}
