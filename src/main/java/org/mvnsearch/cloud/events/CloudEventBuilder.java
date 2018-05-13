package org.mvnsearch.cloud.events;

import java.net.URI;
import java.util.Date;
import java.util.Map;

/**
 * cloud event builder
 *
 * @author linux_china
 */
public class CloudEventBuilder<T> {
    /**
     * cloud event
     */
    private CloudEvent<T> cloudEvent;

    public static <K> CloudEventBuilder<K> newInstance() {
        CloudEventBuilder<K> builder = new CloudEventBuilder<K>();
        builder.cloudEvent = new CloudEvent<K>();
        return builder;
    }

    public static CloudEventBuilder<Map<String, Object>> newInstance(Map<String, Object> data) {
        CloudEventBuilder<Map<String, Object>> builder = new CloudEventBuilder<Map<String, Object>>();
        builder.cloudEvent = new MapCloudEvent();
        builder.cloudEvent.setContentType("application/json");
        return builder;
    }

    public CloudEventBuilder<T> eventID(String eventId) {
        cloudEvent.setEventID(eventId);
        return this;
    }

    public CloudEventBuilder<T> eventType(String eventType) {
        cloudEvent.setEventType(eventType);
        return this;
    }

    public CloudEventBuilder<T> eventTypeVersion(String eventTypeVersion) {
        cloudEvent.setEventTypeVersion(eventTypeVersion);
        return this;
    }


    public CloudEventBuilder<T> source(URI source) {
        cloudEvent.setSource(source);
        return this;
    }


    public CloudEventBuilder<T> contentType(String contentType) {
        cloudEvent.setContentType(contentType);
        return this;
    }

    public CloudEventBuilder<T> data(T data) {
        cloudEvent.setData(data);
        return this;
    }

    public CloudEventBuilder<T> schemaURL(URI schemaURL) {
        cloudEvent.setSchemaURL(schemaURL);
        return this;
    }

    public CloudEventBuilder<T> eventTime(Date eventTime) {
        cloudEvent.setEventTime(eventTime);
        return this;
    }

    public CloudEventBuilder<T> extensions(Map<String, String> extensions) {
        cloudEvent.setExtensions(extensions);
        return this;
    }

    public CloudEventBuilder<T> extension(String name, String value) {
        cloudEvent.setExtension(name, value);
        return this;
    }

    public CloudEventBuilder<T> cloudEventsVersion(String cloudEventsVersion) {
        cloudEvent.setCloudEventsVersion(cloudEventsVersion);
        return this;
    }

    public CloudEvent<T> build() {
        return cloudEvent;
    }

}
