package com.jhj.common.auth;

import com.jhj.common.enums.Error;
import com.jhj.common.exception.GlobalException;
import com.jhj.utils.safe.CryptDESUtil;

public class TokenUtil {

    private static final String password = "wq_jhj_123456789";
    private static final String exkey = "!_!";


    public static String encode(Object... keys){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            sb.append(keys[i]);
            if(i != keys.length-1){
                sb.append(exkey);
            }
        }
        String tokenStr ;
        try{
            tokenStr = CryptDESUtil.encrypt(sb.toString(), password);
            tokenStr = tokenStr.replaceAll("/", "-");
            tokenStr = tokenStr.replaceAll("\\+", "_");
        } catch (Exception e) {
            throw new GlobalException(Error.FAIL);
        }
        return tokenStr;
    }
    public static String[] decode(String token){
        token = token.replaceAll("-", "/");
        token = token.replaceAll("_", "+");
        try {
            String contents = CryptDESUtil.decrypt(token, password);
            String[] tokenValues = contents.split(exkey);
            if ((tokenValues == null) || (tokenValues.length != 3)) {
                return null;
            }
            return tokenValues;
        }catch (Exception e){
            throw new GlobalException(Error.FAIL);
        }
    }


}
