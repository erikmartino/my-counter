package org.glassfish.jersey.filter;

import org.glassfish.jersey.client.ClientConfig;

import java.util.logging.Logger;

public class LoggingFilter extends ClientConfig {
    public LoggingFilter(Logger logger, boolean b) {
    }
}
