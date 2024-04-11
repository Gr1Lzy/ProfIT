package com.github.project;

import com.github.project.reader.JSONReader;
import com.github.project.writer.XMLWriter;

public class Main {
    public static void main(String[] args) {
        JSONReader jsonReader = new JSONReader();
        XMLWriter xmlWriter = new XMLWriter();

        xmlWriter.writeByCondition(jsonReader.readAllFiles(), MovieFilter.FILTER_BY_GENRE_COUNT, "3");
    }
}