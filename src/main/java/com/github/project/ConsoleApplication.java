package com.github.project;

import com.github.project.reader.JSONParser;
import com.github.project.util.MovieFilter;
import com.github.project.writer.XMLWriter;

import java.util.Scanner;
import java.util.logging.Logger;

public class ConsoleApplication {
    private ConsoleApplication() {}

    public static void run() {
        Logger logger = Logger.getLogger(Main.class.getName());

        Scanner scanner = new Scanner(System.in);
        JSONParser jsonReader = new JSONParser();
        XMLWriter xmlWriter = new XMLWriter();

        logger.info("Enter the path( ./src/main/resources/json/ ):");
        String path = scanner.next();

        logger.info("Enter 1. Statistic only 2. Filter by value (write 1 or 2)");
        int choice = scanner.nextInt();
        if (choice == 1) {
            logger.info("Enter the filter (STATISTIC_BY_GENRE, STATISTIC_BY_DIRECTOR, STATISTIC_BY_YEAR):");
            String filter = scanner.next();
            xmlWriter.writeByCondition(jsonReader.readAllFiles(path), MovieFilter.valueOf(filter));
        } else if (choice == 2) {
            logger.info("Enter the filter (FILTER_BY_YEAR_LESS, FILTER_BY_YEAR_MORE, FILTER_BY_GENRE_NAME, " +
                    "FILTER_BY_GENRE_COUNT, FILTER_BY_DIRECTOR):");
            String filter = scanner.next();
            logger.info("Enter the value (example: 1970 or Robert):");
            String value = scanner.next();
            xmlWriter.writeByConditionWithValue(jsonReader.readAllFiles(path), MovieFilter.valueOf(filter), value);
        }
    }
}
