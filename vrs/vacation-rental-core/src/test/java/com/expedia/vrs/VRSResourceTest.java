package com.expedia.vrs;


import com.expedia.vrs.exception.VRSServiceException;
import com.expedia.vrs.module.VRSModule;
import com.expedia.vrs.persistence.model.Address;
import com.expedia.vrs.persistence.model.Contact;
import com.expedia.vrs.persistence.model.Location;
import com.expedia.vrs.persistence.model.RentalProperty;
import com.expedia.vrs.persistence.module.VRSPersistenceModule;
import com.expedia.vrs.resource.VRSResource;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;

/**
 * Created by uday.chavan on Mar, 2019
 */
public class VRSResourceTest {

    private Injector injector;

    VRSResource vrsResource;

    String propertyId;

    @Before
    public void setup(){

        injector = Guice.createInjector(
                new VRSPersistenceModule(),
                new VRSModule(null));

        vrsResource = injector.getInstance(VRSResource.class);


        RentalProperty rentalProperty = new RentalProperty();
        Contact contact = new Contact();
        contact.setFormattedPhone("1234213");
        contact.setPhone("134123412");
        Address address = new Address();
        Location location = new Location();
        rentalProperty.setContact(contact);
        rentalProperty.setAddress(address);
        rentalProperty.setLocation(location);

        Response response = null;
        try {
            response = vrsResource.add(rentalProperty);
        } catch (VRSServiceException e) {
        }
        Map<String, String> rEntity = (Map<String, String>)response.getEntity();
        propertyId = rEntity.get("propertyId");
    }

    @Test(expected = VRSServiceException.class)
    public void testPropertyFetchWithInvalidPropertyId() throws VRSServiceException {
        vrsResource.fetch(UUID.randomUUID());
    }

    @Test(expected = VRSServiceException.class)
    public void testPropertyFetchWithNullPropertyId() throws VRSServiceException {
        vrsResource.fetch(null);
    }

    @Test
    public void testPropertyFetchWithValidPropertyId() throws VRSServiceException {
        Response fetchResult = vrsResource.fetch(UUID.fromString(propertyId));
        RentalProperty resultEntity =  (RentalProperty)fetchResult.getEntity();
        Assert.assertEquals(UUID.fromString(propertyId), resultEntity.getId());

    }

    @Test
    public void testPropertyAddWithValidProperty() throws VRSServiceException {
        RentalProperty rentalProperty = new RentalProperty();
        Contact contact = new Contact();
        contact.setFormattedPhone("1234213");
        contact.setPhone("134123412");
        Address address = new Address();
        Location location = new Location();
        rentalProperty.setContact(contact);
        rentalProperty.setAddress(address);
        rentalProperty.setLocation(location);

        Response response = vrsResource.add(rentalProperty);
        Map<String, String> rEntity = (Map<String, String>)response.getEntity();
        String propertyId1 = rEntity.get("propertyId");
        Response fetchResult = vrsResource.fetch(UUID.fromString(propertyId1));
        RentalProperty resultEntity =  (RentalProperty)fetchResult.getEntity();
        Assert.assertEquals(UUID.fromString(propertyId1), resultEntity.getId());
    }

    @Test
    public void testPropertyUpdateForValidPropertyPresentInListing() throws VRSServiceException {
        RentalProperty rentalProperty = new RentalProperty();
        Contact contact = new Contact();
        contact.setFormattedPhone("111");
        contact.setPhone("134123412");
        Address address = new Address();
        Location location = new Location();
        rentalProperty.setContact(contact);
        rentalProperty.setAddress(address);
        rentalProperty.setLocation(location);

        vrsResource.update(UUID.fromString(propertyId), rentalProperty);
        Response fetchResult = vrsResource.fetch(UUID.fromString(propertyId));
        RentalProperty resultEntity =  (RentalProperty)fetchResult.getEntity();
        Assert.assertEquals(resultEntity.getContact().getPhone(), rentalProperty.getContact().getPhone());
    }

    @Test(expected = VRSServiceException.class)
    public void testPropertyUpdateForPropertyNotPresentInListing() throws VRSServiceException {
        RentalProperty rentalProperty = new RentalProperty();
        Contact contact = new Contact();
        contact.setFormattedPhone("111");
        contact.setPhone("134123412");
        Address address = new Address();
        Location location = new Location();
        rentalProperty.setContact(contact);
        rentalProperty.setAddress(address);
        rentalProperty.setLocation(location);
        vrsResource.update(UUID.randomUUID(), rentalProperty);
    }


    @Test(expected = VRSServiceException.class)
    public void testPropertyDeleteWithNullPropertyId() throws VRSServiceException {
        vrsResource.delete(null);
    }


    @Test
    public void testPropertyDeleteWithValidPropertyId() throws VRSServiceException {
       Response response =  vrsResource.delete(UUID.fromString(propertyId));
       RentalProperty rEntity = (RentalProperty)response.getEntity();
       Assert.assertEquals(UUID.fromString(propertyId), rEntity.getId());
    }


    @Test(expected = VRSServiceException.class)
    public void testPropertyDeleteWithInValidPropertyId() throws VRSServiceException {
        Response response =  vrsResource.delete(UUID.randomUUID());
    }






}
