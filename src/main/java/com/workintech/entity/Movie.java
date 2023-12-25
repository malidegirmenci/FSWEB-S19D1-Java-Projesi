package com.workintech.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "movie", schema = "s19d1")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Name can not be null!")
    @Size(max = 75, message = "Name can not be over 75 character")
    private String name;

    @Column(name = "director_name")
    @Size(max = 75, message = "Director name can not be over 75 character")
    private String directorName;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "release_date")
    @Size(max = 75, message = "Release date can not be over 75 character")
    private String releaseDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(name = "movie_actor", schema = "s19d1",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actorList;

    public void addActor(Actor actor){
        if(actorList == null){
            actorList = new ArrayList<>();
        }
        actorList.add(actor);
    }

}
