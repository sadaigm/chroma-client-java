package com.sa.gms.restclient.chromadb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Request DTO for searching records in a Chroma DB collection.
 * Supports dense, sparse, or hybrid vector search.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchRecordsRequest {

    /**
     * Read level for the search operation.
     * Valid values: "IndexAndWal", "Index", "Wal"
     */
    @JsonProperty("read_level")
    private String readLevel;

    /**
     * List of search queries to execute.
     */
    @JsonProperty("searches")
    private List<SearchQuery> searches;

    /**
     * Represents a single search query.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SearchQuery {

        /**
         * Number of results to return for this query.
         */
        @JsonProperty("n_results")
        private Integer nResults;

        /**
         * Query embeddings for dense vector search.
         * List of embedding vectors, where each vector is a list of floats.
         */
        @JsonProperty("query_embeddings")
        private List<List<Float>> queryEmbeddings;

        /**
         * Query texts for text-based search.
         */
        @JsonProperty("query_texts")
        private List<String> queryTexts;

        /**
         * Where clause for metadata filtering.
         */
        @JsonProperty("where")
        private Object where;

        /**
         * Where document clause for document content filtering.
         */
        @JsonProperty("where_document")
        private Object whereDocument;

        /**
         * Default constructor.
         */
        public SearchQuery() {
        }

        /**
         * Gets number of results to return for this query.
         *
         * @return number of results
         */
        public Integer getNResults() {
            return nResults;
        }

        /**
         * Sets number of results to return for this query.
         *
         * @param nResults number of results
         */
        public void setNResults(Integer nResults) {
            this.nResults = nResults;
        }

        /**
         * Gets query embeddings for dense vector search.
         *
         * @return list of embedding vectors
         */
        public List<List<Float>> getQueryEmbeddings() {
            return queryEmbeddings;
        }

        /**
         * Sets query embeddings for dense vector search.
         *
         * @param queryEmbeddings list of embedding vectors
         */
        public void setQueryEmbeddings(List<List<Float>> queryEmbeddings) {
            this.queryEmbeddings = queryEmbeddings;
        }

        /**
         * Gets query texts for text-based search.
         *
         * @return list of query texts
         */
        public List<String> getQueryTexts() {
            return queryTexts;
        }

        /**
         * Sets query texts for text-based search.
         *
         * @param queryTexts list of query texts
         */
        public void setQueryTexts(List<String> queryTexts) {
            this.queryTexts = queryTexts;
        }

        /**
         * Gets where clause for metadata filtering.
         *
         * @return where clause
         */
        public Object getWhere() {
            return where;
        }

        /**
         * Sets where clause for metadata filtering.
         *
         * @param where where clause
         */
        public void setWhere(Object where) {
            this.where = where;
        }

        /**
         * Gets where document clause for document content filtering.
         *
         * @return where document clause
         */
        public Object getWhereDocument() {
            return whereDocument;
        }

        /**
         * Sets where document clause for document content filtering.
         *
         * @param whereDocument where document clause
         */
        public void setWhereDocument(Object whereDocument) {
            this.whereDocument = whereDocument;
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
         * Builder pattern for creating SearchQuery instances.
         */
        public static class Builder {
            private SearchQuery query = new SearchQuery();

            /**
             * Default constructor.
             */
            public Builder() {
            }

            /**
             * Sets number of results to return for this query.
             *
             * @param nResults number of results
             * @return this Builder instance
             */
            public Builder nResults(Integer nResults) {
                query.setNResults(nResults);
                return this;
            }

            /**
             * Sets query embeddings for dense vector search.
             *
             * @param queryEmbeddings list of embedding vectors
             * @return this Builder instance
             */
            public Builder queryEmbeddings(List<List<Float>> queryEmbeddings) {
                query.setQueryEmbeddings(queryEmbeddings);
                return this;
            }

            /**
             * Sets query texts for text-based search.
             *
             * @param queryTexts list of query texts
             * @return this Builder instance
             */
            public Builder queryTexts(List<String> queryTexts) {
                query.setQueryTexts(queryTexts);
                return this;
            }

            /**
             * Sets where clause for metadata filtering.
             *
             * @param where where clause
             * @return this Builder instance
             */
            public Builder where(Object where) {
                query.setWhere(where);
                return this;
            }

            /**
             * Sets where document clause for document content filtering.
             *
             * @param whereDocument where document clause
             * @return this Builder instance
             */
            public Builder whereDocument(Object whereDocument) {
                query.setWhereDocument(whereDocument);
                return this;
            }

            /**
             * Builds SearchQuery instance.
             *
             * @return SearchQuery instance
             */
            public SearchQuery build() {
                return query;
            }
        }
    }

    /**
     * Default constructor.
     */
    public SearchRecordsRequest() {
    }

    /**
     * Gets read level for the search operation.
     *
     * @return read level
     */
    public String getReadLevel() {
        return readLevel;
    }

    /**
     * Sets read level for the search operation.
     *
     * @param readLevel read level
     */
    public void setReadLevel(String readLevel) {
        this.readLevel = readLevel;
    }

    /**
     * Gets list of search queries to execute.
     *
     * @return list of search queries
     */
    public List<SearchQuery> getSearches() {
        return searches;
    }

    /**
     * Sets list of search queries to execute.
     *
     * @param searches list of search queries
     */
    public void setSearches(List<SearchQuery> searches) {
        this.searches = searches;
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
     * Builder pattern for creating SearchRecordsRequest instances.
     */
    public static class Builder {
        private SearchRecordsRequest request = new SearchRecordsRequest();

        /**
         * Default constructor.
         */
        public Builder() {
        }

        /**
         * Sets read level for the search operation.
         *
         * @param readLevel read level
         * @return this Builder instance
         */
        public Builder readLevel(String readLevel) {
            request.setReadLevel(readLevel);
            return this;
        }

        /**
         * Sets list of search queries to execute.
         *
         * @param searches list of search queries
         * @return this Builder instance
         */
        public Builder searches(List<SearchQuery> searches) {
            request.setSearches(searches);
            return this;
        }

        /**
         * Builds SearchRecordsRequest instance.
         *
         * @return SearchRecordsRequest instance
         */
        public SearchRecordsRequest build() {
            return request;
        }
    }
}
