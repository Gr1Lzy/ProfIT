package com.github.project.model.wrapper;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.github.project.model.xml.Item;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

@JacksonXmlRootElement(localName = "statistics")
@AllArgsConstructor
@ToString
public class StatisticWrapper {
    @JacksonXmlProperty(localName = "item")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<Item> items;
}
