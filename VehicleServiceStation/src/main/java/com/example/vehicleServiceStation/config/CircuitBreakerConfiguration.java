package com.example.vehicleServiceStation.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfiguration {

    @Bean
    public CircuitBreakerConfig getCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(5000))
                .permittedNumberOfCallsInHalfOpenState(2)
                .slidingWindowSize(5)
                .build();
    }

    @Bean
    public CircuitBreaker getCircuitBreaker(CircuitBreakerConfig circuitBreakerConfig) {
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
        return circuitBreakerRegistry.circuitBreaker("myCircuitBreaker");
    }
}
