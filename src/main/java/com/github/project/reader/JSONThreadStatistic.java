package com.github.project.reader;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class JSONThreadStatistic {
    private final JSONParser jsonParser = new JSONParser();
    private final JSONFileFinder jsonFileFinder = new JSONFileFinder();
    private final Logger logger = Logger.getLogger(JSONThreadStatistic.class.getName());

    public static void main(String[] args) {
        String path = "./src/main/resources/json/";
        int[] threadCounts = {1, 2, 4, 8};

        JSONThreadStatistic jsonThreadStatistic = new JSONThreadStatistic();
        jsonThreadStatistic.start(path, threadCounts);
    }

    public void start(String path, int[] threadCounts) {
        List<File> files = jsonFileFinder.getAllFiles(path);

        for (int threadCount : threadCounts) {
            parseFilesWithThreadPool(files, threadCount);
        }
    }

    private void parseFilesWithThreadPool(List<File> files, int threadCount) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        double startTime = System.nanoTime();
        for (File file : files) {
            executor.execute(() -> jsonParser.read(file));
        }
        double endTime = System.nanoTime();

        double result = (endTime - startTime) / 1e6;

        logger.info(String.format("Time taken with %d threads: %.4f milliseconds", threadCount, result));

        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                logger.warning("Some tasks were not terminated");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
