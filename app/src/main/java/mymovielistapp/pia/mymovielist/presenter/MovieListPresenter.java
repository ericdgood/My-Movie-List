package mymovielistapp.pia.mymovielist.presenter;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import mymovielistapp.pia.mymovielist.R;
import mymovielistapp.pia.mymovielist.model.Movies;
import mymovielistapp.pia.mymovielist.network.GetNetworkData;
import mymovielistapp.pia.mymovielist.network.RetroClient;
import mymovielistapp.pia.mymovielist.views.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListPresenter implements ListPresenter {

    private static final String TAG = "MovieListPresenter";
    private MainActivity view;
    private Call<Movies> call;

    public MovieListPresenter(MainActivity view) {
        this.view = view;
    }


    @Override
    public void onCreate() {
    }


    public void getMovieData(int movieOption) {

        movieOption(movieOption).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                view.dismissProgressDialog();
                Log.i(TAG, "onResponse: " + response.body());
                view.generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                view.dismissProgressDialog();
                Log.i(TAG, "onFailure: " + t);
            }
        });
    }

    private Call<Movies> movieOption(int movieOption) {
        GetNetworkData service = RetroClient.getRetrofitInstance().create(GetNetworkData.class);

        if (movieOption == (R.string.now_playing))
            call = service.getNowPlaying();

        if (movieOption == R.string.upcoming)
            call = service.getUpcoming();

        return call;
    }

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.now_playing:
                    view.toolbar.setTitle(R.string.now_playing);
                    getMovieData(R.string.now_playing);
                    return true;
                case R.id.favorites:
                    view.toolbar.setTitle(R.string.favorites);
                    return true;
                case R.id.upcoming:
                    view.toolbar.setTitle(R.string.upcoming);
                    getMovieData(R.string.upcoming);
                    return true;
            }
            return false;
        }
    };
}
