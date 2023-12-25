package com.workintech.service;

import com.workintech.converter.DtoConverter;
import com.workintech.dto.ActorResponse;
import com.workintech.entity.Actor;
import com.workintech.exceptions.CinemaException;
import com.workintech.repository.ActorRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Service
public class ActorServiceImpl implements ActorService {
    ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public Actor findById(Long id) {
        Optional<Actor> optionalMovie = actorRepository.findById(id);
        if(optionalMovie.isPresent()){
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
        return DtoConverter.convertToActorResponse(actorRepository.save(willUpdateActor));
    }

    @Override
    public ActorResponse save(Actor actor) {
        return DtoConverter.convertToActorResponse(actorRepository.save(actor));
    }

    @Override
    public List<ActorResponse> getAll() {
        return DtoConverter.convertToActorResponseList(actorRepository.findAll());
    }
}
