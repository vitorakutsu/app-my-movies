package br.com.app.mymovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

import br.com.app.mymovies.R;
import br.com.app.mymovies.entities.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final List<Movie> movies;
    private final Context context;
    private final OnMovieClickListener listener;

    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }

    public MovieAdapter(List<Movie> movies, Context context, OnMovieClickListener listener) {
        this.movies = movies;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.movieTitle.setText(movie.getTitle());

        // Carrega a imagem com Glide (ou outra biblioteca de sua escolha)
        Glide.with(context)
                .load(movie.getThumbnail()) // Certifique-se de que `getImageUrl` retorna a URL correta
                .into(holder.movieImage);

        holder.itemView.setOnClickListener(v -> listener.onMovieClick(movie));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle;
        ImageView movieImage;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieImage = itemView.findViewById(R.id.movieImage);
        }
    }
}
