package com.saasestate.app.component.parser.dto;

import com.saasestate.app.component.parser.ItemsCollection;
import lombok.Data;

import java.util.List;

@Data
public class Index {

    private String name;

    private List<ItemsCollection> links;
}
