package br.com.app.mymovies.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.app.mymovies.R;
import br.com.app.mymovies.adapter.MovieAdapter;
import br.com.app.mymovies.config.RetrofitInstance;
import br.com.app.mymovies.entities.Movie;
import br.com.app.mymovies.service.MovieApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    private MovieApiService movieApiService;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        EditText searchInput = view.findViewById(R.id.searchInput);
        Button searchButton = view.findViewById(R.id.searchButton);
        recyclerView = view.findViewById(R.id.recyclerViewMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        movieApiService = RetrofitInstance.getRetrofitInstance().create(MovieApiService.class);

        searchButton.setOnClickListener(v -> {
            String query = searchInput.getText().toString();
            searchMovies(query);
        });

        return view;
    }

    private void searchMovies(String query) {
        Call<List<Movie>> call = movieApiService.getMovies(query);
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body();
                    movieAdapter = new MovieAdapter(movies, getContext(), movie -> {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("movie", movie);
                        getParentFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment_content_main, MovieDetailFragment.class, bundle)
                                .addToBackStack(null)
                                .commit();
                    });
                    recyclerView.setAdapter(movieAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(getContext(), "Erro ao buscar filmes", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
