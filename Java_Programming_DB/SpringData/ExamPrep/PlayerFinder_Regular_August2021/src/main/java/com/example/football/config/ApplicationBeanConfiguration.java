package com.example.football.config;

import com.example.football.util.validation.ValidationUtils;
import com.example.football.util.validation.ValidationUtilsImpl;
import com.example.football.util.xmlParser.XmlParser;
import com.example.football.util.xmlParser.XmlParserImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public XmlParser xmlParser() {
        return new XmlParserImpl();
    }

    @Bean
    public ValidationUtils validationUtils() {
        return new ValidationUtilsImpl();
    }
}
