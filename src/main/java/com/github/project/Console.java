package com.github.project;

import com.github.project.reader.JSONReader;
import com.github.project.writer.XMLWriter;

import java.util.Scanner;
import java.util.logging.Logger;

public class Console {
    private Console() {}

    public static void run() {
        Logger logger = Logger.getLogger(Main.class.getName());

        Scanner scanner = new Scanner(System.in);
        JSONReader jsonReader = new JSONReader();
        XMLWriter xmlWriter = new XMLWriter();

        logger.info("Enter the path (empty = default path):");
        String path = scanner.nextLine();
        if (path.isEmpty()) {
            path = "./src/main/resources/json/";
        }

        logger.info("Enter 1. Statistic only 2. Filter by value");
        int choice = scanner.nextInt();
        if (choice == 1) {
            logger.info("Enter the filter (STATISTIC_BY_GENRE, STATISTIC_BY_DIRECTOR, STATISTIC_BY_YEAR):");
            String filter = scanner.next();
            xmlWriter.writeByCondition(jsonReader.readAllFiles(path), MovieFilter.valueOf(filter));
        } else if (choice == 2) {
            logger.info("Enter the filter (FILTER_BY_YEAR_LESS, FILTER_BY_YEAR_MORE, FILTER_BY_GENRE_NAME, FILTER_BY_GENRE_COUNT, FILTER_BY_DIRECTOR):");
            String filter = scanner.next();
            logger.info("Enter the value:");
            String value = scanner.next();
            xmlWriter.writeByConditionWithValue(jsonReader.readAllFiles(path), MovieFilter.valueOf(filter), value);
        }
    }
}
