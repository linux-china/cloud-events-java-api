package org.mvnsearch.cloud.events;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * cloud event from CloudEvents Specs: https://github.com/cloudevents/spec/blob/v0.1/spec.md
 *
 * @author linux_china
 */
@JsonIgnoreProperties(value = {"$schema"})
public class CloudEvent<T> {
    /**
     * cloud events version
     */
    @JsonProperty("cloudEventsVersion")
    private String cloudEventsVersion = "0.1";
    /**
     * event ID
     */
    @JsonProperty("eventID")
    private String eventID = UUID.randomUUID().toString();
    /**
     * event type: com.example.someevent
     */
    @JsonProperty("eventType")
    private String eventType;
    /**
     * event type version:
     */
    @JsonProperty("eventTypeVersion")
    private String eventTypeVersion;
    /**
     * additional metadata
     */
    @JsonProperty("extensions")
    private Map<String, String> extensions;
    /**
     * event producer
     */
    @JsonProperty("source")
    private URI source;
    /**
     * content type for data, such as text/plain, application/json
     */
    @JsonProperty("contentType")
    private String contentType;
    /**
     * The event payload
     */
    @JsonProperty("data")
    private T data;
    /**
     * A link to the schema that the data attribute adheres to
     */
    @JsonProperty("schemaURL")
    private URI schemaURL;
    /**
     * Timestamp of when the event happened
     */
    @JsonProperty("eventTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Date eventTime;

    public CloudEvent() {
        this.eventTime = new Date();
    }

    public CloudEvent(T data) {
        this.data = data;
        this.eventTime = new Date();
    }

    public CloudEvent(String type, T data) {
        this.contentType = type;
        this.data = data;
        this.eventTime = new Date();
    }

    public CloudEvent(String type, T data, URI source) {
        this.contentType = type;
        this.data = data;
        this.source = source;
        this.eventTime = new Date();
    }

    public String getCloudEventsVersion() {
        return cloudEventsVersion;
    }

    public void setCloudEventsVersion(String cloudEventsVersion) {
        this.cloudEventsVersion = cloudEventsVersion;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventTypeVersion() {
        return eventTypeVersion;
    }

    public void setEventTypeVersion(String eventTypeVersion) {
        this.eventTypeVersion = eventTypeVersion;
    }

    public Map<String, String> getExtensions() {
        return extensions;
    }

    public void setExtensions(Map<String, String> extensions) {
        this.extensions = extensions;
    }

    public URI getSource() {
        return source;
    }

    public void setSource(URI source) {
        this.source = source;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public URI getSchemaURL() {
        return schemaURL;
    }

    public void setSchemaURL(URI schemaURL) {
        this.schemaURL = schemaURL;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public Serializable getExtension(String name) {
        return extensions == null ? null : extensions.get(name);
    }

    public void setExtension(String name, String value) {
        if (extensions == null) {
            extensions = new HashMap<String, String>();
        }
        this.extensions.put(name, value);
    }

    @Override
    public String toString() {
        return "CloudEvent{" +
                "cloudEventsVersion='" + cloudEventsVersion + '\'' +
                ", eventID='" + eventID + '\'' +
                ", eventType='" + eventType + '\'' +
                ", eventTypeVersion='" + eventTypeVersion + '\'' +
                ", extensions=" + extensions +
                ", source=" + source +
                ", contentType='" + contentType + '\'' +
                ", data=" + data +
                ", schemaURL=" + schemaURL +
                ", eventTime=" + eventTime +
                '}';
    }
}
