package com.github.project.reader;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JSONThreadStatistic {
    private final JSONParser jsonParser = new JSONParser();
    private final JSONFileFinder jsonFileFinder = new JSONFileFinder();

    public static void main(String[] args) {
        String path = "./src/main/resources/json/";
        int[] threadCounts = {1, 2, 4, 8};

        JSONThreadStatistic jsonThreadStatistic = new JSONThreadStatistic();
        jsonThreadStatistic.start(path, threadCounts);
    }

    public void start(String path, int[] threadCounts) {
        List<File> files = jsonFileFinder.getAllFiles(path);

        for (int threadCount : threadCounts) {
            long startTime = System.nanoTime();
            parseFilesWithThreadPool(files, threadCount);
            long endTime = System.nanoTime();

            System.out.println("Time taken with " + threadCount + " threads: " + (endTime - startTime) / 1e6 + " milliseconds");
        }
    }

    private void parseFilesWithThreadPool(List<File> files, int threadCount) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (File file : files) {
            executor.execute(() -> jsonParser.read(file));
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)) {
                System.out.println("Some tasks were not terminated");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
