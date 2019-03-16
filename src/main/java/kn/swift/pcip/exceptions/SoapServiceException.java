package kn.swift.pcip.exceptions;

public class SoapServiceException extends RuntimeException {

    private final Integer code;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SoapServiceException(String message,Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}