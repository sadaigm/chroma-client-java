package com.sa.gms.restclient.chromadb.dto;

/**
 * Request DTO for creating a new tenant in Chroma DB.
 */
public class CreateTenantRequest {

    private String name;

    /**
     * Default constructor.
     */
    public CreateTenantRequest() {
    }

    /**
     * Constructor with name.
     *
     * @param name the tenant name
     */
    public CreateTenantRequest(String name) {
        this.name = name;
    }

    /**
     * Gets the tenant name.
     *
     * @return the tenant name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the tenant name.
     *
     * @param name the tenant name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CreateTenantRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}