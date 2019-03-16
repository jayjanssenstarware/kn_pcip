package kn.swift.pcip.exceptions;

public class TrackingNumberNotPresentException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -2205823720468493441L;

    public TrackingNumberNotPresentException(String message) {
        super(message);
    }

}