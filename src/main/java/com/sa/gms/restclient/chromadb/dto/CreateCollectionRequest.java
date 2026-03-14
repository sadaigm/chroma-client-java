package com.sa.gms.restclient.chromadb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * DTO for creating a new collection in Chroma DB.
 */
public class CreateCollectionRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    @JsonProperty("configuration")
    private Object configuration;

    @JsonProperty("schema")
    private Object schema;

    @JsonProperty("get_or_create")
    private Boolean getOrCreate;

    /**
     * Default constructor.
     */
    public CreateCollectionRequest() {
    }

    /**
     * Constructor with required fields.
     *
     * @param name the collection name
     */
    public CreateCollectionRequest(String name) {
        this.name = name;
    }

    /**
     * Constructor with all fields.
     *
     * @param name the collection name
     * @param metadata the collection metadata
     * @param configuration the collection configuration
     * @param schema the collection schema
     * @param getOrCreate whether to get existing collection if it exists
     */
    public CreateCollectionRequest(String name, Map<String, Object> metadata, 
                               Object configuration, Object schema, Boolean getOrCreate) {
        this.name = name;
        this.metadata = metadata;
        this.configuration = configuration;
        this.schema = schema;
        this.getOrCreate = getOrCreate;
    }

    /**
     * Gets the collection name.
     *
     * @return the collection name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the collection name.
     *
     * @param name the collection name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the metadata.
     *
     * @return the metadata
     */
    public Map<String, Object> getMetadata() {
        return metadata;
    }

    /**
     * Sets the metadata.
     *
     * @param metadata the metadata
     */
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    /**
     * Gets the configuration.
     *
     * @return the configuration
     */
    public Object getConfiguration() {
        return configuration;
    }

    /**
     * Sets the configuration.
     *
     * @param configuration the configuration
     */
    public void setConfiguration(Object configuration) {
        this.configuration = configuration;
    }

    /**
     * Gets the schema.
     *
     * @return the schema
     */
    public Object getSchema() {
        return schema;
    }

    /**
     * Sets the schema.
     *
     * @param schema the schema
     */
    public void setSchema(Object schema) {
        this.schema = schema;
    }

    /**
     * Gets the get_or_create flag.
     *
     * @return the get_or_create flag
     */
    public Boolean getGetOrCreate() {
        return getOrCreate;
    }

    /**
     * Sets the get_or_create flag.
     *
     * @param getOrCreate the get_or_create flag
     */
    public void setGetOrCreate(Boolean getOrCreate) {
        this.getOrCreate = getOrCreate;
    }

    @Override
    public String toString() {
        return "CreateCollectionRequest{" +
                "name='" + name + '\'' +
                ", metadata=" + metadata +
                ", configuration=" + configuration +
                ", schema=" + schema +
                ", getOrCreate=" + getOrCreate +
                '}';
    }
}