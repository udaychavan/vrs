package com.expedia.vrs.exception;

import com.google.common.collect.Maps;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Map;

/**
 * Created by uday.chavan on Mar, 2019
 */
public class ApplicationExceptionMapper implements ExceptionMapper<VRSServiceException> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(VRSServiceException exception) {
        final Map<String, Object> responseMap = Maps.newHashMap();
        if (exception.getMessage() != null) {
            responseMap.put("message", exception.getMessage());
        }

        return Response.status(exception.getErrorCode().getStatusCode()).entity(responseMap).build();
    }
}
