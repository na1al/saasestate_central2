package com.saasestate.app.component.geo;

import com.saasestate.app.component.geo.dto.Address;
import com.saasestate.app.component.geo.dto.Response;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GeoApi {

    GeoClient client;

    public GeoApi(GeoClient client) {
        this.client = client;
    }

    public Response<Address[]> reverse(Double lat, Double lng) {
        String url = client.getUrl() + String.format("/api/reverse?lat=%s&lng=%s", lat, lng);
        ResponseEntity<Response<Address[]>> actualExample = client.getRestTemplate().exchange(url, HttpMethod.GET, null, new   ParameterizedTypeReference<Response<Address[]>>() {});
        return actualExample.getBody();
    }

}
