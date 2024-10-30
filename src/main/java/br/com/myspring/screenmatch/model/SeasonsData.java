package br.com.myspring.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties (ignoreUnknown = true)
public record SeasonsData(@JsonAlias ("Season") Integer num,
                          @JsonAlias ("Episodes") List<EpisodesData> episodes) {
}
