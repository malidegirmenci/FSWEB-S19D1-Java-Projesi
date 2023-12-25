package com.workintech.controller;

import com.workintech.dto.ActorResponse;
import com.workintech.dto.MovieResponse;
import com.workintech.entity.Actor;
import com.workintech.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {
    private final ActorService actorService;
    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }
    @PostMapping("/")
    public ActorResponse save(@Validated @RequestBody Actor actor){
        return actorService.save(actor);
    }
    @GetMapping("/")
    public List<ActorResponse> getAll(){
        return actorService.getAll();
    }
    @GetMapping("/{id}")
    public ActorResponse getById(@PathVariable Long id){
        return actorService.getById(id);
    }
    @PutMapping("/{id}")
    public ActorResponse update(@Validated @PathVariable Long id, @RequestBody Actor actor){
        return actorService.update(id,actor);
    }
    @DeleteMapping("/{id}")
    public ActorResponse delete(@PathVariable Long id){
        return actorService.delete(id);
    }

    @GetMapping("/{id}/movies")
    public List<MovieResponse> getMoviesFromActor(@PathVariable Long id){
        return actorService.getMoviesFromActor(id);
    }
}
