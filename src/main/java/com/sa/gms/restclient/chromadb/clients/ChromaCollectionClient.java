package com.sa.gms.restclient.chromadb.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.gms.restclient.chromadb.core.IChromaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Service facade for Chroma DB collection operations.
 * Provides a clean API for collection-related operations.
 */
@Service
public class ChromaCollectionClient {

    private final IChromaClient chromaClient;
    private final ObjectMapper objectMapper;

    /**
     * Constructor-based dependency injection of IChromaClient implementation.
     *
     * @param chromaClient the Chroma DB client implementation
     */
    @Autowired
    public ChromaCollectionClient(IChromaClient chromaClient) {
        this.chromaClient = chromaClient;
        this.objectMapper = new ObjectMapper();
    }

    // ==================== Collection Operations ====================

    /**
     * Creates a new collection in a specific tenant and database.
     *
     * @param tenant the tenant UUID
     * @param database the database name
     * @param collectionRequest the collection creation request as String
     * @return ResponseEntity containing the response
     */
    public ResponseEntity<String> createCollection(String tenant, String database, String collectionRequest) {
        return chromaClient.createCollection(tenant, database, collectionRequest, new HashMap<>());
    }

    /**
     * Creates a new collection.
     *
     * @param collectionRequest the collection creation request as String
     * @return ResponseEntity containing the response
     */
    public ResponseEntity<String> createCollection(String collectionRequest) {
        return chromaClient.createCollection(collectionRequest, new HashMap<>());
    }

    /**
     * Gets collection information by Chroma Resource Name (CRN).
     * CRN format: tenant:database:collection
     *
     * @param crn the Chroma Resource Name
     * @return ResponseEntity containing the collection information
     */
    public ResponseEntity<String> getCollectionByCrn(String crn) {
        return chromaClient.getCollectionByCrn(crn, new HashMap<>());
    }

    /**
     * Gets collection information by tenant, database, and collection ID.
     *
     * @param tenant the tenant UUID
     * @param database the database name
     * @param collectionId the collection UUID
     * @return ResponseEntity containing the collection information
     */
    public ResponseEntity<String> getCollection(String tenant, String database, String collectionId) {
        return chromaClient.getCollection(tenant, database, collectionId, new HashMap<>());
    }

    /**
     * Updates an existing collection's name or metadata.
     *
     * @param tenant the tenant UUID
     * @param database the database name
     * @param collectionId the collection UUID
     * @param updateRequest the update request as String
     * @return ResponseEntity containing the response
     */
    public ResponseEntity<String> updateCollection(String tenant, String database, String collectionId, String updateRequest) {
        return chromaClient.updateCollection(tenant, database, collectionId, updateRequest, new HashMap<>());
    }

    /**
     * Deletes a collection in a specific tenant and database.
     *
     * @param tenant the tenant UUID
     * @param database the database name
     * @param collectionId the collection UUID
     * @return ResponseEntity containing the response
     */
    public ResponseEntity<String> deleteCollection(String tenant, String database, String collectionId) {
        return chromaClient.deleteCollection(tenant, database, collectionId, new HashMap<>());
    }

    /**
     * Forks an existing collection in a specific tenant and database.
     *
     * @param tenant the tenant UUID
     * @param database the database name
     * @param collectionId the collection UUID
     * @param forkRequest the fork request as String
     * @return ResponseEntity containing the response
     */
    public ResponseEntity<String> forkCollection(String tenant, String database, String collectionId, String forkRequest) {
        return chromaClient.forkCollection(tenant, database, collectionId, forkRequest, new HashMap<>());
    }

    /**
     * Gets the total number of collections in a specific tenant and database.
     *
     * @param tenant the tenant UUID
     * @param database the database name
     * @return ResponseEntity containing the count
     */
    public ResponseEntity<String> getCollectionsCount(String tenant, String database) {
        return chromaClient.getCollectionsCount(tenant, database, new HashMap<>());
    }

    /**
     * Gets collection information.
     *
     * @param collectionId the collection identifier
     * @return ResponseEntity containing the collection information
     */
    public ResponseEntity<String> getCollection(String collectionId) {
        return chromaClient.getCollection(collectionId, new HashMap<>());
    }

    /**
     * Lists all collections in a specific tenant and database.
     *
     * @param tenant the tenant UUID
     * @param database the database name
     * @param queryParams optional query parameters for pagination (e.g., "limit=10&amp;offset=0").
     * @return ResponseEntity containing the list of collections
     */
    public ResponseEntity<String> listCollections(String tenant, String database, String queryParams) {
        return chromaClient.listCollections(tenant, database, queryParams, new HashMap<>());
    }

    /**
     * Lists all collections.
     *
     * @param queryParams optional query parameters for filtering.
     * @return ResponseEntity containing the list of collections
     */
    public ResponseEntity<String> listCollections(String queryParams) {
        return chromaClient.listCollections(queryParams, new HashMap<>());
    }

    /**
     * Counts the number of items in a collection.
     *
     * @param collectionId the collection identifier
     * @return ResponseEntity containing the count
     */
    public ResponseEntity<String> countCollection(String collectionId) {
        return chromaClient.countCollection(collectionId, new HashMap<>());
    }

    /**
     * Deletes a collection.
     *
     * @param collectionId the collection identifier
     * @return ResponseEntity containing the response
     */
    public ResponseEntity<String> deleteCollection(String collectionId) {
        return chromaClient.deleteCollection(collectionId, new HashMap<>());
    }
}