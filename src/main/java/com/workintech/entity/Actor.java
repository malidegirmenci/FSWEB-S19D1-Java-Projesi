package com.workintech.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "actor",schema = "s19d1")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    @NotNull(message = "First name can not be null!")
    @Size(max = 50,message = "First name can not be over 50 character")
    private String firstName;

    @Column(name ="last_name")
    @NotNull(message = "Last name can not be null!")
    @Size(max = 50,message = "Last name can not be over 50 character")
    private String lastName;

    @Column(name = "gender")
    @Size(max = 25,message = "Gender can not be over 25 character")
    private String gender;

    @Column(name = "birth_date")
    @Size(max = 50,message = "Birth date can not be over 50 character")
    private String birthDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(name = "movie_actor", schema = "s19d1",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movieList;
}
