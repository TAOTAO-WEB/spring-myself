package com.blend.ibt.bean;

import com.blend.ibt.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    private final DateTimeFormatter DATE_TIME_FORMATTER;

    public StringToLocalDateConverter(DateTimeFormatter date_time_formatter) {
        DATE_TIME_FORMATTER = date_time_formatter;
    }

    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(source,DATE_TIME_FORMATTER);
    }
}
