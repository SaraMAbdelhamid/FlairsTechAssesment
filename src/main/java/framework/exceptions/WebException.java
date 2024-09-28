package framework.exceptions;

import framework.logger.Log;

public class WebException extends RuntimeException{


    // Default constructor
    private WebException(String message) {
        super(message);
        Log.error(message);
    }

    // Constructor that accepts a message and a cause
    private WebException(String message, Throwable cause) {
        super(message, cause);
        Log.error(message, cause);
    }

    // Factory method for creating an exception with just a message
    public static WebException of(String message) {
       Log.info(message);
        return new WebException(message);
    }

    // Factory method for creating an exception with a message and a cause
    public static WebException of(String message, Throwable cause) {
        Log.error(message, cause);
        return new WebException(message, cause);
    }

    // Factory method for creating an exception with custom test case context
    public static WebException of(String message, String testCaseId, String elementDetails) {
        String formattedMessage = String.format("Test Case: %s - Error in element: %s - %s", testCaseId, elementDetails, message);
        Log.error(formattedMessage);
        return new WebException(formattedMessage);
    }

}
