package mymovielistapp.pia.mymovielist.presenter;

import mymovielistapp.pia.mymovielist.model.Movies;
import retrofit2.Call;

public interface Presenter {

    void onCreate();

    void getMovieData(int movieOption);
}
