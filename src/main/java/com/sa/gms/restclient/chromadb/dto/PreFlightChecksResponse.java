package com.sa.gms.restclient.chromadb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for pre-flight checks endpoint.
 * Contains basic readiness information.
 */
public class PreFlightChecksResponse {

    /**
     * The maximum batch size supported.
     */
    @JsonProperty("max_batch_size")
    private Long maxBatchSize;

    /**
     * Whether base64 encoding is supported.
     */
    @JsonProperty("supports_base64_encoding")
    private Boolean supportsBase64Encoding;

    /**
     * Default constructor.
     */
    public PreFlightChecksResponse() {
    }

    /**
     * Constructor with all fields.
     *
     * @param maxBatchSize the maximum batch size
     * @param supportsBase64Encoding whether base64 encoding is supported
     */
    public PreFlightChecksResponse(Long maxBatchSize, Boolean supportsBase64Encoding) {
        this.maxBatchSize = maxBatchSize;
        this.supportsBase64Encoding = supportsBase64Encoding;
    }

    /**
     * Gets the maximum batch size.
     *
     * @return the maximum batch size
     */
    public Long getMaxBatchSize() {
        return maxBatchSize;
    }

    /**
     * Sets the maximum batch size.
     *
     * @param maxBatchSize the maximum batch size to set
     */
    public void setMaxBatchSize(Long maxBatchSize) {
        this.maxBatchSize = maxBatchSize;
    }

    /**
     * Gets whether base64 encoding is supported.
     *
     * @return true if base64 encoding is supported, false otherwise
     */
    public Boolean getSupportsBase64Encoding() {
        return supportsBase64Encoding;
    }

    /**
     * Sets whether base64 encoding is supported.
     *
     * @param supportsBase64Encoding the base64 encoding support flag to set
     */
    public void setSupportsBase64Encoding(Boolean supportsBase64Encoding) {
        this.supportsBase64Encoding = supportsBase64Encoding;
    }

    @Override
    public String toString() {
        return "PreFlightChecksResponse{" +
                "maxBatchSize=" + maxBatchSize +
                ", supportsBase64Encoding=" + supportsBase64Encoding +
                '}';
    }
}