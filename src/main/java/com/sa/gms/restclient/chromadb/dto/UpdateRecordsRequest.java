package com.sa.gms.restclient.chromadb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Request DTO for updating records in a Chroma DB collection.
 * Updates records by ID with new documents, embeddings, metadatas, or URIs.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateRecordsRequest {

    /**
     * List of document texts to update.
     */
    @JsonProperty("documents")
    private List<String> documents;

    /**
     * List of embedding vectors to update.
     * Each embedding is a list of floats.
     */
    @JsonProperty("embeddings")
    private List<List<Float>> embeddings;

    /**
     * List of record IDs to update.
     * This field is required.
     */
    @JsonProperty("ids")
    private List<String> ids;

    /**
     * List of metadata objects to update.
     * Each metadata is a map of string keys to values.
     */
    @JsonProperty("metadatas")
    private List<Object> metadatas;

    /**
     * List of URIs to update.
     */
    @JsonProperty("uris")
    private List<String> uris;

    /**
     * Default constructor.
     */
    public UpdateRecordsRequest() {
    }

    /**
     * Gets list of document texts to update.
     *
     * @return list of document texts
     */
    public List<String> getDocuments() {
        return documents;
    }

    /**
     * Sets list of document texts to update.
     *
     * @param documents list of document texts
     */
    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    /**
     * Gets list of embedding vectors to update.
     *
     * @return list of embedding vectors
     */
    public List<List<Float>> getEmbeddings() {
        return embeddings;
    }

    /**
     * Sets list of embedding vectors to update.
     *
     * @param embeddings list of embedding vectors
     */
    public void setEmbeddings(List<List<Float>> embeddings) {
        this.embeddings = embeddings;
    }

    /**
     * Gets list of record IDs to update.
     *
     * @return list of record IDs
     */
    public List<String> getIds() {
        return ids;
    }

    /**
     * Sets list of record IDs to update.
     *
     * @param ids list of record IDs
     */
    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    /**
     * Gets list of metadata objects to update.
     *
     * @return list of metadata objects
     */
    public List<Object> getMetadatas() {
        return metadatas;
    }

    /**
     * Sets list of metadata objects to update.
     *
     * @param metadatas list of metadata objects
     */
    public void setMetadatas(List<Object> metadatas) {
        this.metadatas = metadatas;
    }

    /**
     * Gets list of URIs to update.
     *
     * @return list of URIs
     */
    public List<String> getUris() {
        return uris;
    }

    /**
     * Sets list of URIs to update.
     *
     * @param uris list of URIs
     */
    public void setUris(List<String> uris) {
        this.uris = uris;
    }

    /**
     * Creates a new Builder instance.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder pattern for creating UpdateRecordsRequest instances.
     */
    public static class Builder {
        private UpdateRecordsRequest request = new UpdateRecordsRequest();

        /**
         * Default constructor.
         */
        public Builder() {
        }

        /**
         * Sets list of document texts to update.
         *
         * @param documents list of document texts
         * @return this Builder instance
         */
        public Builder documents(List<String> documents) {
            request.setDocuments(documents);
            return this;
        }

        /**
         * Sets list of embedding vectors to update.
         *
         * @param embeddings list of embedding vectors
         * @return this Builder instance
         */
        public Builder embeddings(List<List<Float>> embeddings) {
            request.setEmbeddings(embeddings);
            return this;
        }

        /**
         * Sets list of record IDs to update.
         *
         * @param ids list of record IDs
         * @return this Builder instance
         */
        public Builder ids(List<String> ids) {
            request.setIds(ids);
            return this;
        }

        /**
         * Sets list of metadata objects to update.
         *
         * @param metadatas list of metadata objects
         * @return this Builder instance
         */
        public Builder metadatas(List<Object> metadatas) {
            request.setMetadatas(metadatas);
            return this;
        }

        /**
         * Sets list of URIs to update.
         *
         * @param uris list of URIs
         * @return this Builder instance
         */
        public Builder uris(List<String> uris) {
            request.setUris(uris);
            return this;
        }

        /**
         * Builds UpdateRecordsRequest instance.
         *
         * @return UpdateRecordsRequest instance
         */
        public UpdateRecordsRequest build() {
            return request;
        }
    }
}
