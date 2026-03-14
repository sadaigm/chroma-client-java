package com.sa.gms.restclient.chromadb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

/**
 * Request DTO for adding records to a Chroma DB collection.
 * 
 * @see <a href="https://docs.trychroma.com/reference/api/Collection#add">Chroma API Documentation</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddRecordsRequest {
    
    /**
     * The documents to add. Must be the same length as ids.
     */
    private List<String> documents;
    
    /**
     * The embeddings to add. Must be the same length as ids.
     * Each embedding is a list of floats.
     */
    private List<List<Float>> embeddings;
    
    /**
     * The IDs of the documents to add. Required.
     */
    private List<String> ids;
    
    /**
     * The metadata to add. Must be the same length as ids.
     * Each metadata entry is a map of string keys to values.
     */
    private List<Map<String, Object>> metadatas;
    
    /**
     * The URIs of the documents to add. Must be the same length as ids.
     */
    private List<String> uris;

    /**
     * Default constructor.
     */
    public AddRecordsRequest() {
    }

    /**
     * Constructor with IDs.
     *
     * @param ids the IDs of the documents to add
     */
    public AddRecordsRequest(List<String> ids) {
        this.ids = ids;
    }

    /**
     * Gets the documents to add.
     *
     * @return list of documents
     */
    public List<String> getDocuments() {
        return documents;
    }

    /**
     * Sets the documents to add.
     *
     * @param documents list of documents
     */
    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    /**
     * Gets the embeddings to add.
     *
     * @return list of embeddings
     */
    public List<List<Float>> getEmbeddings() {
        return embeddings;
    }

    /**
     * Sets the embeddings to add.
     *
     * @param embeddings list of embeddings
     */
    public void setEmbeddings(List<List<Float>> embeddings) {
        this.embeddings = embeddings;
    }

    /**
     * Gets the IDs of the documents to add.
     *
     * @return list of IDs
     */
    public List<String> getIds() {
        return ids;
    }

    /**
     * Sets the IDs of the documents to add.
     *
     * @param ids list of IDs
     */
    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    /**
     * Gets the metadata to add.
     *
     * @return list of metadata maps
     */
    public List<Map<String, Object>> getMetadatas() {
        return metadatas;
    }

    /**
     * Sets the metadata to add.
     *
     * @param metadatas list of metadata maps
     */
    public void setMetadatas(List<Map<String, Object>> metadatas) {
        this.metadatas = metadatas;
    }

    /**
     * Gets the URIs of the documents to add.
     *
     * @return list of URIs
     */
    public List<String> getUris() {
        return uris;
    }

    /**
     * Sets the URIs of the documents to add.
     *
     * @param uris list of URIs
     */
    public void setUris(List<String> uris) {
        this.uris = uris;
    }

    /**
     * Builder pattern for creating AddRecordsRequest instances.
     */
    public static class Builder {
        private List<String> documents;
        private List<List<Float>> embeddings;
        private List<String> ids;
        private List<Map<String, Object>> metadatas;
        private List<String> uris;

        /**
         * Default constructor.
         */
        public Builder() {
        }

        /**
         * Sets the documents to add.
         *
         * @param documents list of documents
         * @return this Builder instance
         */
        public Builder documents(List<String> documents) {
            this.documents = documents;
            return this;
        }

        /**
         * Sets the embeddings to add.
         *
         * @param embeddings list of embeddings
         * @return this Builder instance
         */
        public Builder embeddings(List<List<Float>> embeddings) {
            this.embeddings = embeddings;
            return this;
        }

        /**
         * Sets the IDs of the documents to add.
         *
         * @param ids list of IDs
         * @return this Builder instance
         */
        public Builder ids(List<String> ids) {
            this.ids = ids;
            return this;
        }

        /**
         * Sets the metadata to add.
         *
         * @param metadatas list of metadata maps
         * @return this Builder instance
         */
        public Builder metadatas(List<Map<String, Object>> metadatas) {
            this.metadatas = metadatas;
            return this;
        }

        /**
         * Sets the URIs of the documents to add.
         *
         * @param uris list of URIs
         * @return this Builder instance
         */
        public Builder uris(List<String> uris) {
            this.uris = uris;
            return this;
        }

        /**
         * Builds AddRecordsRequest instance.
         *
         * @return AddRecordsRequest instance
         */
        public AddRecordsRequest build() {
            AddRecordsRequest request = new AddRecordsRequest();
            request.setDocuments(documents);
            request.setEmbeddings(embeddings);
            request.setIds(ids);
            request.setMetadatas(metadatas);
            request.setUris(uris);
            return request;
        }
    }

    /**
     * Creates a new Builder instance.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }
}
