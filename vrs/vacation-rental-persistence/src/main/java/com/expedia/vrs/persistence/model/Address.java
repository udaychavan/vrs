package com.expedia.vrs.persistence.model;

import lombok.Data;

/**
 * Created by uday.chavan on Mar, 2019
 */
@Data
public class Address {
    private String address;
    private String postalCode;
    private String countryCode;
    private String city;
    private String state;
    private String country;
}
