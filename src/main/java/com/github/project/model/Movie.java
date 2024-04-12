package com.github.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @JacksonXmlProperty(localName = "title")
    private String title;

    @JacksonXmlProperty(localName = "year")
    private Integer year;

    @JacksonXmlElementWrapper(localName = "genres")
    @JacksonXmlProperty(localName = "genre")
    private List<String> genres;

    @JacksonXmlProperty(localName = "director")
    private Director director;

    public boolean isValid() {
        return title != null && !title.isEmpty() &&
                year != null &&
                genres != null && !genres.isEmpty() &&
                director != null;
    }
}
