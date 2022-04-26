package com.jhj.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date arg0, JsonGenerator arg1, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowAsISO = df.format(arg0);
        arg1.writeString(nowAsISO);
    }


    @Override
    public void serializeWithType(Date value, JsonGenerator g, SerializerProvider provider,
                                  TypeSerializer typeSer) throws IOException {
        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g,
                typeSer.typeId(value, JsonToken.VALUE_EMBEDDED_OBJECT));
        serialize(value, g, provider);
        typeSer.writeTypeSuffix(g, typeIdDef);
    }

}
