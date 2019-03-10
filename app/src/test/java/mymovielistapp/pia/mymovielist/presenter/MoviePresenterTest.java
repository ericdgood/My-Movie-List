package mymovielistapp.pia.mymovielist.presenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import mymovielistapp.pia.mymovielist.R;
import mymovielistapp.pia.mymovielist.model.Movies;
import mymovielistapp.pia.mymovielist.views.MainActivity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MoviePresenterTest {

    Movies movieList;

    MoviePresenter presenter;

    @Mock
    private MainActivity view;

    @Test
    public Movies getMovieData() {
        presenter.getMovieData(R.string.now_playing);
        return movieList;
    }
}