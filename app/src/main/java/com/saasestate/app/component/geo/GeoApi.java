package com.saasestate.app.component.geo;

import com.saasestate.app.component.geo.dto.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GeoApi {

    GeoClient client;

    public GeoApi(GeoClient client) {
        this.client = client;
    }

    public Address[] reverse(Double lat, Double lng) {
        String url = client.getUrl() + String.format("/api/reverse?lat=%s&lng=%s", lat, lng);
        ResponseEntity<Address[]> response = client.getRestTemplate().getForEntity(url, Address[].class);
        return response.getBody();
    }

}
