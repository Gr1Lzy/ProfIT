package com.github.project.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.project.model.Director;

import java.io.IOException;

public class DirectorDeserializer extends JsonDeserializer<Director> {

    @Override
    public Director deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return new Director(jsonParser.getText());
    }
}
