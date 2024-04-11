package com.github.project.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.project.util.DirectorDeserializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Movie {
    private String title;
    private int year;
    private String[] genre;

    @JsonDeserialize(using = DirectorDeserializer.class)
    private Director director;
}
