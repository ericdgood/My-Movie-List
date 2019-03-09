package mymovielistapp.pia.mymovielist.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import mymovielistapp.pia.mymovielist.R;
import mymovielistapp.pia.mymovielist.adapters.RecyclerviewAdapter;
import mymovielistapp.pia.mymovielist.model.Movies;
import mymovielistapp.pia.mymovielist.network.GetNetworkData;
import mymovielistapp.pia.mymovielist.network.RetroClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private static final String TAG = "MainActivity";
    ActionBar toolbar;
    ProgressDialog progressDialog;
    RecyclerviewAdapter adapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.now_playing:
                    toolbar.setTitle(R.string.now_playing);
                    return true;
                case R.id.favorites:
                    toolbar.setTitle(R.string.favorites);
                    return true;
                case R.id.upcoming:
                    toolbar.setTitle(R.string.upcoming);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Start Butterknife
        ButterKnife.bind(this);

//        Setup toolbar
        toolbar = getSupportActionBar();
        toolbar.setTitle(R.string.now_playing);

//          Start ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.show();

        getMovieData();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void getMovieData() {

        GetNetworkData service = RetroClient.getRetrofitInstance().create(GetNetworkData.class);

        Call<Movies> call = service.getNowPlaying();

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                progressDialog.dismiss();
                genorateDataList(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                progressDialog.dismiss();
                Log.i(TAG, "onFailure: " + t);
            }
        });
    }

    private void genorateDataList(Movies movieList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerviewAdapter(movieList, MainActivity.this));
    }

}
