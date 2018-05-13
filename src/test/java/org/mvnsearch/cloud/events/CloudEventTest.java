package org.mvnsearch.cloud.events;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URI;

/**
 * cloud event test
 *
 * @author linux_china
 */
public class CloudEventTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Test
    public void testJsonEvent() throws Exception {
        String email = "linux_china@hotmail.com";
        CloudEvent<String> loginEvent = new CloudEvent<String>("text/plain", email);
        loginEvent.setSchemaURL(new URI("mailto:" + email));
        String jsonText = objectMapper.writeValueAsString(loginEvent);
        System.out.println(jsonText);
        CloudEvent cloudEvent = objectMapper.readValue(jsonText, new TypeReference<CloudEvent<String>>() {
        });
        System.out.println(cloudEvent.getEventTime());
    }


    @Test
    public void testBuilder() throws Exception {
        CloudEvent<String> loginEvent = CloudEventBuilder.<String>newInstance().contentType("text/plain").data("linux_china@hotmail.com").build();
        System.out.println(objectMapper.writeValueAsString(loginEvent));
    }
}
