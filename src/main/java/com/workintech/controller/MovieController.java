package com.workintech.controller;

import com.workintech.dto.ActorResponse;
import com.workintech.dto.MovieResponse;
import com.workintech.entity.Movie;
import com.workintech.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @PostMapping("/")
    public MovieResponse save(@Validated @RequestBody Movie movie){
        return movieService.save(movie);
    }
    @PutMapping("/{id}")
    public MovieResponse update(@PathVariable Long id, @RequestBody Movie movie){
        return movieService.update(id,movie);
    }

    @DeleteMapping("/{id}")
    public MovieResponse delete(@PathVariable Long id){
        return movieService.delete(id);
    }

    @GetMapping("/{id}")
    public MovieResponse getById(@PathVariable Long id){
        return movieService.getById(id);
    }
    @GetMapping("/")
    public List<MovieResponse> getAll(){
        return movieService.getAll();
    }

    @GetMapping("/{id}/actors")
    public List<ActorResponse> getActorsFromMovie(@PathVariable Long id){
        return movieService.getActorsFromMovie(id);
    }

}
