package mymovielistapp.pia.mymovielist.presenter;

import mymovielistapp.pia.mymovielist.adapters.RecyclerviewAdapter;
import mymovielistapp.pia.mymovielist.model.Movies;
import retrofit2.Call;

public interface ListPresenter {

    void onCreate();

    void getMovieData(int movieOption);
}
