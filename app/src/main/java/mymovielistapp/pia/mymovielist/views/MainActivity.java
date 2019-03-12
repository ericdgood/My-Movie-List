package mymovielistapp.pia.mymovielist.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mymovielistapp.pia.mymovielist.R;
import mymovielistapp.pia.mymovielist.adapters.RecyclerviewAdapter;
import mymovielistapp.pia.mymovielist.model.Movies;
import mymovielistapp.pia.mymovielist.presenter.MovieListPresenter;

public class MainActivity extends AppCompatActivity implements RecyclerviewAdapter.OnMovieClicked {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private static final String TAG = "MainActivity";
    MovieListPresenter presenter = new MovieListPresenter(this);
    public ActionBar toolbar;
    public ProgressDialog progressDialog;
    Movies movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Create ListPresenter
        presenter.onCreate();

//        Start Butterknife
        ButterKnife.bind(this);

//        Setup toolbar
        toolbar = getSupportActionBar();
        toolbar.setTitle(R.string.now_playing);
        presenter.getMovieData(R.string.now_playing);

//          Start ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.show();

        navigation.setOnNavigationItemSelectedListener(presenter.mOnNavigationItemSelectedListener);
    }

    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    public void generateDataList(Movies movieList) {
        this.movieList = movieList;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerviewAdapter(movieList, this,this));
    }

    @Override
    public void OnMovieClickedDetails(int position) {
        Intent movieDetail  = new Intent(this, MovieDetailView.class);
        movieDetail.putExtra("movieTitle", movieList.getResults().get(position).getTitle());
        movieDetail.putExtra("moviePoster", movieList.getResults().get(position).getPosterPath());
        movieDetail.putExtra("movieOverview", movieList.getResults().get(position).getOverview());
        movieDetail.putExtra("movieRelease", movieList.getResults().get(position).getReleaseDate());
        movieDetail.putExtra("movieVote", movieList.getResults().get(position).getVoteAverage());
        startActivity(movieDetail);
    }
}
