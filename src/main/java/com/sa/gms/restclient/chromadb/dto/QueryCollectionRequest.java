package com.sa.gms.restclient.chromadb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Request DTO for querying a Chroma DB collection.
 * Supports dense vector search with metadata and full-text search filtering.
 * 
 * @see <a href="https://docs.trychroma.com/reference/api/Collection#query">Chroma API Documentation</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryCollectionRequest {
    
    /**
     * Fields to include in response.
     * Valid values: "documents", "metadatas", "distances", "embeddings", "uris"
     */
    private List<String> include;
    
    /**
     * Number of results to return.
     */
    private Integer nResults;
    
    /**
     * Query embeddings for vector search.
     * Each embedding is a list of floats representing a vector.
     */
    private List<List<Float>> queryEmbeddings;

    /**
     * Default constructor.
     */
    public QueryCollectionRequest() {
    }

    /**
     * Gets fields to include in response.
     *
     * @return list of fields to include
     */
    public List<String> getInclude() {
        return include;
    }

    /**
     * Sets fields to include in response.
     *
     * @param include list of fields to include
     */
    public void setInclude(List<String> include) {
        this.include = include;
    }

    /**
     * Gets number of results to return.
     *
     * @return number of results
     */
    public Integer getNResults() {
        return nResults;
    }

    /**
     * Sets number of results to return.
     *
     * @param nResults number of results
     */
    public void setNResults(Integer nResults) {
        this.nResults = nResults;
    }

    /**
     * Gets query embeddings for vector search.
     *
     * @return list of query embeddings
     */
    public List<List<Float>> getQueryEmbeddings() {
        return queryEmbeddings;
    }

    /**
     * Sets query embeddings for vector search.
     *
     * @param queryEmbeddings list of query embeddings
     */
    public void setQueryEmbeddings(List<List<Float>> queryEmbeddings) {
        this.queryEmbeddings = queryEmbeddings;
    }

    /**
     * Builder pattern for creating QueryCollectionRequest instances.
     */
    public static class Builder {
        private List<String> include;
        private Integer nResults;
        private List<List<Float>> queryEmbeddings;

        /**
         * Default constructor.
         */
        public Builder() {
        }

        /**
         * Sets fields to include in response.
         *
         * @param include list of fields to include
         * @return this Builder instance
         */
        public Builder include(List<String> include) {
            this.include = include;
            return this;
        }

        /**
         * Sets number of results to return.
         *
         * @param nResults number of results
         * @return this Builder instance
         */
        public Builder nResults(Integer nResults) {
            this.nResults = nResults;
            return this;
        }

        /**
         * Sets query embeddings for vector search.
         *
         * @param queryEmbeddings list of query embeddings
         * @return this Builder instance
         */
        public Builder queryEmbeddings(List<List<Float>> queryEmbeddings) {
            this.queryEmbeddings = queryEmbeddings;
            return this;
        }

        /**
         * Builds QueryCollectionRequest instance.
         *
         * @return QueryCollectionRequest instance
         */
        public QueryCollectionRequest build() {
            QueryCollectionRequest request = new QueryCollectionRequest();
            request.setInclude(include);
            request.setNResults(nResults);
            request.setQueryEmbeddings(queryEmbeddings);
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
