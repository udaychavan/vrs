package com.expedia.vrs.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by uday.chavan on Mar, 2019
 */
public class VRSServiceException extends Exception {

    private static Map<String, String> errorCodes = new HashMap<>();
    private ErrorCode code;

    static {
        errorCodes.put("unknown_error", "We have encountered unknown error.");
        errorCodes.put("unauthorized", "Unauthorized request");
        errorCodes.put("bad_request", "Invalid input.");
        errorCodes.put("not_found", "Property does not exists");
    }

    public VRSServiceException(final ErrorCode code, final String message) {
        super(message);
        this.code = code;

    }

    public ErrorCode getErrorCode(){
        return this.code;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)

    public enum ErrorCode {
        unknown_error("unknown_error", 500),  unauthorized("unauthorized",
                401),  not_found("not_found", 404),bad_request("bad_request",
                400);

        private String code;

        @Getter
        private Integer statusCode;

//        public String getMessage() {
//            return errorCodes.containsKey(code) ? errorCodes.get(code) : "Unknown error";
//        }
    }

}
