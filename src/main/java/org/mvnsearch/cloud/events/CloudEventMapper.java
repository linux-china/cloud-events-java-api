package org.mvnsearch.cloud.events;

import com.google.protobuf.ByteString;

import java.net.URI;
import java.util.Date;

/**
 * Cloud event mapper for bean convert
 *
 * @author linux_china
 */
public class CloudEventMapper {

    public static CloudEvent<byte[]> protoToJavaBean(CloudEventProtos.CloudEventProto proto) {
        CloudEvent<byte[]> event = new CloudEvent<>();
        event.setCloudEventsVersion(proto.getCloudEventsVersion());
        event.setEventID(proto.getEventID());
        event.setEventType(proto.getEventType());
        event.setEventTypeVersion(proto.getEventTypeVersion());
        event.setExtensions(proto.getExtensionsMap());
        event.setContentType(proto.getContentType());
        event.setData(proto.getData().toByteArray());
        event.setEventTime(new Date(proto.getEventTime()));
        try {
            if (proto.getSource() != null && !proto.getSource().isEmpty()) {
                event.setSource(new URI(proto.getSource()));
            }
            if (proto.getSchemaURL() != null && !proto.getSchemaURL().isEmpty()) {
                event.setSchemaURL(new URI(proto.getSchemaURL()));
            }
        } catch (Exception ignore) {

        }
        return event;
    }

    public static CloudEventProtos.CloudEventProto javaBeanToProto(CloudEvent event) {
        CloudEventProtos.CloudEventProto.Builder builder = CloudEventProtos.CloudEventProto.newBuilder();
        builder.setCloudEventsVersion(event.getCloudEventsVersion())
                .setEventID(event.getEventID())
                .setEventType(event.getEventType())
                .setEventTypeVersion(event.getEventTypeVersion())
                .setContentType(event.getContentType())
                .setEventTime(event.getEventTime().getTime());
        if (event.getExtensions() != null) {
            builder.putAllExtensions(event.getExtensions());
        }
        if (event.getSource() != null) {
            builder.setSource(event.getSource().toString());
        }
        if (event.getSchemaURL() != null) {
            builder.setSchemaURL(event.getSchemaURL().toString());
        }
        if (event.getData() != null) {
            if (event.getData() instanceof String) {
                builder.setData(ByteString.copyFromUtf8((String) event.getData()));
            } else if (event.getData() instanceof byte[]) {
                builder.setData(ByteString.copyFrom((byte[]) event.getData()));
            }
        }
        return builder.build();

    }
}
