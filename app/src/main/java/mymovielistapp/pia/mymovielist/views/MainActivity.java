package mymovielistapp.pia.mymovielist.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import mymovielistapp.pia.mymovielist.R;
import mymovielistapp.pia.mymovielist.adapters.RecyclerviewAdapter;
import mymovielistapp.pia.mymovielist.model.Movies;
import mymovielistapp.pia.mymovielist.presenter.MoviePresenter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    MoviePresenter presenter = new MoviePresenter(this);
    public ActionBar toolbar;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Create Presenter
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

    public void dismissProgressDialog(){
        progressDialog.dismiss();
    }

    public void generateDataList(Movies movieList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerviewAdapter(movieList, MainActivity.this));
    }

}
