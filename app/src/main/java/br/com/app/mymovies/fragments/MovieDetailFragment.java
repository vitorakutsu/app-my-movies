package br.com.app.mymovies.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.app.mymovies.R;
import br.com.app.mymovies.entities.Movie;

public class MovieDetailFragment extends Fragment {
    private Movie movie;

    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movie = (Movie) getArguments().getSerializable("movie");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        ImageView thumbnail = view.findViewById(R.id.movieThumbnail);
        TextView title = view.findViewById(R.id.movieTitle);
        TextView extract = view.findViewById(R.id.movieExtract);

        if (movie != null) {
            title.setText(movie.getTitle());
            extract.setText(movie.getExtract());
            Glide.with(this).load(movie.getThumbnail()).into(thumbnail);
        }

        return view;
    }
}
