package com.workintech.dto;

import java.util.List;

public record MovieResponse(String name, String directorName, Double rating, String releaseDate, List<ActorResponse> actorList) {
}
