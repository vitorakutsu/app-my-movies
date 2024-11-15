package br.com.app.mymovies.service;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import br.com.app.mymovies.entities.Movie;

public interface MovieApiService {
    @GET("api/find-movies")
    Call<List<Movie>> getMovies(@Query("filter") String filter);
}

