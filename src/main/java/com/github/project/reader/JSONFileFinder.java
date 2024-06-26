package com.github.project.reader;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class JSONFileFinder {
    public List<File> getAllFiles(String path) {
        File directory = new File(path);

        if (!directory.exists() || !directory.isDirectory()) {
            throw new InvalidDirectoryException("Invalid directory: " + path);
        }

        return List.of((Objects.requireNonNull(directory.listFiles())));
    }
}
