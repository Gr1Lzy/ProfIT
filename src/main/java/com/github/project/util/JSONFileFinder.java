package com.github.project.util;

import com.github.project.exeption.InvalidDirectoryException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JSONFileFinder {
    public List<String> findAll(String directoryPath) {
        List<String> jsonFiles = new ArrayList<>();

        File[] files = getByDirectory(directoryPath).listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".json")) {
                    jsonFiles.add(file.getAbsolutePath());
                }
            }
        }
        return jsonFiles;
    }

    private File getByDirectory(String directoryPath) {
        File node = new File(directoryPath);

        if (!node.exists() || !node.isDirectory()) {
            throw new InvalidDirectoryException("No such directory");
        }

        return node;
    }
}
