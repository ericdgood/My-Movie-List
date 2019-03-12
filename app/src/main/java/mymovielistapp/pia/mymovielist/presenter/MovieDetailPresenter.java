package mymovielistapp.pia.mymovielist.presenter;

import android.util.Log;

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
        String movieOverview = detailView.getIntent().getStringExtra("movieOverview");
        String movieRelease = detailView.getIntent().getStringExtra("movieRelease");
        String movieVote = detailView.getIntent().getStringExtra("movieVote");
        Log.i(TAG, "getMovieDetails: " + movieVote);
        String moviePoster = detailView.getIntent().getStringExtra("moviePoster");
        String moviePosterString = "https://image.tmdb.org/t/p/w500" + moviePoster;
        detailView.displayMovieDetails(movieTitle, movieOverview, movieRelease, movieVote, moviePosterString);


    }

}
