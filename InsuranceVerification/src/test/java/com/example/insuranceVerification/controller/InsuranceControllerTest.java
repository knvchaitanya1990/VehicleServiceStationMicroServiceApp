package com.example.insuranceVerification.controller;


import com.example.insuranceVerification.controller.InsuranceController;
import com.example.insuranceVerification.model.Insurance;
import com.example.insuranceVerification.service.InsuranceService;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.when;
/*
@WebMvcTest(InsuranceController.class)
public class InsuranceControllerTest {

    @Mock
    private InsuranceService insuranceService;

    @InjectMocks
    private InsuranceController insuranceController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    private void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetInsuranceByRegistrationNumber() throws Exception {
        String registrationNumber ="TS09454";
        Insurance insurance = new Insurance();
        insurance.setRegistrationNumber(registrationNumber);
        insurance.setInsuranceProvider("hdfc_ergo");
        when(insuranceService.getInsuranceByRegistrationNumber(registrationNumber)).thenReturn(Optional.of(insurance));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/verify-insurance/{registrationNumber}",registrationNumber)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.registrationNumber").value(registrationNumber))
                .andExpect(MockMvcResultMatchers.jsonPath("$.setInsuranceProvider").value("hdfc_ergo"));

    }


}

 */