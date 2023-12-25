package com.workintech.converter;

import com.workintech.dto.ActorResponse;
import com.workintech.dto.MovieResponse;
import com.workintech.entity.Actor;
import com.workintech.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {
    public static MovieResponse convertToMovieResponse(Movie movie){
        return new MovieResponse(
                movie.getName(),
                movie.getDirectorName(),
                movie.getRating(),
                movie.getReleaseDate()
        );
    }
    public static List<MovieResponse> convertToMovieResponseList(List<Movie> movieList){
        List<MovieResponse> responses = new ArrayList<>();
        movieList.forEach(movie -> responses.add(new MovieResponse(
                movie.getName(),
                movie.getDirectorName(),
                movie.getRating(),
                movie.getReleaseDate()
        )));
        return responses;
    }
    public static ActorResponse convertToActorResponse(Actor actor){
        return new ActorResponse(
                actor.getFirstName(),
                actor.getLastName(),
                actor.getGender(),
                actor.getBirthDate()

        );
    }
    public static List<ActorResponse> convertToActorResponseList(List<Actor> actorList){
        List<ActorResponse> responses = new ArrayList<>();
        actorList.forEach(actor -> responses.add(new ActorResponse(
                actor.getFirstName(),
                actor.getLastName(),
                actor.getGender(),
                actor.getBirthDate()

        )));
        return responses;
    }
}
