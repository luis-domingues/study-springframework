package br.com.myspring.screenmatch.main;

import br.com.myspring.screenmatch.model.Episode;
import br.com.myspring.screenmatch.model.EpisodesData;
import br.com.myspring.screenmatch.model.SeasonsData;
import br.com.myspring.screenmatch.model.SeriesData;
import br.com.myspring.screenmatch.service.ApiConsumption;
import br.com.myspring.screenmatch.service.DataConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private ApiConsumption apiConsumption = new ApiConsumption ();
    private DataConverter dataConverter = new DataConverter ();
    private Scanner scanner = new Scanner (System.in);
    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=d1a73fd4";

    public void display() {
        System.out.print ("Procurar série: ");
        var searchedTitle = scanner.nextLine ();
        var json = apiConsumption.getData (ADDRESS + searchedTitle.replace (" ", "+") + API_KEY);
        SeriesData data = dataConverter.getData (json, SeriesData.class);
        System.out.println (data);

        List<SeasonsData> seasons = new ArrayList<> ();
		for (int i = 1; i <= data.totalSeasons (); i++) {
			json = apiConsumption.getData (ADDRESS + searchedTitle.replace (" ", "+") + "&season=" + i + API_KEY);
			SeasonsData seasonsData = dataConverter.getData (json, SeasonsData.class);
			seasons.add (seasonsData);
		}
		seasons.forEach (System.out::println);

        seasons.forEach (s -> s.episodes ().forEach (e -> System.out.println (e.title ())));

        List<EpisodesData> episodesData = seasons.stream()
                .flatMap(s -> s.episodes().stream())
                .collect(Collectors.toList());

        System.out.println("\nTop 5 episódios");
        episodesData.stream()
                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodesData::rating).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episode> episodes = seasons.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(d -> new Episode(d.numEpisode(), d))
                ).collect(Collectors.toList());

        episodes.forEach(System.out::println);

        System.out.print("\nAssitir a partir de um ano específico: ");
        var year = scanner.nextInt();
        scanner.nextLine();
        LocalDate searchDate = LocalDate.of(year, 1, 1);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodes.stream()
                .filter(e -> e.getReleaseDate() != null && e.getReleaseDate().isAfter(searchDate))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getSeason()
                        + " Episódio: " + e.getTitle()
                        + " | Lançado em: " + e.getReleaseDate().format(df)
                ));
    }
}
