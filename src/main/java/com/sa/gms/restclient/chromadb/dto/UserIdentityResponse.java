package com.sa.gms.restclient.chromadb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * DTO for user identity response from Chroma DB.
 * Contains user ID, tenant, and list of databases.
 */
public class UserIdentityResponse {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("tenant")
    private String tenant;

    @JsonProperty("databases")
    private List<String> databases;

    /**
     * Default constructor.
     */
    public UserIdentityResponse() {
    }

    /**
     * Constructor with all fields.
     *
     * @param userId the user ID
     * @param tenant the tenant name
     * @param databases the list of databases
     */
    public UserIdentityResponse(String userId, String tenant, List<String> databases) {
        this.userId = userId;
        this.tenant = tenant;
        this.databases = databases;
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId the user ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the tenant name.
     *
     * @return the tenant name
     */
    public String getTenant() {
        return tenant;
    }

    /**
     * Sets the tenant name.
     *
     * @param tenant the tenant name
     */
    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    /**
     * Gets the list of databases.
     *
     * @return the list of databases
     */
    public List<String> getDatabases() {
        return databases;
    }

    /**
     * Sets the list of databases.
     *
     * @param databases the list of databases
     */
    public void setDatabases(List<String> databases) {
        this.databases = databases;
    }

    @Override
    public String toString() {
        return "UserIdentityResponse{" +
                "userId='" + userId + '\'' +
                ", tenant='" + tenant + '\'' +
                ", databases=" + databases +
                '}';
    }
}