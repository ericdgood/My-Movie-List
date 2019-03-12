package mymovielistapp.pia.mymovielist.presenter;

import android.content.Intent;
import android.util.Log;

import mymovielistapp.pia.mymovielist.model.Movies;
import mymovielistapp.pia.mymovielist.views.MovieDetailView;

public class MovieDetailPresenter implements DetailPresenter {

    private static final String TAG = "MovieDetailPresenter";
    private MovieDetailView detailView;


    public MovieDetailPresenter(MovieDetailView movieDetailView) {
        this.detailView = movieDetailView;
    }

    public void getMovieDetails() {
        //    Movie Details
        String movieTitle = detailView.getIntent().getStringExtra("movieTitle");
        String moviePoster = detailView.getIntent().getStringExtra("moviePoster");
        String movieOverview = detailView.getIntent().getStringExtra("movieOverview");
        String movieRelease = detailView.getIntent().getStringExtra("movieRelease");
        String movieVote = detailView.getIntent().getStringExtra("movieVote");
        detailView.displayMovieDetails(movieTitle, moviePoster, movieOverview, movieRelease, movieVote);
    }


}
