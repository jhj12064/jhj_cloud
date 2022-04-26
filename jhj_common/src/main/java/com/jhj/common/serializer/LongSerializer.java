package com.jhj.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class LongSerializer extends JsonSerializer<Long>{

    @Override
    public void serialize(Long arg0, JsonGenerator arg1, SerializerProvider arg2)
            throws IOException {
        arg0 =arg0*1000L;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowAsISO = df.format(arg0);
        arg1.writeString(nowAsISO);
    }

}
