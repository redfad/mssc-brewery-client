package com.example.msscbreweryclient.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import web.model.CustomerDto;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.customer",ignoreUnknownFields = false)
public class CustomerClient {
    private final String PATH_V1 = "/api/v1/customer/";
    private String apiHost;

    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID uuid){
        return restTemplate.getForObject(apiHost+PATH_V1+uuid.toString(),CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto CustomerDto){
        return restTemplate.postForLocation(apiHost+PATH_V1,CustomerDto);
    }

    public void updateCustomer(UUID uuid,CustomerDto CustomerDto){
        restTemplate.put(apiHost+PATH_V1+uuid,CustomerDto);
    }

    public void deleteCustomer(UUID uuid){
        restTemplate.delete(apiHost+PATH_V1+uuid);
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
}
