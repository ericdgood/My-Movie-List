package mymovielistapp.pia.mymovielist.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mymovielistapp.pia.mymovielist.R;
import mymovielistapp.pia.mymovielist.presenter.MovieDetailPresenter;

public class MovieDetailView extends AppCompatActivity {

    @BindView(R.id.textview_detail_title)
    TextView tvMovieDetailTitle;

    private static final String TAG = "MovieDetailView";
    MovieDetailPresenter detailPresenter = new MovieDetailPresenter(this);
    ActionBar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_layout);
//        Start Butterknife
        ButterKnife.bind(this);
        toolbar = getSupportActionBar();
        toolbar.hide();

        detailPresenter.getMovieDetails();

    }

    public void displayMovieDetails(String movieTitle, String moviePoster, String movieOverview, String movieRelease, String movieVote){
        tvMovieDetailTitle.setText(movieTitle);
    }
}
