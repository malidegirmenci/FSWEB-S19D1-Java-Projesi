package com.workintech.service;

import com.workintech.dto.ActorResponse;
import com.workintech.entity.Actor;

import java.util.List;


public interface ActorService {
    Actor findById(Long id);
    ActorResponse getById(Long id);
    ActorResponse delete(Long id);
    ActorResponse update(Long id, Actor actor);
    ActorResponse save(Actor actor);
    List<ActorResponse> getAll();
}
