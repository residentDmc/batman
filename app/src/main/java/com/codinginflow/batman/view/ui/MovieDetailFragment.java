package com.codinginflow.batman.view.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codinginflow.batman.R;
import com.codinginflow.batman.adapter.genre_list.AdapterGenre;
import com.codinginflow.batman.adapter.rating_list.AdapterRating;
import com.codinginflow.batman.model.model.model_movie_detail.MovieDetail;
import com.codinginflow.batman.model.model.model_movie_detail.Rating;
import com.codinginflow.batman.utils.GlideTools;
import com.codinginflow.batman.utils.HandelErrorTools;
import com.codinginflow.batman.utils.SplitterTools;
import com.codinginflow.batman.utils.ThrowableTools;
import com.codinginflow.batman.view_model.MovieDetailViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import static com.codinginflow.batman.utils.AppConstants.ERROR_BUNDLE;
import static com.codinginflow.batman.utils.AppConstants.ITEM_ID_BUNDLE;

public class MovieDetailFragment extends Fragment {


    @BindView(R.id.prg_movie_detail)
    ProgressBar prgMovieDetail;

    @BindView(R.id.txt_title_and_year)
    TextView txtTitleAndYear;

    @BindView(R.id.txt_rated)
    TextView txtRated;

    @BindView(R.id.txt_released)
    TextView txtReleased;

    @BindView(R.id.txt_runtime)
    TextView txtRuntime;

    @BindView(R.id.txt_plot)
    TextView txtPlot;

    @BindView(R.id.txt_rating)
    TextView txtRating;

    @BindView(R.id.txt_votes)
    TextView txtVotes;

    @BindView(R.id.txt_metascore)
    TextView txtMetascore;

    @BindView(R.id.txt_director)
    TextView txtDirector;

    @BindView(R.id.txt_writer)
    TextView txtWriter;

    @BindView(R.id.txt_actors)
    TextView txtActors;

    @BindView(R.id.txt_box_office)
    TextView txtBoxOffice;

    @BindView(R.id.txt_website)
    TextView txtWebsite;

    @BindView(R.id.txt_country)
    TextView txtCountry;

    @BindView(R.id.txt_language)
    TextView txtLanguage;

    @BindView(R.id.txt_production)
    TextView txtProduction;

    @BindView(R.id.txt_awards)
    TextView txtAwards;

    @BindView(R.id.v_detail_parent)
    View vDetailParent;

    @BindView(R.id.img_poster)
    ImageView imgPoster;

    @BindView(R.id.rc_genre)
    RecyclerView rcGenre;

    @BindView(R.id.rc_rating)
    RecyclerView rcRating;

    private NavController navController;

    public MovieDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        try {
            initView(view);
            getMovieDetail();
        } catch (Exception e) {
            HandelErrorTools.getInstance().setHandelError(e);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavFragment();
    }

    private void initNavFragment() {
        navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.my_nav_fragment);
    }

    private void initView(View view) {
        ButterKnife.bind(this, view);
    }

    private void getMovieDetail() {
        MovieDetailViewModel movieListViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        movieListViewModel.getMovieDetailResponseLiveData(getItemId()).observe(this, this::onChanged);

    }

    private String getItemId() {
        return Objects.requireNonNull(getArguments()).getString(ITEM_ID_BUNDLE, "");
    }

    private void onChanged(Object obj) {
        if (obj instanceof Throwable) getErrorRetrofit(obj);
        else if (obj instanceof MovieDetail) getDataMovieDetail(obj);
    }

    private void getErrorRetrofit(Object obj) {
        String error = ThrowableTools.getInstance().getThrowableError((Throwable) obj);
        navController.navigate(R.id.action_detailMovieFragment_to_errorMessageRetrofitFragment, getBundle(error));
    }

    private Bundle getBundle(String error) {
        final Bundle bundle = new Bundle();
        bundle.putString(ERROR_BUNDLE, error);
        return bundle;
    }

    private void getDataMovieDetail(Object obj) {
        prgMovieDetail.setVisibility(View.GONE);
        vDetailParent.setVisibility(View.VISIBLE);
        MovieDetail movieDetail = (MovieDetail) obj;
        if (movieDetail != null) {
            setDataResult(movieDetail);
        }
    }

    private void setDataResult(MovieDetail movieDetail) {
        GlideTools.getInstance().displayImageOriginal(imgPoster, movieDetail.getPoster());
        initAdapterGenre(getGenreList(movieDetail.getGenre()));
        initAdapterRatings(movieDetail.getRatings());
        txtTitleAndYear.setText(movieDetail.getTitleAndYear());
        txtRated.setText(movieDetail.getRated());
        txtRuntime.setText(movieDetail.getRuntime());
        txtReleased.setText(movieDetail.getReleased());
        txtPlot.setText(movieDetail.getPlot());
        txtRating.setText(movieDetail.getImdbRating());
        txtVotes.setText(movieDetail.getImdbVotes());
        txtMetascore.setText(movieDetail.getMetascore());
        txtDirector.setText(movieDetail.getDirector());
        txtWriter.setText(movieDetail.getWriter());
        txtActors.setText(movieDetail.getActors());
        txtBoxOffice.setText(movieDetail.getBoxOffice());
        txtWebsite.setText(movieDetail.getWebsite());
        txtCountry.setText(movieDetail.getCountry());
        txtLanguage.setText(movieDetail.getLanguage());
        txtProduction.setText(movieDetail.getProduction());
        txtAwards.setText(movieDetail.getAwards());
    }

    private void initAdapterRatings(List<Rating> ratings) {
        rcRating.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcRating.setHasFixedSize(true);
        AdapterRating adapter = new AdapterRating();
        rcRating.setAdapter(adapter);
        adapter.updateList(ratings);
    }

    private List<String> getGenreList(String genre) {
        return SplitterTools.getInstance().splitterStringList(genre);
    }

    private void initAdapterGenre(List<String> genreList) {
        rcGenre.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcGenre.setHasFixedSize(true);
        AdapterGenre adapter = new AdapterGenre();
        rcGenre.setAdapter(adapter);
        adapter.updateList(genreList);
    }
}
