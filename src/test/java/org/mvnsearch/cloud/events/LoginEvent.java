package org.mvnsearch.cloud.events;

/**
 * login event
 *
 * @author linux_china
 */
public class LoginEvent extends CloudEvent<String> {

    public LoginEvent(String email, String ip) {
        setData(email);
        setContentType("text/plain");
        setExtension("ip", ip);
    }
}
