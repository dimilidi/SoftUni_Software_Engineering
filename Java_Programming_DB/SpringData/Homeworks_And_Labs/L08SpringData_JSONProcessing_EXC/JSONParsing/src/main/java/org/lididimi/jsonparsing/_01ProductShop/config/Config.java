package org.lididimi.jsonparsing._01ProductShop.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Scanner;

@Configuration
public class Config {

    private final Environment environment;

    public Config(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource createProductShopDataSource() {

        final DriverManagerDataSource manager = new DriverManagerDataSource();

        manager.setDriverClassName(Objects.requireNonNull(environment.getProperty("spring.datasource.driver-class-name")));
        manager.setUrl(environment.getProperty("product-shop.spring.datasource.url"));
        manager.setUsername(environment.getProperty("spring.datasource.username"));
        manager.setPassword(environment.getProperty("spring.datasource.password"));

        return manager;
    }

    @Bean
    public Gson createGson() {

        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ModelMapper createModelMapper() {

        return new ModelMapper();
    }

    @Bean
    public Scanner createScanner() {

        return new Scanner(System.in);
    }
}



