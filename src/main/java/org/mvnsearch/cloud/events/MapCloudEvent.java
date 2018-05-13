package org.mvnsearch.cloud.events;

import java.util.Map;

/**
 * cloud event with Map data
 *
 * @author linux_china
 */
public class MapCloudEvent extends CloudEvent<Map<String, Object>> {
    public MapCloudEvent() {
        setContentType("application/json");
    }
}
