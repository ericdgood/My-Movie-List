package mymovielistapp.pia.mymovielist.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import mymovielistapp.pia.mymovielist.R;
import mymovielistapp.pia.mymovielist.model.Movies;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

    private Movies movieList;
    private Context context;

    public RecyclerviewAdapter(Movies movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.movie_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapter.ViewHolder viewHolder, int position) {
//      Set Movie Title
        viewHolder.tvMovieTitle.setText(movieList.getResults().get(position).getOriginalTitle());

//        Set Movie Poster
        String moviePosterString = "https://image.tmdb.org/t/p/w500" + movieList.getResults().get(position).getPosterPath();

        Glide.with(context)
                .load(moviePosterString)
                .error(R.drawable.ic_upcoming)
                .into(viewHolder.imgMoviePoster);
    }

    @Override
    public int getItemCount() {
        return movieList.getResults().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview_movie_title)
        TextView tvMovieTitle;
        @BindView(R.id.imageview_movie_poster)
        ImageView imgMoviePoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
