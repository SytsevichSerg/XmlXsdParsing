
package com.epam.task2.exception;

public class ParsingXMLException extends Exception {
    public ParsingXMLException() {
        super();
    }
    
    public ParsingXMLException(String message) {
        super(message);
    }
    
    public ParsingXMLException(Exception exception) {
        super(exception);
    }
    
    public ParsingXMLException(String message, Exception exception) {
        super(message, exception);
    }
}
