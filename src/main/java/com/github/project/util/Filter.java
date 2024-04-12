package com.github.project.util;

import com.github.project.model.Movie;

import java.util.List;

public class Filter {

    public List<Movie> filterByYearLess(List<Movie> movies, String value) {
        return movies.stream()
                .filter(movie -> movie.getYear() < Integer.parseInt(value))
                .toList();
    }

    public List<Movie> filterByYearMore(List<Movie> movies, String value) {
        return movies.stream()
                .filter(movie -> movie.getYear() > Integer.parseInt(value))
                .toList();
    }

    public List<Movie> filterByDirector(List<Movie> movies, String value) {
        return movies.stream()
                .filter(movie -> movie.getDirector().getFullName().equals(value))
                .toList();
    }
    public List<Movie> filterByGenreCount(List<Movie> movies, String value) {
        return movies.stream()
                .filter(movie -> movie.getGenres().size() == Integer.parseInt(value))
                .toList();
    }
    public List<Movie> filterByGenreName(List<Movie> movies, String value) {
        return movies.stream()
                .filter(movie -> movie.getGenres().stream().anyMatch(genre -> genre.equals(value)))
                .toList();
    }
}
