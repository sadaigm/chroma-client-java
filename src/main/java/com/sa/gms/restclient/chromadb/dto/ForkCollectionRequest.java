package com.sa.gms.restclient.chromadb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for forking a collection.
 * Contains the new name for the forked collection.
 */
public class ForkCollectionRequest {

    /**
     * The new name for the forked collection.
     */
    @JsonProperty("new_name")
    private String newName;

    /**
     * Default constructor.
     */
    public ForkCollectionRequest() {
    }

    /**
     * Constructor with new name.
     *
     * @param newName the new name for the forked collection
     */
    public ForkCollectionRequest(String newName) {
        this.newName = newName;
    }

    /**
     * Gets the new name for the forked collection.
     *
     * @return the new name
     */
    public String getNewName() {
        return newName;
    }

    /**
     * Sets the new name for the forked collection.
     *
     * @param newName the new name to set
     */
    public void setNewName(String newName) {
        this.newName = newName;
    }

    @Override
    public String toString() {
        return "ForkCollectionRequest{" +
                "newName='" + newName + '\'' +
                '}';
    }
}