package com.expedia.vrs.service;

import com.expedia.vrs.exception.VRSServiceException;
import com.expedia.vrs.persistence.model.RentalProperty;

import java.util.UUID;

/**
 * Created by uday.chavan on Mar, 2019
 */
public interface IVacationRentalService {

    RentalProperty fetchRentalProperty(UUID propertyId) throws VRSServiceException;

    UUID addRentalProperty(RentalProperty rentalProperty) throws VRSServiceException;

    RentalProperty updateRentalPropertyById(RentalProperty rentalProperty, UUID propertyId) throws VRSServiceException;

    RentalProperty removeRentalPropertyById(UUID propertyId) throws VRSServiceException;

}
