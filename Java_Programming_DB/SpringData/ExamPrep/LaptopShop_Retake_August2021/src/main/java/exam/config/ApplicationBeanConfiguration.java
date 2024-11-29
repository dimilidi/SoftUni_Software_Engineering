package exam.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exam.util.localDateAdapter.LocalDateAdapterDeserializer;
import exam.util.validation.ValidationUtils;
import exam.util.validation.ValidationUtilsImpl;
import exam.util.xmlParser.XmlParser;
import exam.util.xmlParser.XmlParserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapterDeserializer())
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



