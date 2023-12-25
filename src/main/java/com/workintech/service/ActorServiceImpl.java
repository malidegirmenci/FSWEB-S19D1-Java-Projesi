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
public class ActorServiceImpl implements ActorService {
    ActorRepository actorRepository;
    MovieRepository movieRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository,MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Actor findById(Long id) {
        Optional<Actor> optionalMovie = actorRepository.findById(id);
        if (optionalMovie.isPresent()) {
            return optionalMovie.get();
        }
        throw new CinemaException("Actor with given id could not find", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ActorResponse getById(Long id) {
        Actor actor = findById(id);
        return DtoConverter.convertToActorResponse(actor);
    }

    @Override
    public ActorResponse delete(Long id) {
        Actor willDeleteActor = findById(id);
        actorRepository.delete(willDeleteActor);
        return DtoConverter.convertToActorResponse(willDeleteActor);
    }

    @Override
    public ActorResponse update(Long id, Actor actor) {
        Actor willUpdateActor = findById(id);
        willUpdateActor.setFirstName(actor.getFirstName());
        willUpdateActor.setLastName(actor.getLastName());
        willUpdateActor.setGender(actor.getGender());
        willUpdateActor.setBirthDate(actor.getBirthDate());
        willUpdateActor.setMovieList(exportAddableMovieList(actor));
        return DtoConverter.convertToActorResponse(actorRepository.save(willUpdateActor));
    }
    private List<Movie> exportAddableMovieList(Actor actor){
        List<Movie> addableMovies = new ArrayList<>();

        for (Movie movie : actor.getMovieList()) {
            Optional<Movie> optionalMovie = movieRepository.findByName(movie.getName());
            if (optionalMovie.isPresent()) {
                addableMovies.add(optionalMovie.get());
            } else {
                addableMovies.add(movie);
            }
        }
        return addableMovies;
    }
    @Override
    public ActorResponse save(Actor actor) {
        if (findByFirstNameAndLastName(actor.getFirstName(), actor.getLastName()) == null) {
            actor.setMovieList(exportAddableMovieList(actor));
            return DtoConverter.convertToActorResponse(actorRepository.save(actor));
        }
        throw new CinemaException(actor.getFirstName() + " " + actor.getLastName() + " has already exist in database", HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<ActorResponse> getAll() {
        return DtoConverter.convertToActorResponseList(actorRepository.findAll());
    }

    @Override
    public List<MovieResponse> getMoviesFromActor(Long id) {
        Actor actor = findById(id);
        return DtoConverter.convertToMovieResponseList(actor.getMovieList());
    }

    @Override
    public Actor findByFirstNameAndLastName(String firstName, String lastName) {
        Optional<Actor> optionalMovie = actorRepository.findByFirstNameAndLastName(firstName, lastName);
        return optionalMovie.orElse(null);
    }
}
//fetchType jSonİgnore