package com.github.project;

import com.github.project.model.Movie;
import com.github.project.reader.JSONReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        JSONReader jsonReader = new JSONReader();
        List<Movie> movies = jsonReader.readAllFiles();
        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }
    }
}