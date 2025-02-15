package com.production.infrastructure.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JacksonConfigTest {

    @Test
    public void testObjectMapperBean() {
        JacksonConfig jacksonConfig = new JacksonConfig();
        ObjectMapper objectMapper = jacksonConfig.objectMapper();
        assertNotNull(objectMapper, "ObjectMapper should not be null");
    }
}

