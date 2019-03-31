package com.expedia.vrs.persistence.impl;

import com.expedia.vrs.persistence.IVRSDao;
import com.expedia.vrs.persistence.model.RentalProperty;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by uday.chavan on Mar, 2019
 */
public class IVRSDaoImpl implements IVRSDao {
    ConcurrentHashMap<UUID, RentalProperty> dataStore = new ConcurrentHashMap<>();

    @Override
    public RentalProperty fetchByPropertyId(UUID propertyId) {
        return dataStore.get(propertyId);
    }

    @Override
    public UUID persists(RentalProperty rentalProperty) {
        UUID uuid = UUID.randomUUID();
        rentalProperty.setId(uuid);
        dataStore.put(uuid, rentalProperty);
        return uuid;
    }

    @Override
    public RentalProperty removeByPropertyId(UUID propertyId) {
        return dataStore.remove(propertyId);
    }

    @Override
    public boolean exists(UUID propertyId) {
        return dataStore.containsKey(propertyId);
    }

    @Override
    public RentalProperty updateByPropertyId(UUID propertyId, RentalProperty rentalProperty) {
        return dataStore.put(propertyId, rentalProperty);
    }
}
