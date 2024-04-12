package com.github.project.writer;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.project.MovieFilter;
import com.github.project.exeption.MovieWriteException;
import com.github.project.model.Movie;
import com.github.project.model.xml.Item;
import com.github.project.util.Filter;
import com.github.project.util.Statistic;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class XMLWriter {
    private static final String PATH = "./src/main/resources/xml/";
    private final XmlMapper xmlMapper = (XmlMapper) new XmlMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public void writeByCondition(List<Movie> movies, MovieFilter condition) {
        Map<String, Long> statisticInfo;
        Statistic statistic = new Statistic();

        switch (condition) {
            case STATISTIC_BY_GENRE -> statisticInfo = statistic.getByGenre(movies);

            case STATISTIC_BY_DIRECTOR -> statisticInfo = statistic.getByDirector(movies);

            default -> throw new IllegalStateException("Unexpected value: " + condition);
        }

        List<Item> items = statisticInfo.entrySet().stream()
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

        Filter filter = new Filter();

        switch (condition) {
            case FILTER_BY_YEAR_LESS -> filteredMovies = filter.filterByYearLess(movies, value);

            case FILTER_BY_YEAR_MORE -> filteredMovies = filter.filterByYearMore(movies, value);

            case FILTER_BY_DIRECTOR -> filteredMovies = filter.filterByDirector(movies, value);

            case FILTER_BY_GENRE_COUNT -> filteredMovies = filter.filterByGenreCount(movies, value);

            case FILTER_BY_GENRE_NAME -> filteredMovies = filter.filterByGenreName(movies, value);

            default -> throw new IllegalStateException("Unexpected value: " + condition);
        }

        try {
            xmlMapper.writeValue(new File(PATH + condition.name().toLowerCase() + ".xml"), filteredMovies);
        } catch (IOException e) {
            throw new MovieWriteException("Cannot write movie ", e);
        }
    }
}
