package mymovielistapp.pia.mymovielist.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import mymovielistapp.pia.mymovielist.R;
import mymovielistapp.pia.mymovielist.presenter.MovieDetailPresenter;

public class MovieDetailView extends AppCompatActivity {

    @BindView(R.id.textview_detail_title)
    TextView tvMovieDetailTitle;
    @BindView(R.id.img_detail_poster)
    ImageView imgMoviePoster;
    @BindView(R.id.tv_release_date)
    TextView tvReleaseDate;
    @BindView(R.id.tv_rating)
    TextView tvRating;
    @BindView(R.id.tv_overview)
    TextView tvOverview;

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

    public void displayMovieDetails(String movieTitle, String movieOverview, String movieRelease, String movieVote, String moviePoster){
        tvMovieDetailTitle.setText(movieTitle);
        tvOverview.setText(movieOverview);
        tvReleaseDate.setText(movieRelease);
        tvRating.setText(movieVote);

        setMoviePoster(moviePoster);
    }

    private void setMoviePoster(String moviePoster) {
//         Load Movie Poster
        Glide.with(this)
                .load(moviePoster)
                .error(R.drawable.ic_upcoming)
                .into(imgMoviePoster);
    }
}
