package com.expedia.vrs.persistence;

import com.expedia.vrs.persistence.model.RentalProperty;

import java.util.UUID;

/**
 * Created by uday.chavan on Mar, 2019
 */
public interface IVRSDao {

    RentalProperty fetchByPropertyId(UUID propertyId);

    UUID persists(RentalProperty rentalProperty);

    RentalProperty removeByPropertyId(UUID propertyId);

    boolean exists(UUID propertyId);

    RentalProperty updateByPropertyId(UUID propertyId, RentalProperty rentalProperty);

}
