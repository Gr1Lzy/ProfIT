package com.github.project;

import com.github.project.model.Movie;
import com.github.project.reader.JSONParser;
import com.github.project.reader.JSONThreadStatistic;
import com.github.project.writer.XMLWriter;
import org.jetbrains.annotations.TestOnly;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static com.github.project.util.MovieFilter.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XMLWriterTest {
    private final JSONParser jsonParser = new JSONParser();
    private XMLWriter xmlWriter;
    private List<Movie> movieList;

    @BeforeEach
    void setUp() {
        xmlWriter = new XMLWriter();
        movieList = jsonParser.readAllFiles("src/main/resources/json");
    }

    @Test
    void testThreadStatistic() {
        String path = "./src/main/resources/json/";
        int[] threadCounts = {1, 2, 4, 8};

        JSONThreadStatistic jsonThreadStatistic = new JSONThreadStatistic();
        jsonThreadStatistic.start(path, threadCounts);
    }

    @Test
    void testCreateStatisticXML() {
        xmlWriter.writeByCondition(movieList, STATISTIC_BY_GENRE);
        xmlWriter.writeByCondition(movieList, STATISTIC_BY_DIRECTOR);
        xmlWriter.writeByCondition(movieList, STATISTIC_BY_YEAR);

        assertTrue(new File("src/main/resources/xml/statistic_by_director.xml").exists());
        assertTrue(new File("src/main/resources/xml/statistic_by_genre.xml").exists());
        assertTrue(new File("src/main/resources/xml/statistic_by_year.xml").exists());
    }

    @Test
    void invalidJsonFileWriteToXML() {
        movieList = jsonParser.read(new File("src/main/resources/json/invalid.json"));

        xmlWriter.writeByCondition(movieList, STATISTIC_BY_GENRE);
        xmlWriter.writeByCondition(movieList, STATISTIC_BY_DIRECTOR);
        xmlWriter.writeByCondition(movieList, STATISTIC_BY_YEAR);

        assertTrue(new File("src/main/resources/xml/statistic_by_director.xml").exists());
        assertTrue(new File("src/main/resources/xml/statistic_by_genre.xml").exists());
        assertTrue(new File("src/main/resources/xml/statistic_by_year.xml").exists());
    }

    @Test
    void testCreateFilterWithValueXML() {
        xmlWriter.writeByConditionWithValue(movieList, FILTER_BY_YEAR_LESS, "2000");
        xmlWriter.writeByConditionWithValue(movieList, FILTER_BY_YEAR_MORE, "2000");
        xmlWriter.writeByConditionWithValue(movieList, FILTER_BY_GENRE_NAME, "Drama");
        xmlWriter.writeByConditionWithValue(movieList, FILTER_BY_GENRE_COUNT, "3");
        xmlWriter.writeByConditionWithValue(movieList, FILTER_BY_DIRECTOR, "Christopher Nolan");

        assertTrue(new File("src/main/resources/xml/filter_by_year_less.xml").exists());
        assertTrue(new File("src/main/resources/xml/filter_by_year_more.xml").exists());
        assertTrue(new File("src/main/resources/xml/filter_by_genre_name.xml").exists());
        assertTrue(new File("src/main/resources/xml/filter_by_genre_count.xml").exists());
        assertTrue(new File("src/main/resources/xml/filter_by_director.xml").exists());
    }
}

