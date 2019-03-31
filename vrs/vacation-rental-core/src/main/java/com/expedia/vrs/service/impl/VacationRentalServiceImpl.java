package com.expedia.vrs.service.impl;

import com.expedia.vrs.exception.VRSServiceException;
import com.expedia.vrs.persistence.model.RentalProperty;
import com.expedia.vrs.persistence.IVRSDao;
import com.expedia.vrs.service.IVacationRentalService;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * Created by uday.chavan on Mar, 2019
 */
@Slf4j
public class VacationRentalServiceImpl implements IVacationRentalService {

    @Inject
    IVRSDao ivrsDao;

    //ConcurrentHashMap<UUID, RentalProperty> dataStore = new ConcurrentHashMap<>();

    @Override
    public RentalProperty fetchRentalProperty(UUID propertyId) throws VRSServiceException {
       try{
           return ivrsDao.fetchByPropertyId(propertyId);
        } catch (Exception ex){
            log.error("Exception occurred while fetching property details. PropertyId: {}", propertyId);
            throw new VRSServiceException(VRSServiceException.ErrorCode.unknown_error, ex.getMessage());
        }
    }

    @Override
    public UUID addRentalProperty(RentalProperty rentalProperty) throws VRSServiceException {
        try{
            return ivrsDao.persists(rentalProperty);
        } catch(Exception ex){
            log.error("Exception occurred while adding new property. Property:{}", rentalProperty);
            throw new VRSServiceException(VRSServiceException.ErrorCode.unknown_error, ex.getMessage());
        }

    }

    @Override
    public RentalProperty updateRentalPropertyById(RentalProperty rentalProperty, UUID propertyId) throws VRSServiceException {

        try{
            if(!ivrsDao.exists(propertyId)){
                log.error("Property does not exists. PropertyId:{}", propertyId);
                throw new VRSServiceException(VRSServiceException.ErrorCode.not_found, "Property does not exists");
            }
            rentalProperty.setId(propertyId);
            return ivrsDao.updateByPropertyId(propertyId, rentalProperty);

        } catch (VRSServiceException ex){
            throw ex;
        } catch (Exception ex){
            log.error("Exception occurred while updating property. PropertyId:{}", propertyId);
            throw new VRSServiceException(VRSServiceException.ErrorCode.unknown_error, ex.getMessage());
        }

    }

    @Override
    public RentalProperty removeRentalPropertyById(UUID propertyId) throws VRSServiceException {

        try{
            if(!ivrsDao.exists(propertyId)){
                log.error("Property does not exists. PropertyId:{}", propertyId);
                throw new VRSServiceException(VRSServiceException.ErrorCode.not_found, "Property does not exists");
            }

            return ivrsDao.removeByPropertyId(propertyId);
        } catch (VRSServiceException ex){
            throw ex;
        } catch(Exception ex){
            log.error("Exception occurred while removing property. PropertyId:{}", propertyId);
            throw new VRSServiceException(VRSServiceException.ErrorCode.unknown_error, ex.getMessage());
        }
    }


}
