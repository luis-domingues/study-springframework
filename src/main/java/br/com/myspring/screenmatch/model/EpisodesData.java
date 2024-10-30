package br.com.myspring.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)
public record EpisodesData(@JsonAlias ("Title") String title,
                           @JsonAlias ("Episode") Integer numEpisode,
                           @JsonAlias ("imdbRating") String rating,
                           @JsonAlias ("Released") String releaseDate) {
}
