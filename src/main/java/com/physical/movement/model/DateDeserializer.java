package com.physical.movement.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class DateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        if (StringUtils.isNotEmpty(parser.getText())) {
            try {
                return DateUtils.parseDate(parser.getText(), "yyyy-MM-dd HH:mm:ss");
            } catch (ParseException e) {
                //do nothings
            }
        }
        return null;
    }
}
