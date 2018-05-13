CloudEvents Java API
====================

Java API for CloudEvents(https://github.com/cloudevents/spec)

# Features

* Standard Java Bean for cloud events
* Builder Pattern to build events
* Json support by Jackson adapter

# How to use

* Extend CloudEvent class:

```
public class LoginEvent extends CloudEvent<String> {

    public LoginEvent(String email, String ip) {
        setData(email);
        setContentType("text/plain");
        setExtension("ip", ip);
    }
}
```

* Create object directly

```
CloudEvent<String> loginEvent = new CloudEvent<String>("text/plain", "jacky.chenlb@alibaba-inc.com");
```

* Event Builder

```
CloudEvent<String> loginEvent = CloudEventBuilder.<String>newInstance().contentType("text/plain").data("jacky.chenlb@alibaba-inc.com").build();
```

# ObjectMapper

* ObjectMapper creation
```
ObjectMapper objectMapper = new ObjectMapper();
objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
```

* write as String
```
objectMapper.writeValueAsString(loginEvent);
```

* read from json text
```
objectMapper.readValue(jsonText, new TypeReference<CloudEvent<String>>() {});
```

# Event Broker

Please consider NATS or NATS Streaming to post or subscribe cloud events.

# References

* CloudEvents Specification: https://github.com/cloudevents/spec
* CloudEvents Date format: https://tools.ietf.org/html/rfc3339
* NATS: https://nats.io/
