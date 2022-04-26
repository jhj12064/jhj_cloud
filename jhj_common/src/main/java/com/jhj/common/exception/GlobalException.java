package com.jhj.common.exception;

import com.jhj.common.enums.Error;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalException extends RuntimeException{


    private static final long serialVersionUID = -4952529182498563693L;
    private Integer code;

    public GlobalException(Error msg){
        super(msg.getMsg());
        this.code = msg.getCode();
    }

    public GlobalException(Error msg, String appendMsg){
        super(msg.getMsg() +"："+ appendMsg);
        this.code = msg.getCode();
    }


}
