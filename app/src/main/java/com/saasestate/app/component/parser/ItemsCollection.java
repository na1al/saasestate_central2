package com.saasestate.app.component.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.saasestate.app.component.parser.dto.Item;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Iterator;


public class ItemsCollection  implements Iterable<Item> {

    @Setter
    private String name;

    @Setter
    private String url;

    private ObjectMapper mapper;
    private JsonParser jsonParser;

    public ItemsCollection() {
        super();
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @SneakyThrows
    @Override
    public Iterator<Item> iterator() {

        File data = new File(url);

        if (!data.isFile()) {
            throw new IllegalStateException("Source item file not found");
        }

        jsonParser = mapper.getFactory().createParser(data);

        if (jsonParser.nextToken() != JsonToken.START_ARRAY) {
            throw new IllegalStateException("Expected content to be an array");
        }

        return new Iterator<Item>() {
            @SneakyThrows
            @Override
            public boolean hasNext() {
                return jsonParser.nextToken() != JsonToken.END_ARRAY;
            }

            @SneakyThrows
            @Override
            public Item next() {
                return mapper.readValue(jsonParser, Item.class);
            }
        };
    }
}
