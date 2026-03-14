package com.sa.gms.restclient.chromadb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * DTO for collection response from Chroma DB.
 * Contains collection information including configuration, metadata, and schema.
 */
public class CollectionResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("tenant")
    private String tenant;

    @JsonProperty("database")
    private String database;

    @JsonProperty("dimension")
    private Long dimension;

    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    @JsonProperty("configuration_json")
    private ConfigurationJson configurationJson;

    @JsonProperty("schema")
    private Object schema;

    @JsonProperty("version")
    private Long version;

    @JsonProperty("log_position")
    private Long logPosition;

    /**
     * Inner class for configuration JSON.
     */
    public static class ConfigurationJson {
        @JsonProperty("embedding_function")
        private Object embeddingFunction;

        @JsonProperty("hnsw")
        private Object hnsw;

        @JsonProperty("spann")
        private Object spann;

        /**
         * Default constructor.
         */
        public ConfigurationJson() {
        }

        /**
         * Gets the embedding function.
         *
         * @return the embedding function
         */
        public Object getEmbeddingFunction() {
            return embeddingFunction;
        }

        /**
         * Sets the embedding function.
         *
         * @param embeddingFunction the embedding function
         */
        public void setEmbeddingFunction(Object embeddingFunction) {
            this.embeddingFunction = embeddingFunction;
        }

        /**
         * Gets the HNSW configuration.
         *
         * @return the HNSW configuration
         */
        public Object getHnsw() {
            return hnsw;
        }

        /**
         * Sets the HNSW configuration.
         *
         * @param hnsw the HNSW configuration
         */
        public void setHnsw(Object hnsw) {
            this.hnsw = hnsw;
        }

        /**
         * Gets the SPANN configuration.
         *
         * @return the SPANN configuration
         */
        public Object getSpann() {
            return spann;
        }

        /**
         * Sets the SPANN configuration.
         *
         * @param spann the SPANN configuration
         */
        public void setSpann(Object spann) {
            this.spann = spann;
        }
    }

    /**
     * Default constructor.
     */
    public CollectionResponse() {
    }

    /**
     * Gets the collection ID.
     *
     * @return the collection ID
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the collection ID.
     *
     * @param id the collection ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the collection name.
     *
     * @return the collection name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the collection name.
     *
     * @param name the collection name
     */
    public void setName(String name) {
        this.name = name;
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
     * Gets the database name.
     *
     * @return the database name
     */
    public String getDatabase() {
        return database;
    }

    /**
     * Sets the database name.
     *
     * @param database the database name
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     * Gets the dimension.
     *
     * @return the dimension
     */
    public Long getDimension() {
        return dimension;
    }

    /**
     * Sets the dimension.
     *
     * @param dimension the dimension
     */
    public void setDimension(Long dimension) {
        this.dimension = dimension;
    }

    /**
     * Gets the metadata.
     *
     * @return the metadata
     */
    public Map<String, Object> getMetadata() {
        return metadata;
    }

    /**
     * Sets the metadata.
     *
     * @param metadata the metadata
     */
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    /**
     * Gets the configuration JSON.
     *
     * @return the configuration JSON
     */
    public ConfigurationJson getConfigurationJson() {
        return configurationJson;
    }

    /**
     * Sets the configuration JSON.
     *
     * @param configurationJson the configuration JSON
     */
    public void setConfigurationJson(ConfigurationJson configurationJson) {
        this.configurationJson = configurationJson;
    }

    /**
     * Gets the schema.
     *
     * @return the schema
     */
    public Object getSchema() {
        return schema;
    }

    /**
     * Sets the schema.
     *
     * @param schema the schema
     */
    public void setSchema(Object schema) {
        this.schema = schema;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * Gets the log position.
     *
     * @return the log position
     */
    public Long getLogPosition() {
        return logPosition;
    }

    /**
     * Sets the log position.
     *
     * @param logPosition the log position
     */
    public void setLogPosition(Long logPosition) {
        this.logPosition = logPosition;
    }

    @Override
    public String toString() {
        return "CollectionResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", tenant='" + tenant + '\'' +
                ", database='" + database + '\'' +
                ", dimension=" + dimension +
                ", metadata=" + metadata +
                ", configurationJson=" + configurationJson +
                ", schema=" + schema +
                ", version=" + version +
                ", logPosition=" + logPosition +
                '}';
    }
}