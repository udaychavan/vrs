package com.expedia.vrs.persistence.module;

import com.expedia.vrs.persistence.IVRSDao;
import com.expedia.vrs.persistence.impl.IVRSDaoImpl;
import com.google.inject.AbstractModule;

/**
 * Created by uday.chavan on Mar, 2019
 */
public class VRSPersistenceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IVRSDao.class).to(IVRSDaoImpl.class);
    }
}
