package org.mvnsearch.cloud.events;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URI;
import java.util.Base64;
import java.util.Date;

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
        CloudEvent<String> loginEvent = new CloudEvent<>("text/plain", email);
        loginEvent.setSchemaURL(new URI("mailto:" + email));
        loginEvent.setEventTime(new Date());
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

    @Test
    public void parseJsonFile() throws Exception {
        CloudEvent cloudEvent = objectMapper.readValue(this.getClass().getResourceAsStream("/cloud-event-demo.json"), new TypeReference<CloudEvent<String>>() {
        });
        System.out.println(cloudEvent.getEventTime());
    }

    @Test
    public void testBinaryData() throws Exception {
        byte[] data = "good".getBytes();
        CloudEvent<byte[]> event1 = new CloudEvent<>("application/octet-stream", data);
        String jsonText = objectMapper.writeValueAsString(event1);
        String base64Text = Base64.getEncoder().encodeToString(data);
        Assert.assertTrue(jsonText.contains(base64Text));
        CloudEvent<byte[]> event2 = objectMapper.readValue(jsonText, new TypeReference<CloudEvent<byte[]>>() {
        });
        Assert.assertArrayEquals(data, event2.getData());
    }
}

