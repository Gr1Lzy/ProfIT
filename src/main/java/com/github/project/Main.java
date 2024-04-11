package com.github.project;

import com.github.project.reader.JSONReader;
import com.github.project.writer.XMLWriter;

public class Main {
    public static void main(String[] args) {
        String path = "./src/main/resources/json/";

        JSONReader jsonReader = new JSONReader();
        XMLWriter xmlWriter = new XMLWriter();

        xmlWriter.writeByConditionWithValue(jsonReader.readAllFiles(path), MovieFilter.FILTER_BY_GENRE_COUNT, "3");
        xmlWriter.writeByCondition(jsonReader.readAllFiles(path), MovieFilter.STATISTIC_BY_GENRE);
        xmlWriter.writeByCondition(jsonReader.readAllFiles(path), MovieFilter.STATISTIC_BY_DIRECTOR);
    }
}