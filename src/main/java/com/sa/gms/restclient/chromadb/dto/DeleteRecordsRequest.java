package com.sa.gms.restclient.chromadb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Request DTO for deleting records from a Chroma DB collection.
 * Can filter by IDs or metadata.
 * 
 * @see <a href="https://docs.trychroma.com/reference/api/Collection#delete">Chroma API Documentation</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteRecordsRequest {
    
    /**
     * Filter by metadata.
     * A string representing the metadata filter condition.
     */
    private String where;
    
    /**
     * Filter by document content.
     * A string representing the document content filter condition.
     */
    private String whereDocument;
    
    /**
     * List of IDs to delete.
     */
    private List<String> ids;
    
    /**
     * Maximum number of records to delete.
     */
    private Integer limit;

    /**
     * Default constructor.
     */
    public DeleteRecordsRequest() {
    }

    /**
     * Gets the metadata filter condition.
     *
     * @return the metadata filter condition
     */
    public String getWhere() {
        return where;
    }

    /**
     * Sets the metadata filter condition.
     *
     * @param where the metadata filter condition
     */
    public void setWhere(String where) {
        this.where = where;
    }

    /**
     * Gets the document content filter condition.
     *
     * @return the document content filter condition
     */
    public String getWhereDocument() {
        return whereDocument;
    }

    /**
     * Sets the document content filter condition.
     *
     * @param whereDocument the document content filter condition
     */
    public void setWhereDocument(String whereDocument) {
        this.whereDocument = whereDocument;
    }

    /**
     * Gets the list of IDs to delete.
     *
     * @return the list of IDs
     */
    public List<String> getIds() {
        return ids;
    }

    /**
     * Sets the list of IDs to delete.
     *
     * @param ids the list of IDs
     */
    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    /**
     * Gets the maximum number of records to delete.
     *
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Sets the maximum number of records to delete.
     *
     * @param limit the limit
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * Builder pattern for creating DeleteRecordsRequest instances.
     */
    public static class Builder {
        private String where;
        private String whereDocument;
        private List<String> ids;
        private Integer limit;

        /**
         * Default constructor.
         */
        public Builder() {
        }

        /**
         * Sets the metadata filter condition.
         *
         * @param where the metadata filter condition
         * @return this Builder instance
         */
        public Builder where(String where) {
            this.where = where;
            return this;
        }

        /**
         * Sets the document content filter condition.
         *
         * @param whereDocument the document content filter condition
         * @return this Builder instance
         */
        public Builder whereDocument(String whereDocument) {
            this.whereDocument = whereDocument;
            return this;
        }

        /**
         * Sets the list of IDs to delete.
         *
         * @param ids the list of IDs
         * @return this Builder instance
         */
        public Builder ids(List<String> ids) {
            this.ids = ids;
            return this;
        }

        /**
         * Sets the maximum number of records to delete.
         *
         * @param limit the limit
         * @return this Builder instance
         */
        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Builds the DeleteRecordsRequest instance.
         *
         * @return the DeleteRecordsRequest instance
         */
        public DeleteRecordsRequest build() {
            DeleteRecordsRequest request = new DeleteRecordsRequest();
            request.setWhere(where);
            request.setWhereDocument(whereDocument);
            request.setIds(ids);
            request.setLimit(limit);
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