package com.sa.gms.restclient.chromadb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for healthcheck endpoint.
 * Contains the health status of the service.
 */
public class HealthcheckResponse {

    /**
     * The health status message.
     */
    @JsonProperty("status")
    private String status;

    /**
     * Default constructor.
     */
    public HealthcheckResponse() {
    }

    /**
     * Constructor with status.
     *
     * @param status the health status
     */
    public HealthcheckResponse(String status) {
        this.status = status;
    }

    /**
     * Gets the health status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the health status.
     *
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HealthcheckResponse{" +
                "status='" + status + '\'' +
                '}';
    }
}