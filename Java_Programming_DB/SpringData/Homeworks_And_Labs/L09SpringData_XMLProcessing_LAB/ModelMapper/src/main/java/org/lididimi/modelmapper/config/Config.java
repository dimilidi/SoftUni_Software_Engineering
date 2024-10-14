package org.lididimi.modelmapper.config;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.lididimi.modelmapper.dtos.AddressDTO;
import org.lididimi.modelmapper.dtos.AddressWithCollectionDTO;
import org.lididimi.modelmapper.dtos.CountryXMLDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Scanner;

@Configuration
public class Config {
    @Bean
    public ModelMapper createModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Scanner createScanner() {
        return new Scanner(System.in);
    }

    @Bean("addressContext")
    @Primary
    public JAXBContext createAddressContext() throws JAXBException {
        return JAXBContext.newInstance(AddressDTO.class);
    }

    @Bean("addressWithCollectionContext")
    public JAXBContext createAddressWithCollectionContext() throws JAXBException {
        return JAXBContext.newInstance(AddressWithCollectionDTO.class);
    }

    @Bean("countryContext")
    public JAXBContext createCountryContext() throws JAXBException {
        return JAXBContext.newInstance(CountryXMLDTO.class);
    }

}
