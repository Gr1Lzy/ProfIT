package com.github.project.writer;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.project.MovieFilter;
import com.github.project.exeption.MovieWriteException;
import com.github.project.model.Movie;
import com.github.project.model.xml.Item;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class XMLWriter {
    private static final String PATH = "./src/main/resources/xml/";
    private final XmlMapper xmlMapper = (XmlMapper) new XmlMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public void writeByCondition(List<Movie> movies, MovieFilter condition) {
        Map<Object, Long> statistic;

        switch (condition) {
            case STATISTIC_BY_GENRE ->
                    statistic = movies.stream()
                            .flatMap(movie -> movie.getGenres().stream().map(genre -> new AbstractMap.SimpleEntry<>(genre, 1L)))
                            .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey, Collectors.counting()));

            case STATISTIC_BY_DIRECTOR ->
                    statistic = movies.stream()
                            .collect(Collectors.groupingBy(
                                    movie -> movie.getDirector().getFullName(),
                                    Collectors.counting()));

            case STATISTIC_BY_YEAR_COUNT ->
                    statistic = movies.stream()
                            .collect(Collectors.groupingBy(Movie::getTitle, Collectors.counting()));

            default -> throw new IllegalStateException("Unexpected value: " + condition);
        }

        List<Item> items = statistic.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(entry -> new Item(entry.getKey(), entry.getValue()))
                .toList();

        try {
            xmlMapper.writeValue(new File(PATH + condition.name().toLowerCase() + ".xml"), items);
        } catch (IOException e) {
            throw new MovieWriteException("Cannot write movie ", e);
        }
    }

    public void writeByConditionWithValue(List<Movie> movies, MovieFilter condition, String value) {
        List<Movie> filteredMovies;

        switch (condition) {
            case FILTER_BY_YEAR_LESS ->
                    filteredMovies = movies.stream()
                            .filter(movie -> movie.getYear() < Integer.parseInt(value))
                            .toList();

            case FILTER_BY_YEAR_MORE ->
                    filteredMovies = movies.stream()
                            .filter(movie -> movie.getYear() > Integer.parseInt(value))
                            .toList();

            case FILTER_BY_DIRECTOR ->
                    filteredMovies = movies.stream()
                            .filter(movie -> movie.getDirector().getFullName().equals(value))
                            .toList();

            case FILTER_BY_GENRE_COUNT ->
                    filteredMovies = movies.stream()
                            .filter(movie -> movie.getGenres().size() == Integer.parseInt(value))
                            .toList();

            case FILTER_BY_GENRE_NAME ->
                    filteredMovies = movies.stream()
                            .filter(movie -> movie.getGenres().stream().anyMatch(genre -> genre.equals(value)))
                            .toList();

            default -> throw new IllegalStateException("Unexpected value: " + condition);
        }

        try {
            xmlMapper.writeValue(new File(PATH + condition.name().toLowerCase() + ".xml"), filteredMovies);
        } catch (IOException e) {
            throw new MovieWriteException("Cannot write movie ", e);
        }
    }
}
