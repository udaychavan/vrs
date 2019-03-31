package com.expedia.vrs;


import com.expedia.vrs.config.VRSConfiguration;
import com.expedia.vrs.exception.ApplicationExceptionMapper;
import com.expedia.vrs.module.VRSModule;
import com.expedia.vrs.persistence.module.VRSPersistenceModule;
import com.expedia.vrs.resource.VRSResource;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class VRSApplication extends Application<VRSConfiguration> {

    @Override
    public void initialize(Bootstrap<VRSConfiguration> bootstrap){
    }

    @Override
    public void run(VRSConfiguration configuration, Environment environment) {

        VRSModule vrsModule = new VRSModule(configuration);
        VRSPersistenceModule persistenceModule = new VRSPersistenceModule();
        Injector injector = Guice.createInjector(vrsModule, persistenceModule);

        environment.jersey().register(injector.getInstance(VRSResource.class));
        environment.jersey().register(ApplicationExceptionMapper.class);
    }

    public static void main(String[] args) throws Exception {
        new VRSApplication().run(args);
    }
}