package com.showgo.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Create web service at /api
 */
@ApplicationPath("/api")
public class ShowGoApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(PerformerService.class);
        return h;
    }
}
