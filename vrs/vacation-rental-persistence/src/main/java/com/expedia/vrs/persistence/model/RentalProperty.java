package com.expedia.vrs.persistence.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by uday.chavan on Mar, 2019
 */
@Data
public class RentalProperty {

    private UUID id;

    @NotNull
    private Contact contact;

    @NotNull
    private Address address;

    @NotNull
    private Location location;
}
