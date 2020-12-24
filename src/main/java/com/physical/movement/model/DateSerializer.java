package com.physical.movement.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.util.Date;

public class DateSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator generator, SerializerProvider provider) throws IOException {
        if (date == null) {
            generator.writeNull();
        } else {
            generator.writeString(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
        }
    }
}
