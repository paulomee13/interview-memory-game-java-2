package org.couchbase.exception;
/*
 * CustomException class for handling exceptions
 * @version 1
 * 3 Dec 2022
 */

public class CustomException extends Exception{
    public CustomException(String message) {
        super(message);
    }
}
