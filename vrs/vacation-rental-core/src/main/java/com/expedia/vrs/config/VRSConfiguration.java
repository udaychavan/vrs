package com.expedia.vrs.config;


import io.dropwizard.Configuration;
import lombok.Data;

@Data
public class VRSConfiguration extends Configuration {
    private String appName;
}
