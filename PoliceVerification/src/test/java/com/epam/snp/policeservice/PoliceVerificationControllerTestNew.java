package com.epam.snp.policeservice;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PoliceVerificationControllerTestNew {


    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testEndpoint() {
      // Make an HTTP request to the endpoint
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/verify-theft//vehicleDB/653465478", String.class);
        // Perform assertions on the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
       // assertEquals(false, response.getBody().isVehicleNotInvolved());
    }


}
