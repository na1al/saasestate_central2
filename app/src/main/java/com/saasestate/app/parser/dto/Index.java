package com.saasestate.app.parser.dto;

import com.saasestate.app.parser.ItemsCollection;
import lombok.Data;

import java.util.List;

@Data
public class Index {

    private String name;

    private List<ItemsCollection> links;
}
