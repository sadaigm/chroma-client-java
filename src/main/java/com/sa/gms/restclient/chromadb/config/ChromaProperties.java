package com.sa.gms.restclient.chromadb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for Chroma DB.
 * Binds to 'chroma' prefix in bootstrap.yml
 */
@Component
@ConfigurationProperties(prefix = "chroma")
public class ChromaProperties {

    /**
     * Default constructor.
     */
    public ChromaProperties(){
//        
    }

    /**
     * The base URL/host for Chroma DB API.
     * Default value: http://localhost:8000
     */
    private String host = "http://localhost:8000";

    /**
     * Gets the base URL/host for Chroma DB API.
     *
     * @return the Chroma DB host URL
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the base URL/host for Chroma DB API.
     *
     * @param host the Chroma DB host URL
     */
    public void setHost(String host) {
        this.host = host;
    }
}
