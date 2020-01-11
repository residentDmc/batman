package com.codinginflow.batman.view.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codinginflow.batman.R;
import com.codinginflow.batman.adapter.movie_list.AdapterMovieList;
import com.codinginflow.batman.model.model.model_movie_list.Movie;
import com.codinginflow.batman.model.model.model_movie_list.MovieList;
import com.codinginflow.batman.utils.HandelErrorTools;
import com.codinginflow.batman.utils.NetworkTools;
import com.codinginflow.batman.utils.ScreenLayoutTools;
import com.codinginflow.batman.utils.ThrowableTools;
import com.codinginflow.batman.view_model.MovieListViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.codinginflow.batman.utils.AppConstants.ERROR_BUNDLE;
import static com.codinginflow.batman.utils.AppConstants.ITEM_ID_BUNDLE;

public class MovieListFragment extends Fragment {


    private AdapterMovieList adapter;

    @BindView(R.id.prg_movie_list)
    ProgressBar prgMovieList;

    @BindView(R.id.rc_movie_list)
    RecyclerView rcMovieList;

    private NavController navController;

    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        try {
            initView(view);
            initAdapter();
            getMovieList();


        } catch (Exception e) {
            HandelErrorTools.getInstance().setHandelError(e);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.my_nav_fragment);

    }

    private void initView(View view) {
        ButterKnife.bind(this, view);
    }

    private void initAdapter() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(getSpanCount(), StaggeredGridLayoutManager.VERTICAL);
        rcMovieList.setLayoutManager(staggeredGridLayoutManager);
        rcMovieList.setHasFixedSize(true);
        adapter = new AdapterMovieList(getContext());
        rcMovieList.setAdapter(adapter);
        adapter.setItemClickListener(object -> initNavDetailFragment((String) object));
    }

    private void initNavDetailFragment(String itemId) {
        navController.navigate(R.id.action_movieListFragment_to_detailMovieFragment, getBundleDetailMovie(itemId));
    }

    private int getSpanCount() {
        return ScreenLayoutTools.getInstance().screenLayout();
    }

    private void getMovieList() {

        MovieListViewModel movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        movieListViewModel.getMovieListResponseLiveData().observe(this, this::onChanged);
    }

    private void onChanged(Object obj) {
        if (obj instanceof Throwable) initNavErrorRetrofit(obj);
        else getDataMovieList((List<Movie>) obj);
    }

    private void initNavErrorRetrofit(Object obj) {
        String error = ThrowableTools.getInstance().getThrowableError((Throwable) obj);
        navController.navigate(R.id.action_movieListFragment_to_errorMessageRetrofitFragment, getBundleError(error));
    }

    private Bundle getBundleError(String error) {
        final Bundle bundle = new Bundle();
        bundle.putString(ERROR_BUNDLE, error);
        return bundle;
    }

    private Bundle getBundleDetailMovie(String itemId) {
        final Bundle bundle = new Bundle();
        bundle.putString(ITEM_ID_BUNDLE, itemId);
        return bundle;
    }

    private void getDataMovieList(List<Movie> movies) {
        if (movies != null) {
            prgMovieList.setVisibility(View.GONE);
            adapter.updateList(movies);
        }
    }


}
