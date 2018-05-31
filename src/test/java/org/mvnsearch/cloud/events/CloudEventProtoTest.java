package org.mvnsearch.cloud.events;


import org.junit.Assert;
import org.junit.Test;

/**
 * cloud event proto test
 *
 * @author linux_china
 */
public class CloudEventProtoTest {
    @Test
    public void testProtoOperation() throws Exception {
        CloudEventProtos.CloudEventProto event = CloudEventProtos.CloudEventProto.newBuilder()
                .setCloudEventsVersion("0.1")
                .setContentType("text/plain")
                .putExtensions("name", "linux_china")
                .setEventTime(System.currentTimeMillis())
                .build();
        CloudEventProtos.CloudEventProto event2 = CloudEventProtos.CloudEventProto.parseFrom(event.toByteArray());
        Assert.assertEquals(event2.getContentType(), "text/plain");
        Assert.assertEquals(event2.getExtensionsMap().get("name"), "linux_china");
        CloudEvent javaEvent = CloudEventMapper.protoToJavaBean(event2);
        Assert.assertEquals(javaEvent.getContentType(), "text/plain");
        Assert.assertEquals(javaEvent.getExtension("name"), "linux_china");
    }
}
