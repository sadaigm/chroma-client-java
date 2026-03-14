package com.sa.gms.restclient.chromadb.core;

import com.sa.gms.restclient.chromadb.config.ChromaProperties;
import com.sa.gms.restclient.chromadb.connection.IRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Implementation of IChromaClient using IRestClient.
 * Provides methods for interacting with Chroma DB API.
 */
@Component
public class ChromaClientImpl implements IChromaClient {

    private final IRestClient restClient;
    private final ChromaProperties chromaProperties;
    private String baseUrl;

    /**
     * Default constructor.
     * Initializes with default base URL.
     */
    public ChromaClientImpl() {
        this.restClient = null;
        this.chromaProperties = null;
        this.baseUrl = "http://localhost:8000";
    }

    /**
     * Constructor with IRestClient dependency injection.
     * Loads baseUrl from ChromaProperties configuration.
     *
     * @param restClient the REST client to use
     * @param chromaProperties the Chroma configuration properties
     */
    @Autowired
    public ChromaClientImpl(IRestClient restClient, ChromaProperties chromaProperties) {
        this.restClient = restClient;
        this.chromaProperties = chromaProperties;
        this.baseUrl = chromaProperties != null ? chromaProperties.getHost() : "http://localhost:8000";
    }

    /**
     * Constructor with IRestClient and custom base URL.
     *
     * @param restClient the REST client to use
     * @param baseUrl the base URL for Chroma DB API
     */
    public ChromaClientImpl(IRestClient restClient, String baseUrl) {
        this.restClient = restClient;
        this.chromaProperties = null;
        this.baseUrl = baseUrl;
    }

    /**
     * Sets the base URL for Chroma DB API.
     *
     * @param baseUrl the base URL
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Gets the base URL for Chroma DB API.
     *
     * @return the base URL
     */
    public String getBaseUrl() {
        return baseUrl;
    }



    @Override
    public ResponseEntity<String> createTenant(String tenantRequest, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants";
        return restClient.post(url, tenantRequest, headers);
    }

    @Override
    public ResponseEntity<String> getTenant(String tenantId, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenantId;
        return restClient.get(url, headers);
    }

    @Override
    public ResponseEntity<String> updateTenant(String tenantId, String updateRequest, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenantId;
        return restClient.patch(url, updateRequest, headers);
    }

    @Override
    public ResponseEntity<String> createDatabase(String tenantId, String dbRequest, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenantId + "/databases";
        return restClient.post(url, dbRequest, headers);
    }

    @Override
    public ResponseEntity<String> listDatabases(String tenantId, String queryParams, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenantId + "/databases";
        return restClient.get(url, queryParams, headers);
    }

    @Override
    public ResponseEntity<String> getDatabase(String tenantId, String databaseName, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenantId + "/databases/" + databaseName;
        return restClient.get(url, headers);
    }

    @Override
    public ResponseEntity<String> deleteDatabase(String tenantId, String databaseName, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenantId + "/databases/" + databaseName;
        return restClient.delete(url, headers);
    }

    @Override
    public ResponseEntity<String> createCollection(String tenant, String database, String collectionRequest, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenant + "/databases/" + database + "/collections";
        return restClient.post(url, collectionRequest, headers);
    }

    @Override
    public ResponseEntity<String> createCollection(String collectionRequest, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/collections";
        return restClient.post(url, collectionRequest, headers);
    }

    @Override
    public ResponseEntity<String> getCollectionByCrn(String crn, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/collections/" + crn;
        return restClient.get(url, headers);
    }

    @Override
    public ResponseEntity<String> getCollection(String tenant, String database, String collectionId, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenant + "/databases/" + database + "/collections/" + collectionId;
        return restClient.get(url, headers);
    }

    @Override
    public ResponseEntity<String> updateCollection(String tenant, String database, String collectionId, String updateRequest, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenant + "/databases/" + database + "/collections/" + collectionId;
        return restClient.put(url, updateRequest, headers);
    }

    @Override
    public ResponseEntity<String> deleteCollection(String tenant, String database, String collectionId, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenant + "/databases/" + database + "/collections/" + collectionId;
        return restClient.delete(url, headers);
    }

    @Override
    public ResponseEntity<String> forkCollection(String tenant, String database, String collectionId, String forkRequest, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenant + "/databases/" + database + "/collections/" + collectionId + "/fork";
        return restClient.post(url, forkRequest, headers);
    }

    @Override
    public ResponseEntity<String> getCollectionsCount(String tenant, String database, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenant + "/databases/" + database + "/collections_count";
        return restClient.get(url, headers);
    }

    @Override
    public ResponseEntity<String> getCollection(String collectionId, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/collections/" + collectionId;
        return restClient.get(url, headers);
    }

    @Override
    public ResponseEntity<String> listCollections(String tenant, String database, String queryParams, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenant + "/databases/" + database + "/collections";
        return restClient.get(url, queryParams, headers);
    }

    @Override
    public ResponseEntity<String> listCollections(String queryParams, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/collections";
        return restClient.get(url, queryParams, headers);
    }

    @Override
    public ResponseEntity<String> countCollection(String collectionId, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/collections/" + collectionId + "/count";
        return restClient.get(url, headers);
    }

    @Override
    public ResponseEntity<String> deleteCollection(String collectionId, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/collections/" + collectionId;
        return restClient.delete(url, headers);
    }
    @Override
    public ResponseEntity<String> addRecords(String tenantId, String databaseName, String collectionId, String recordsRequest, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenantId + "/databases/" + databaseName + "/collections/" + collectionId + "/add";
        return restClient.post(url, recordsRequest, headers);
    }

    @Override
    public ResponseEntity<String> countRecords(String tenantId, String databaseName, String collectionId, String queryParams, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenantId + "/databases/" + databaseName + "/collections/" + collectionId + "/count";
        return restClient.get(url, queryParams, headers);
    }

    @Override
    public ResponseEntity<String> deleteRecords(String tenantId, String databaseName, String collectionId, String deleteRequest, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenantId + "/databases/" + databaseName + "/collections/" + collectionId + "/delete";
        return restClient.post(url, deleteRequest, headers);
    }

    @Override
    public ResponseEntity<String> getRecords(String tenantId, String databaseName, String collectionId, String getRequest, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenantId + "/databases/" + databaseName + "/collections/" + collectionId + "/get";
        return restClient.post(url, getRequest, headers);
    }

    @Override
    public ResponseEntity<String> getIndexingStatus(String tenantId, String databaseName, String collectionId, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenantId + "/databases/" + databaseName + "/collections/" + collectionId + "/indexing_status";
        return restClient.get(url, headers);
    }

    @Override
    public ResponseEntity<String> queryCollection(String tenantId, String databaseName, String collectionId, String queryParams, String queryRequest, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenantId + "/databases/" + databaseName + "/collections/" + collectionId + "/query";
        if (queryParams != null && !queryParams.isEmpty()) {
            url += "?" + queryParams;
        }
        return restClient.post(url, queryRequest, headers);
    }

    @Override
    public ResponseEntity<String> searchRecords(String tenantId, String databaseName, String collectionId, String requestBody, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenantId + "/databases/" + databaseName + "/collections/" + collectionId + "/search";
        return restClient.post(url, requestBody, headers);
    }

    @Override
    public ResponseEntity<String> updateRecords(String tenantId, String databaseName, String collectionId, String requestBody, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenantId + "/databases/" + databaseName + "/collections/" + collectionId + "/update";
        return restClient.post(url, requestBody, headers);
    }

    @Override
    public ResponseEntity<String> upsertRecords(String tenantId, String databaseName, String collectionId, String requestBody, java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/tenants/" + tenantId + "/databases/" + databaseName + "/collections/" + collectionId + "/upsert";
        return restClient.post(url, requestBody, headers);
    }

    @Override
    public ResponseEntity<String> getIdentity(java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/auth/identity";
        return restClient.get(url, headers);
    }

    @Override
    public ResponseEntity<String> healthcheck(java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/healthcheck";
        return restClient.get(url, headers);
    }

    @Override
    public ResponseEntity<String> heartbeat(java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/heartbeat";
        return restClient.get(url, headers);
    }

    @Override
    public ResponseEntity<String> reset(java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/reset";
        return restClient.post(url, null, headers);
    }

    @Override
    public ResponseEntity<String> getVersion(java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/version";
        return restClient.get(url, headers);
    }

    @Override
    public ResponseEntity<String> preFlightChecks(java.util.Map<String, String> headers) {
        String url = baseUrl + "/api/v2/pre-flight-checks";
        return restClient.get(url, headers);
    }
}