package com.github.project.writer;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.project.MovieFilter;
import com.github.project.exeption.MovieWriteException;
import com.github.project.model.Movie;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLWriter implements Writer {
    private static final String PATH = "./src/main/resources/xml/";

    @Override
    public void write(List<Movie> movies) {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            xmlMapper.writeValue(new File(PATH + "movies.xml"), movies);
        } catch (IOException e) {
            throw new MovieWriteException("Cannot write value", e);
        }
    }

    public void writeByCondition(List<Movie> movies, MovieFilter condition, String value) {
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

            case FILTER_BY_GENRE ->
                    filteredMovies = movies.stream()
                            .filter(movie -> movie.getGenres().stream().anyMatch(genre -> genre.equals(value)))
                            .toList();

            default -> throw new IllegalStateException("Unexpected value: " + condition);
        }

        write(filteredMovies);


    }
}
