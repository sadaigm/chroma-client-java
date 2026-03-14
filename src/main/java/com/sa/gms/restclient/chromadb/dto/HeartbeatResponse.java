package com.sa.gms.restclient.chromadb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for heartbeat endpoint.
 * Contains nanosecond timestamp of current time.
 */
public class HeartbeatResponse {

    /**
     * The nanosecond heartbeat timestamp.
     */
    @JsonProperty("nanosecond heartbeat")
    private Long nanosecondHeartbeat;

    /**
     * Default constructor.
     */
    public HeartbeatResponse() {
    }

    /**
     * Constructor with nanosecond heartbeat.
     *
     * @param nanosecondHeartbeat the nanosecond timestamp
     */
    public HeartbeatResponse(Long nanosecondHeartbeat) {
        this.nanosecondHeartbeat = nanosecondHeartbeat;
    }

    /**
     * Gets the nanosecond heartbeat timestamp.
     *
     * @return the nanosecond timestamp
     */
    public Long getNanosecondHeartbeat() {
        return nanosecondHeartbeat;
    }

    /**
     * Sets the nanosecond heartbeat timestamp.
     *
     * @param nanosecondHeartbeat the nanosecond timestamp to set
     */
    public void setNanosecondHeartbeat(Long nanosecondHeartbeat) {
        this.nanosecondHeartbeat = nanosecondHeartbeat;
    }

    @Override
    public String toString() {
        return "HeartbeatResponse{" +
                "nanosecondHeartbeat=" + nanosecondHeartbeat +
                '}';
    }
}