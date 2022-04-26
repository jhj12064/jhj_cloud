package com.jhj.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jhj.common.auth.TokenUtil;

import java.io.IOException;

public class ObjectSerializer extends JsonSerializer<Integer> {

    @Override
    public void serialize(Integer arg0, JsonGenerator arg1, SerializerProvider arg2)
            throws IOException {
        String objId = TokenUtil.encode(arg0);
        arg1.writeString(objId);
    }

}
