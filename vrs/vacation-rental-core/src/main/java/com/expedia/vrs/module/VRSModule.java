package com.expedia.vrs.module;

import com.expedia.vrs.config.VRSConfiguration;
import com.expedia.vrs.service.IVacationRentalService;
import com.expedia.vrs.service.impl.VacationRentalServiceImpl;
import com.google.inject.AbstractModule;

public class VRSModule extends AbstractModule {
    VRSConfiguration configuration;

    public VRSModule(VRSConfiguration configuration){
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        bind(IVacationRentalService.class).to(VacationRentalServiceImpl.class);
    }

}
