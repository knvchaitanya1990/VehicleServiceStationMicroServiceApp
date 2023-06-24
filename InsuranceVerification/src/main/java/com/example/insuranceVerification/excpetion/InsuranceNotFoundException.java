package com.example.insuranceVerification.excpetion;

public class InsuranceNotFoundException extends RuntimeException {


    String message;

    public InsuranceNotFoundException(String message){
        super(message);
        this.message=message;
    }

    public InsuranceNotFoundException(){


    }

}
