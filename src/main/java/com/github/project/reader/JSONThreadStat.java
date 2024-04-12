package com.github.project.reader;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JSONThreadStat {
    private final JSONParser jsonParser = new JSONParser();
    private final JSONFileFinder jsonFileFinder = new JSONFileFinder();

    public static void main(String[] args) {
        String path = "./src/main/resources/json/";
        int[] threadCounts = {1, 2, 4, 8};

        JSONThreadStat jsonThreadStat = new JSONThreadStat();
        jsonThreadStat.start(path, threadCounts);
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
    }
}
