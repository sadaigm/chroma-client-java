package com.sa.gms.restclient.chromadb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * DTO for updating an existing collection in Chroma DB.
 */
public class UpdateCollectionRequest {

    @JsonProperty("new_name")
    private String newName;

    @JsonProperty("new_metadata")
    private Map<String, Object> newMetadata;

    @JsonProperty("new_configuration")
    private Object newConfiguration;

    /**
     * Default constructor.
     */
    public UpdateCollectionRequest() {
    }

    /**
     * Constructor with new name.
     *
     * @param newName the new collection name
     */
    public UpdateCollectionRequest(String newName) {
        this.newName = newName;
    }

    /**
     * Constructor with all fields.
     *
     * @param newName the new collection name
     * @param newMetadata the new metadata
     * @param newConfiguration the new configuration
     */
    public UpdateCollectionRequest(String newName, Map<String, Object> newMetadata, Object newConfiguration) {
        this.newName = newName;
        this.newMetadata = newMetadata;
        this.newConfiguration = newConfiguration;
    }

    /**
     * Gets the new collection name.
     *
     * @return the new collection name
     */
    public String getNewName() {
        return newName;
    }

    /**
     * Sets the new collection name.
     *
     * @param newName the new collection name
     */
    public void setNewName(String newName) {
        this.newName = newName;
    }

    /**
     * Gets the new metadata.
     *
     * @return the new metadata
     */
    public Map<String, Object> getNewMetadata() {
        return newMetadata;
    }

    /**
     * Sets the new metadata.
     *
     * @param newMetadata the new metadata
     */
    public void setNewMetadata(Map<String, Object> newMetadata) {
        this.newMetadata = newMetadata;
    }

    /**
     * Gets the new configuration.
     *
     * @return the new configuration
     */
    public Object getNewConfiguration() {
        return newConfiguration;
    }

    /**
     * Sets the new configuration.
     *
     * @param newConfiguration the new configuration
     */
    public void setNewConfiguration(Object newConfiguration) {
        this.newConfiguration = newConfiguration;
    }

    @Override
    public String toString() {
        return "UpdateCollectionRequest{" +
                "newName='" + newName + '\'' +
                ", newMetadata=" + newMetadata +
                ", newConfiguration=" + newConfiguration +
                '}';
    }
}