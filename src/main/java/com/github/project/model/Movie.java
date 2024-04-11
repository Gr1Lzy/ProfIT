package com.github.project.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JacksonXmlRootElement(localName = "movie")
public class Movie {

    @JacksonXmlProperty(localName = "title")
    private String title;

    @JacksonXmlProperty(localName = "year")
    private int year;

    @JacksonXmlProperty(localName = "genres")
    private List<String> genres;

    @JacksonXmlProperty(localName = "director")
    private Director director;
}
