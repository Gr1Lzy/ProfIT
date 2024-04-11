package com.github.project.reader;

import com.github.project.model.Movie;

import java.io.File;
import java.util.List;

public interface Reader {
    List<Movie> read(File file);
}
