package framework.exceptions;

import framework.logger.Log;

public class ApiException extends RuntimeException{


    // Default constructor
    private ApiException(String message) {
        super(message);
        Log.error(message);
    }

    // Constructor that accepts a message and a cause
    private ApiException(String message, Throwable cause) {
        super(message, cause);
        Log.error(message, cause);
    }

    // Factory method for creating an exception with just a message
    public static ApiException of(String message) {
        Log.info(message);
        return new ApiException(message);
    }

    // Factory method for creating an exception with a message and a cause
    public static ApiException of(String message, Throwable cause) {
       Log.error(message, cause);
        return new ApiException(message, cause);
    }

    // Factory method for creating an exception with custom test case context
    public static ApiException of(String message, String testCaseId, String requestDetails) {
        String formattedMessage = String.format("Test Case: %s - Error in request: %s - %s", testCaseId, requestDetails, message);
       Log.error(formattedMessage);
        return new ApiException(formattedMessage);
    }

}
