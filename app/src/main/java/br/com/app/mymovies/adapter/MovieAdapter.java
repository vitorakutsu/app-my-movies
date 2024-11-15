package br.com.app.mymovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.app.mymovies.R;
import br.com.app.mymovies.entities.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    public MovieAdapter(List<Movie> movies, Context context, OnItemClickListener listener) {
        this.movies = movies;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.title.setText(movie.getTitle());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(movie));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movieTitle);
        }
    }
}
