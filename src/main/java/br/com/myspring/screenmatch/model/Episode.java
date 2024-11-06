package br.com.myspring.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {
    private Integer season;
    private String title;
    private Integer numEpisode;
    private Double rating;
    private LocalDate releaseDate;

    public Episode(Integer numSeason, EpisodesData dEpisode) {
        this.season = numSeason;
        this.title = dEpisode.title();
        this.numEpisode = dEpisode.numEpisode();

        try {
            this.rating = Double.valueOf(dEpisode.rating());
        } catch (NumberFormatException ex) {
            this.rating = 0.0;
        }

        try {
            this.releaseDate = LocalDate.parse(dEpisode.releaseDate());
        } catch (DateTimeParseException ex) {
            this.releaseDate = null;
        }
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumEpisode() {
        return numEpisode;
    }

    public void setNumEpisode(Integer numEpisode) {
        this.numEpisode = numEpisode;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return  "\nseason=" + season +
                ", title='" + title +
                ", numEpisode=" + numEpisode +
                ", rating=" + rating +
                ", releaseDate=" + releaseDate;
    }
}