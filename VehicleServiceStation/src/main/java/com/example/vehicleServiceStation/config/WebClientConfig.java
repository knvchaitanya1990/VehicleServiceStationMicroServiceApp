package com.example.vehicleServiceStation.config;

import com.example.vehicleServiceStation.client.InsuranceVerificationClient;
import com.example.vehicleServiceStation.client.PoliceVerificationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;


    @Bean
    public WebClient policeVerificationWebClient() {
        return WebClient.builder()
                .baseUrl("http://PoliceVerification-Service")
                .filter(filterFunction)
                .build();
    }
    @Bean
    public PoliceVerificationClient policeVerificationClient() {
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(policeVerificationWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(PoliceVerificationClient.class);
    }




}
