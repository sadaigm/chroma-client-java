package com.sa.gms.restclient.chromadb.core;

import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * Interface for Chroma DB operations.
 * Provides methods for tenant, database, collection, and document management.
 */
public interface IChromaClient {

    // ==================== Tenant Operations ====================

    /**
     * Creates a new tenant.
     *
     * @param tenantRequest the tenant creation request as String
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response
     */
    ResponseEntity<String> createTenant(String tenantRequest, Map<String, String> headers);

    /**
     * Gets tenant information.
     *
     * @param tenantId the tenant identifier
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the tenant information
     */
    ResponseEntity<String> getTenant(String tenantId,Map<String, String> headers);

    /**
     * Updates an existing tenant.
     *
     * @param tenantId the tenant identifier
     * @param updateRequest the update request as String
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response
     */
    ResponseEntity<String> updateTenant(String tenantId, String updateRequest,Map<String, String> headers);

    // ==================== Database Operations ====================

    /**
     * Creates a new database for a tenant.
     *
     * @param tenantId the tenant identifier
     * @param dbRequest the database creation request as String
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response
     */
    ResponseEntity<String> createDatabase(String tenantId, String dbRequest, Map<String, String> headers);

    /**
     * Lists all databases for a tenant.
     *
     * @param tenantId the tenant identifier
     * @param queryParams optional query parameters for pagination (e.g., "limit=10&amp;offset=0").
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the list of databases
     */
    ResponseEntity<String> listDatabases(String tenantId, String queryParams, Map<String, String> headers);

    /**
     * Gets database information.
     *
     * @param tenantId the tenant identifier
     * @param databaseName the database name
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the database information
     */
    ResponseEntity<String> getDatabase(String tenantId, String databaseName, Map<String, String> headers);

    /**
     * Deletes a database.
     *
     * @param tenantId the tenant identifier
     * @param databaseName the database name
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response
     */
    ResponseEntity<String> deleteDatabase(String tenantId, String databaseName, Map<String, String> headers);

    // ==================== Collection Operations ====================

    /**
     * Creates a new collection in a specific tenant and database.
     *
     * @param tenant the tenant UUID
     * @param database the database name
     * @param collectionRequest the collection creation request as String
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response
     */
    ResponseEntity<String> createCollection(String tenant, String database, String collectionRequest, Map<String, String> headers);

    /**
     * Creates a new collection.
     *
     * @param collectionRequest the collection creation request as String
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response
     */
    ResponseEntity<String> createCollection(String collectionRequest, Map<String, String> headers);

    /**
     * Gets collection information by Chroma Resource Name (CRN).
     * CRN format: tenant:database:collection
     *
     * @param crn the Chroma Resource Name
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the collection information
     */
    ResponseEntity<String> getCollectionByCrn(String crn, Map<String, String> headers);

    /**
     * Gets collection information by tenant, database, and collection ID.
     *
     * @param tenant the tenant UUID
     * @param database the database name
     * @param collectionId the collection UUID
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the collection information
     */
    ResponseEntity<String> getCollection(String tenant, String database, String collectionId, Map<String, String> headers);

    /**
     * Updates an existing collection's name or metadata.
     *
     * @param tenant the tenant identifier
     * @param database the database name
     * @param collectionId the collection UUID
     * @param updateRequest the update request as String
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response
     */
    ResponseEntity<String> updateCollection(String tenant, String database, String collectionId, String updateRequest, Map<String, String> headers);

    /**
     * Deletes a collection in a specific tenant and database.
     *
     * @param tenant the tenant UUID
     * @param database the database name
     * @param collectionId the collection UUID
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response
     */
    ResponseEntity<String> deleteCollection(String tenant, String database, String collectionId, Map<String, String> headers);

    /**
     * Forks an existing collection in a specific tenant and database.
     *
     * @param tenant the tenant UUID
     * @param database the database name
     * @param collectionId the collection UUID
     * @param forkRequest the fork request as String
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response
     */
    ResponseEntity<String> forkCollection(String tenant, String database, String collectionId, String forkRequest, Map<String, String> headers);

    /**
     * Gets the total number of collections in a specific tenant and database.
     *
     * @param tenant the tenant UUID
     * @param database the database name
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the count
     */
    ResponseEntity<String> getCollectionsCount(String tenant, String database, Map<String, String> headers);

    /**
     * Gets collection information.
     *
     * @param collectionId the collection identifier
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the collection information
     */
    ResponseEntity<String> getCollection(String collectionId, Map<String, String> headers);

    /**
     * Lists all collections in a specific tenant and database.
     *
     * @param tenant the tenant UUID
     * @param database the database name
     * @param queryParams optional query parameters for pagination (e.g., "limit=10&amp;offset=0").
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the list of collections
     */
    ResponseEntity<String> listCollections(String tenant, String database, String queryParams, Map<String, String> headers);

    /**
     * Lists all collections.
     *
     * @param queryParams optional query parameters for filtering.
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the list of collections
     */
    ResponseEntity<String> listCollections(String queryParams, Map<String, String> headers);

    /**
     * Counts the number of items in a collection.
     *
     * @param collectionId the collection identifier
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the count
     */
    ResponseEntity<String> countCollection(String collectionId, Map<String, String> headers);

    /**
     * Deletes a collection.
     *
     * @param collectionId the collection identifier
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response
     */
    ResponseEntity<String> deleteCollection(String collectionId, Map<String, String> headers);

    // ==================== Document Operations ====================

    /**
     * Adds records to a collection.
     *
     * @param tenantId the tenant identifier
     * @param databaseName the database name
     * @param collectionId the collection identifier
     * @param recordsRequest the records addition request as String
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response
     */
    ResponseEntity<String> addRecords(String tenantId, String databaseName, String collectionId, String recordsRequest, Map<String, String> headers);

    /**
     * Counts the number of records in a collection.
     *
     * @param tenantId the tenant identifier
     * @param databaseName the database name
     * @param collectionId the collection identifier
     * @param queryParams optional query parameters (e.g., "read_level=index_and_wal").
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the count
     */
    ResponseEntity<String> countRecords(String tenantId, String databaseName, String collectionId, String queryParams, Map<String, String> headers);

    /**
     * Deletes records from a collection.
     *
     * @param tenantId the tenant identifier
     * @param databaseName the database name
     * @param collectionId the collection identifier
     * @param deleteRequest the delete request as String
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response
     */
    ResponseEntity<String> deleteRecords(String tenantId, String databaseName, String collectionId, String deleteRequest, Map<String, String> headers);

    /**
     * Gets records from a collection.
     *
     * @param tenantId the tenant identifier
     * @param databaseName the database name
     * @param collectionId the collection identifier
     * @param getRequest the get request as String
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the records
     */
    ResponseEntity<String> getRecords(String tenantId, String databaseName, String collectionId, String getRequest, Map<String, String> headers);

    /**
     * Gets indexing status of a collection.
     *
     * @param tenantId * tenant identifier
     * @param databaseName * database name
     * @param collectionId * collection identifier
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing indexing status
     */
    ResponseEntity<String> getIndexingStatus(String tenantId, String databaseName, String collectionId, Map<String, String> headers);

    /**
     * Queries a collection using dense vector search.
     *
     * @param tenantId * tenant identifier
     * @param databaseName * database name
     * @param collectionId * collection identifier
     * @param queryParams optional query parameters for pagination (e.g., "limit=10&amp;offset=0").
     * @param queryRequest * query request as String
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing query results
     */
    ResponseEntity<String> queryCollection(String tenantId, String databaseName, String collectionId, String queryParams, String queryRequest, Map<String, String> headers);

    /**
     * Searches records from a collection with dense, sparse, or hybrid vector search.
     *
     * @param tenantId * tenant identifier
     * @param databaseName * database name
     * @param collectionId * collection identifier
     * @param requestBody * search request body as JSON string
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing search results
     */
    ResponseEntity<String> searchRecords(String tenantId, String databaseName, String collectionId, String requestBody, Map<String, String> headers);

    /**
     * Updates records in a collection by ID.
     *
     * @param tenantId * tenant identifier
     * @param databaseName * database name
     * @param collectionId * collection identifier
     * @param requestBody * update request body as JSON string
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing update response
     */
    ResponseEntity<String> updateRecords(String tenantId, String databaseName, String collectionId, String requestBody, Map<String, String> headers);

    /**
     * Upserts records in a collection (create if not exists, otherwise update).
     *
     * @param tenantId * tenant identifier
     * @param databaseName * database name
     * @param collectionId * collection identifier
     * @param requestBody * upsert request body as JSON string
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing upsert response
     */
    ResponseEntity<String> upsertRecords(String tenantId, String databaseName, String collectionId, String requestBody, Map<String, String> headers);

    // ==================== Authentication Operations ====================

    /**
     * Gets the current user's identity, tenant, and databases.
     *
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the user identity information
     */
    ResponseEntity<String> getIdentity(Map<String, String> headers);

    // ==================== System Operations ====================

    /**
     * Gets the health status of the Chroma DB service.
     *
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the health status
     */
    ResponseEntity<String> healthcheck(Map<String, String> headers);

    /**
     * Gets the heartbeat timestamp in nanoseconds.
     *
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the nanosecond timestamp
     */
    ResponseEntity<String> heartbeat(Map<String, String> headers);

    /**
     * Gets basic readiness information.
     *
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the pre-flight checks
     */
    ResponseEntity<String> preFlightChecks(Map<String, String> headers);

    /**
     * Gets the version of the Chroma DB server.
     *
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the server version
     */
    ResponseEntity<String> getVersion(Map<String, String> headers);

    /**
     * Resets the Chroma DB instance.
     *
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the reset response
     */
    ResponseEntity<String> reset(Map<String, String> headers);
}