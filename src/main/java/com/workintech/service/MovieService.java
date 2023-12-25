package com.workintech.service;

import com.workintech.dto.ActorResponse;
import com.workintech.dto.MovieResponse;
import com.workintech.entity.Movie;

import java.util.List;

public interface MovieService {
    Movie findById(Long id);
    MovieResponse getById(Long id);
    MovieResponse delete(Long id);
    MovieResponse update(Long id, Movie movie);
    MovieResponse save(Movie movie);
    List<MovieResponse> getAll();
    List<ActorResponse> getActorsFromMovie(Long id);
    Movie findByName(String name);

}
