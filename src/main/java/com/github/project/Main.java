package com.github.project;

import com.github.project.reader.JSONReader;
import com.github.project.writer.XMLWriter;

public class Main {
    public static void main(String[] args) {
        JSONReader jsonReader = new JSONReader();
        XMLWriter xmlWriter = new XMLWriter();

        xmlWriter.write(jsonReader.readAllFiles());
    }
}