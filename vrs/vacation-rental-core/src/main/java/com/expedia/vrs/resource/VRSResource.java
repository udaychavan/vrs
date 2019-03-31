package com.expedia.vrs.resource;


import com.expedia.vrs.exception.VRSServiceException;
import com.expedia.vrs.persistence.model.RentalProperty;
import com.expedia.vrs.service.IVacationRentalService;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.UUID;


@Path("vrs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class VRSResource {

    private IVacationRentalService vacationRentalService;
    @Inject
    public VRSResource(IVacationRentalService vacationRentalService){
        this.vacationRentalService = vacationRentalService;
    }


    @GET
    @Path("/listings/{propertyId}")
    public Response fetch(@PathParam("propertyId") UUID propertyId) throws VRSServiceException {
        try {
            log.info("Fetching property details for propertyId: {}", propertyId);
            RentalProperty rentalProperty = vacationRentalService.fetchRentalProperty(propertyId);
            if(rentalProperty == null){
                log.error("Property does not exists for propertyId : {}", propertyId);
                throw new VRSServiceException(VRSServiceException.ErrorCode.not_found, "Property does not exists");
            }

            log.info("Successfully fetched property details for propertyId: {}, PropertyDetails: {}", propertyId, rentalProperty);
            return  Response.status(Response.Status.OK).entity(rentalProperty).build();
        } catch (VRSServiceException vrsServiceException) {
            throw vrsServiceException;
        }
    }

    @POST
    @Path("/listings")
    public Response add(@Valid RentalProperty rentalProperty) throws VRSServiceException {
        try{
            log.info("Adding new property in listings. Property: {}", rentalProperty);
            UUID propertyId = vacationRentalService.addRentalProperty(rentalProperty);
            HashMap<String, String> responseEntity = new HashMap<>();
            responseEntity.put("propertyId", propertyId.toString());
            return  Response.status(Response.Status.OK).entity(responseEntity).build();
        } catch (VRSServiceException ex){
            throw ex;
        }
    }

    @PUT
    @Path("/listings/{propertyId}")
    public Response update(@PathParam("propertyId")  UUID propertyId, @Valid RentalProperty rentalProperty) throws VRSServiceException {
        try {
            log.info("Updating property details for propertyId:{}", propertyId);
            return  Response.status(Response.Status.OK).entity(vacationRentalService.updateRentalPropertyById(rentalProperty, propertyId)).build();
        } catch (VRSServiceException vrsServiceException) {
            throw vrsServiceException;
        }
    }

    @DELETE
    @Path("/listings/{propertyId}")
    public Response delete(@PathParam("propertyId") UUID propertyId) throws VRSServiceException {
        try {
            log.info("Removing property from listing. PropertyId: {}", propertyId);
            return Response.status(Response.Status.OK).entity(vacationRentalService.removeRentalPropertyById(propertyId)).build();
        } catch (VRSServiceException vrsServiceException) {
            throw vrsServiceException;
        }
    }

}
