package com.github.project.reader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.project.model.Movie;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JSONFileFinder jsonFileFinder = new JSONFileFinder();

    public List<Movie> readAllFiles(String path) {
        List<Movie> movies = new ArrayList<>();

        for (File file : jsonFileFinder.getAllFiles(path)) {
            movies.addAll(read(file));
        }

        return movies;
    }

    public List<Movie> read(File file) {
        List<Movie> movies = new ArrayList<>();

        try (MappingIterator<Movie> iterator = objectMapper.readerFor(Movie.class).readValues(file)) {
            while (iterator.hasNext()) {
                movies.add(iterator.next());
            }
        } catch (IOException e) {
            throw new FileReadException("Cannot read value", e);
        }

        return movies;
    }
}
