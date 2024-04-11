package com.github.project.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Director {
    @JacksonXmlProperty(localName = "name")
    String name;

    @JacksonXmlProperty(localName = "surname")
    String surname;

    public Director(String fullName) {
        parse(fullName);
    }

    private void parse(String fullName) {
        String [] names = fullName.split(" ");
        if (names.length < 1) {
            throw new IllegalArgumentException("Wrong name");
        }

        this.name = names[0];
        this.surname = names[1];
    }
}