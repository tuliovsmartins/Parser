package com.ef.Exception;

// Class to exceptions intercept and format message
// Other exception threat can be done, if we have time
public class ParserException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ParserException(String message) {
        super(message);
    }

    public ParserException(String message, Object... params) {
        super(String.format(message, params));
    }

}
