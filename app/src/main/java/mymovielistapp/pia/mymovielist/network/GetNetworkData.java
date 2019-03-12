package mymovielistapp.pia.mymovielist.network;

import mymovielistapp.pia.mymovielist.model.Movies;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetNetworkData {

    @GET("now_playing?api_key=d32cc5bd24233a690a9c9c367d00111c&language=en-US&page=1")
    Call<Movies> getNowPlaying();

    @GET("upcoming?api_key=d32cc5bd24233a690a9c9c367d00111c&language=en-US&page=1")
    Call<Movies> getUpcoming();

}
