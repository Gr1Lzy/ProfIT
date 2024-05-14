package com.github.project.model.wrapper;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.github.project.model.Movie;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

@JacksonXmlRootElement(localName = "filter")
@AllArgsConstructor
@ToString
public class FilterWrapper {
    @JacksonXmlProperty(localName = "movie")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Movie> movies;
}
