package com.lc.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon Server
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
    
    
    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate() {
    		RestTemplate restTemplate = new RestTemplate();
    		return restTemplate;
    }
}
