package com.workintech.service;

import com.workintech.converter.DtoConverter;
import com.workintech.dto.ActorResponse;
import com.workintech.dto.MovieResponse;
import com.workintech.entity.Actor;
import com.workintech.entity.Movie;
import com.workintech.exceptions.CinemaException;
import com.workintech.repository.ActorRepository;
import com.workintech.repository.MovieRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Service
public class MovieServiceImpl implements MovieService{
    private MovieRepository movieRepository;
    private ActorRepository actorRepository;
    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }
    @Override
    public Movie findByName(String name) {
        Optional<Movie> optionalMovie = movieRepository.findByName(name);
        return optionalMovie.orElse(null);
    }
    @Override
    public Movie findById(Long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if(optionalMovie.isPresent()){
            return optionalMovie.get();
        }
        throw new CinemaException("Movie with given id could not find", HttpStatus.NOT_FOUND);
    }

    @Override
    public MovieResponse getById(Long id) {
        Movie movie = findById(id);
        return DtoConverter.convertToMovieResponse(movie);
    }

    @Override
    public MovieResponse delete(Long id) {
        Movie willDeleteMovie = findById(id);
        movieRepository.delete(willDeleteMovie);
        return DtoConverter.convertToMovieResponse(willDeleteMovie);
    }

    @Override
    public MovieResponse update(Long id, Movie movie) {
        Movie willUpdateMovie = findById(id);
        willUpdateMovie.setName(movie.getName());
        willUpdateMovie.setDirectorName(movie.getDirectorName());
        willUpdateMovie.setRating(movie.getRating());
        willUpdateMovie.setReleaseDate(movie.getReleaseDate());
        willUpdateMovie.setActorList(exportAddableActorList(movie));
        return DtoConverter.convertToMovieResponse(movieRepository.save(willUpdateMovie));
    }
    private List<Actor> exportAddableActorList(Movie movie){
        List<Actor> addableActors = new ArrayList<>();
        for (Actor actor : movie.getActorList()) {
            Optional<Actor> optionalActor = actorRepository.findByFirstNameAndLastName(actor.getFirstName(), actor.getLastName());
            if (optionalActor.isPresent()) {
                addableActors.add(optionalActor.get());
            } else {
                addableActors.add(actor);
            }
        }
        return addableActors;
    }
    @Override
    public MovieResponse save(Movie movie) {
        if(findByName(movie.getName()) == null){
            movie.setActorList(exportAddableActorList(movie));
            return DtoConverter.convertToMovieResponse(movieRepository.save(movie));
        }
        throw new CinemaException(movie.getName()+" has already exist in database",HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<MovieResponse> getAll(){
        return DtoConverter.convertToMovieResponseList(movieRepository.findAll());
    }

    @Override
    public List<ActorResponse> getActorsFromMovie(Long id) {
        Movie movie = findById(id);
        return DtoConverter.convertToActorResponseList(movie.getActorList());
    }


}
