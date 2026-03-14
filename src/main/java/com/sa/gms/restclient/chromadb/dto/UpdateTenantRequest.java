package com.sa.gms.restclient.chromadb.dto;

/**
 * Request DTO for updating an existing tenant in Chroma DB.
 */
public class UpdateTenantRequest {

    private String resource_name;

    /**
     * Default constructor.
     */
    public UpdateTenantRequest() {
    }

    /**
     * Constructor with resource name.
     *
     * @param resourceName the resource name
     */
    public UpdateTenantRequest(String resourceName) {
        this.resource_name = resourceName;
    }

    /**
     * Gets the resource name.
     *
     * @return the resource name
     */
    public String getResource_name() {
        return resource_name;
    }

    /**
     * Sets the resource name.
     *
     * @param resource_name the resource name
     */
    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    @Override
    public String toString() {
        return "UpdateTenantRequest{" +
                "resource_name='" + resource_name + '\'' +
                '}';
    }
}