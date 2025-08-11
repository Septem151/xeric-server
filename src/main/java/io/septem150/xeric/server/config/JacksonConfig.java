package io.septem150.xeric.server.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
    return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
            .serializationInclusion(JsonInclude.Include.NON_NULL)
            .featuresToEnable(
                    DeserializationFeature.READ_ENUMS_USING_TO_STRING,
                    SerializationFeature.WRITE_ENUMS_USING_TO_STRING
            )
            .featuresToDisable(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    DeserializationFeature.ACCEPT_FLOAT_AS_INT,
                    SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
            );
  }

}
