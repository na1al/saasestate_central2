package com.saasestate.app.config;

import com.saasestate.app.component.geo.GeoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public GeoClient geoClient(@Value("${geo.url}") String url, @Value("${geo.secret}") String secret) {
        return new GeoClient(url, secret);
    }

}
