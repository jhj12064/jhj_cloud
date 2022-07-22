package com.jhj.exceptions;

import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 */
@EqualsAndHashCode(callSuper = true)
public class NatBaseException extends RuntimeException {

    private static final long serialVersionUID = -7327347391234022655L;
    protected Integer code;
    protected String msg;

    public NatBaseException(String ...msg){
        super(StringUtils.join(msg));
    }

}
