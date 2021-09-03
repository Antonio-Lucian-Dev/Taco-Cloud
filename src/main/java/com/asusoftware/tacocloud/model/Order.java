package com.asusoftware.tacocloud.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
public class Order {

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Street is required")
    private String street;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "State is required")
    private String state;
    @NotBlank(message = "Zip code is required")
    private String zip;
    @CreditCardNumber(message = "Not valid credit")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]| 1 [0-2]) ([)")
    private String ccExpiration;
    private String ccCVV;
}
