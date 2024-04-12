package com.github.project.util;

import com.github.project.model.Movie;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statistic {
    public Map<String, Long> getByGenre(List<Movie> movies) {
        return movies.stream()
                .flatMap(movie -> movie.getGenres().stream().map(genre -> new AbstractMap.SimpleEntry<>(genre, 1L)))
                .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey, Collectors.counting()));
    }

    public Map<String, Long> getByDirector(List<Movie> movies) {
        return  movies.stream()
                .collect(Collectors.groupingBy(
                        movie -> movie.getDirector().getFullName(),
                        Collectors.counting()));
    }
}
