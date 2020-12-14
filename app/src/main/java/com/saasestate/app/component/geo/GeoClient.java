package com.saasestate.app.component.geo;

import lombok.Getter;
import org.springframework.web.client.RestTemplate;

public class GeoClient {

    @Getter
    private String url;
    private String secret;

    @Getter
    private RestTemplate restTemplate;

    public GeoClient(String url, String secret){
        this.url = url;
        this.secret = secret;
        this.restTemplate = new RestTemplate();
    }


}
