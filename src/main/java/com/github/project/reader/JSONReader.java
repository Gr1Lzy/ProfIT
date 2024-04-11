package com.github.project.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.project.exeption.MovieReadException;
import com.github.project.model.Movie;
import com.github.project.util.JSONFileFinder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONReader implements Reader {
    private static final String PATH = "./src/main/resources/json/";
    JSONFileFinder jsonFileFinder = new JSONFileFinder();

    public List<Movie> readAllFiles() {
        List<Movie> movies = new ArrayList<>();
        List<String> fileNames = jsonFileFinder.findAll(PATH);

        for (String fileName : fileNames) {
            movies.addAll(read(new File(fileName)));
        }

        return movies;
    }

    @Override
    public List<Movie> read(File file) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            TypeReference<List<Movie>> typeReference = new TypeReference<>() {};
            return objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            throw new MovieReadException("Cannot read value", e);
        }
    }
}