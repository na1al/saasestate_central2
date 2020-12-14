package com.saasestate.app.component.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.saasestate.app.component.parser.dto.Index;
import com.saasestate.app.entity.Source;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;

@Log4j2
public class Parser {

    private Source source;
    private ObjectMapper mapper;

    public Parser(Source source) throws IOException {
        this.source = source;
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    /**
     * @return
     * @throws IOException
     */
    public Index getIndex() throws IOException {

        File data = new File(source.getUrl());

        if (!data.isFile()) {
            throw new IllegalStateException("Source file not found");
        }

        JsonParser jsonParser = mapper.getFactory().createParser(data);
        return mapper.readValue(jsonParser, Index.class);
    }
}
