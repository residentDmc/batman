package com.codinginflow.batman.model.repository;

import android.annotation.SuppressLint;

import com.codinginflow.batman.model.network.api.ApiRequest;
import com.codinginflow.batman.model.network.retrofit.RetrofitRequest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailRepository {

    private static final String TAG = MovieDetailRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public MovieDetailRepository() {
        apiRequest = RetrofitRequest.getApiRequest();
    }

    @SuppressLint("CheckResult")
    public LiveData<Object> getMovieDetailArticles(String key,String movieDetailParam) {
        final MutableLiveData<Object> data = new MutableLiveData<>();
        apiRequest.movieDetail(key,movieDetailParam).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieList -> {
                    if (movieList != null)
                        data.setValue(movieList); }
                        , data::setValue);
        return data;
    }
}
