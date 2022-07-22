package com.jhj.exceptions;

/**
 */
public class AlibabaSmsException extends NatBaseException {


    public AlibabaSmsException(String msg){
        super(msg);
        this.msg = msg;
    }
}
