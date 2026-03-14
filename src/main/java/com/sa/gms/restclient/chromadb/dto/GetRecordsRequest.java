package com.sa.gms.restclient.chromadb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Request DTO for getting records from a Chroma DB collection.
 * Can filter by IDs or metadata.
 * 
 * @see <a href="https://docs.trychroma.com/reference/api/Collection#get">Chroma API Documentation</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetRecordsRequest {
    
    /**
     * Filter by metadata.
     * A string representing a metadata filter condition.
     */
    private String where;
    
    /**
     * Filter by document content.
     * A string representing a document content filter condition.
     */
    private String whereDocument;
    
    /**
     * List of IDs to retrieve.
     */
    private List<String> ids;
    
    /**
     * Fields to include in the response.
     * Valid values: "documents", "metadatas", "embeddings", "distances", "uris"
     */
    private List<String> include;
    
    /**
     * Maximum number of records to return.
     */
    private Integer limit;
    
    /**
     * Number of records to skip.
     */
    private Integer offset;

    /**
     * Default constructor.
     */
    public GetRecordsRequest() {
    }

    /**
     * Gets metadata filter condition.
     *
     * @return metadata filter condition
     */
    public String getWhere() {
        return where;
    }

    /**
     * Sets metadata filter condition.
     *
     * @param where metadata filter condition
     */
    public void setWhere(String where) {
        this.where = where;
    }

    /**
     * Gets document content filter condition.
     *
     * @return document content filter condition
     */
    public String getWhereDocument() {
        return whereDocument;
    }

    /**
     * Sets document content filter condition.
     *
     * @param whereDocument document content filter condition
     */
    public void setWhereDocument(String whereDocument) {
        this.whereDocument = whereDocument;
    }

    /**
     * Gets list of IDs to retrieve.
     *
     * @return list of IDs
     */
    public List<String> getIds() {
        return ids;
    }

    /**
     * Sets list of IDs to retrieve.
     *
     * @param ids list of IDs
     */
    public void setIds(List<String> ids) {
        this.ids = ids;
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
     * Gets maximum number of records to return.
     *
     * @return limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Sets maximum number of records to return.
     *
     * @param limit limit
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * Gets number of records to skip.
     *
     * @return offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * Sets number of records to skip.
     *
     * @param offset offset
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * Builder pattern for creating GetRecordsRequest instances.
     */
    public static class Builder {
        private String where;
        private String whereDocument;
        private List<String> ids;
        private List<String> include;
        private Integer limit;
        private Integer offset;

        /**
         * Default constructor.
         */
        public Builder() {
        }

        /**
         * Sets metadata filter condition.
         *
         * @param where metadata filter condition
         * @return this Builder instance
         */
        public Builder where(String where) {
            this.where = where;
            return this;
        }

        /**
         * Sets document content filter condition.
         *
         * @param whereDocument document content filter condition
         * @return this Builder instance
         */
        public Builder whereDocument(String whereDocument) {
            this.whereDocument = whereDocument;
            return this;
        }

        /**
         * Sets list of IDs to retrieve.
         *
         * @param ids list of IDs
         * @return this Builder instance
         */
        public Builder ids(List<String> ids) {
            this.ids = ids;
            return this;
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
         * Sets maximum number of records to return.
         *
         * @param limit limit
         * @return this Builder instance
         */
        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Sets number of records to skip.
         *
         * @param offset offset
         * @return this Builder instance
         */
        public Builder offset(Integer offset) {
            this.offset = offset;
            return this;
        }

        /**
         * Builds GetRecordsRequest instance.
         *
         * @return GetRecordsRequest instance
         */
        public GetRecordsRequest build() {
            GetRecordsRequest request = new GetRecordsRequest();
            request.setWhere(where);
            request.setWhereDocument(whereDocument);
            request.setIds(ids);
            request.setInclude(include);
            request.setLimit(limit);
            request.setOffset(offset);
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