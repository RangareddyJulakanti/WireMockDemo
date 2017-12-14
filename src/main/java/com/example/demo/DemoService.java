package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class DemoService {
    private final String demoServiceUrl;
    private final String demoServiceApiKey;
    private final RestTemplate restTemplate;
    @Autowired
    public DemoService(final RestTemplate restTemplate, final @Value("${demo.service.url}")String demoServiceUrl, @Value("${demo.service.api.key}")final String demoServiceApiKey) {
        this.demoServiceUrl = demoServiceUrl;
        this.demoServiceApiKey = demoServiceApiKey;
        this.restTemplate=restTemplate;
    }
    public Optional<DemoResponse> fetchResponse() {
        String url = String.format("%s/%s", demoServiceUrl, demoServiceApiKey);
        try {
            return Optional.ofNullable(restTemplate.getForObject(url, DemoResponse.class));
        } catch (RestClientException e) {
            return Optional.empty();
        }
    }
}
