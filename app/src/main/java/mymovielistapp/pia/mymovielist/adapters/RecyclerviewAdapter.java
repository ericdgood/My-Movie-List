package mymovielistapp.pia.mymovielist.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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
    private OnMovieClicked onMovieClicked;
    private Context context;

    public RecyclerviewAdapter(Movies movieList, OnMovieClicked onMovieClicked, Context context) {
        this.movieList = movieList;
        this.onMovieClicked = onMovieClicked;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.movie_layout, viewGroup, false);
        return new ViewHolder(view, onMovieClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapter.ViewHolder viewHolder, int position) {
//      Set Movie Title
        viewHolder.tvMoviesTitle.setText(movieList.getResults().get(position).getOriginalTitle());

//        Set Movie Poster
        String moviePosterString = "https://image.tmdb.org/t/p/w500" + movieList.getResults().get(position).getPosterPath();
//         Load Movie Poster
        Glide.with(context)
                .load(moviePosterString)
                .error(R.drawable.ic_upcoming)
                .into(viewHolder.imgMoviePoster);
    }

    @Override
    public int getItemCount() {
        return movieList.getResults().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textview_movie_title)
        TextView tvMoviesTitle;
        @BindView(R.id.imageview_movie_poster)
        ImageView imgMoviePoster;
        @BindView(R.id.constrant_movie_layout)
        ConstraintLayout layMovieLayout;
        OnMovieClicked onMovieClicked;

        public ViewHolder(@NonNull View itemView, OnMovieClicked onMovieClicked) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.onMovieClicked = onMovieClicked;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onMovieClicked.OnMovieClickedDetails(getAdapterPosition());
        }
    }
        public interface OnMovieClicked{
            void OnMovieClickedDetails(int position);
        }

}

