package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.config;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public LocalDateTime unmarshal(String value) throws Exception {
        return LocalDateTime.parse(value, FORMATTER);
    }

    @Override
    public String marshal(LocalDateTime dateTime) throws Exception {
        return dateTime.format(FORMATTER);
    }
}
