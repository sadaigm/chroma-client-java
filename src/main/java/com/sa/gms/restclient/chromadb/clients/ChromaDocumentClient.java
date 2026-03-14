package com.sa.gms.restclient.chromadb.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.gms.restclient.chromadb.core.IChromaClient;
import com.sa.gms.restclient.chromadb.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Service facade for Chroma DB document operations.
 * Provides a clean API for document-related operations.
 */
@Service
public class ChromaDocumentClient {

    private final IChromaClient chromaClient;
    private final ObjectMapper objectMapper;

    /**
     * Constructor-based dependency injection of IChromaClient implementation.
     *
     * @param chromaClient the Chroma DB client implementation
     */
    @Autowired
    public ChromaDocumentClient(IChromaClient chromaClient) {
        this.chromaClient = chromaClient;
        this.objectMapper = new ObjectMapper();
    }

    // ==================== Document Operations ====================

    /**
     * Adds records to a collection.
     *
     * @param tenantId the tenant identifier
     * @param databaseName the database name
     * @param collectionId the collection identifier
     * @param request the records addition request DTO
     * @return ResponseEntity containing the response
     * @throws JsonProcessingException if the DTO cannot be serialized to JSON
     */
    public ResponseEntity<String> addRecords(String tenantId, String databaseName, String collectionId, AddRecordsRequest request) throws JsonProcessingException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        return chromaClient.addRecords(tenantId, databaseName, collectionId, jsonRequest, new HashMap<>());
    }

    /**
     * Counts the number of records in a collection.
     *
     * @param tenantId the tenant identifier
     * @param databaseName the database name
     * @param collectionId the collection identifier
     * @param readLevel the read level for consistency vs performance tradeoffs (optional)
     *                   Valid values: "index_and_wal", "index_only"
     * @return ResponseEntity containing the count
     */
    public ResponseEntity<String> countRecords(String tenantId, String databaseName, String collectionId, String readLevel) {
        String queryParams = QueryParams.forCountRecords(readLevel).build();
        return chromaClient.countRecords(tenantId, databaseName, collectionId, queryParams, new HashMap<>());
    }

    /**
     * Deletes records from a collection.
     *
     * @param tenantId the tenant identifier
     * @param databaseName the database name
     * @param collectionId the collection identifier
     * @param request the delete request DTO
     * @return ResponseEntity containing the response
     * @throws JsonProcessingException if the DTO cannot be serialized to JSON
     */
    public ResponseEntity<String> deleteRecords(String tenantId, String databaseName, String collectionId, DeleteRecordsRequest request) throws JsonProcessingException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        return chromaClient.deleteRecords(tenantId, databaseName, collectionId, jsonRequest, new HashMap<>());
    }

    /**
     * Gets records from a collection.
     *
     * @param tenantId * tenant identifier
     * @param databaseName * database name
     * @param collectionId * collection identifier
     * @param request * get request DTO
     * @return ResponseEntity containing the records
     * @throws JsonProcessingException if the DTO cannot be serialized to JSON
     */
    public ResponseEntity<String> getRecords(String tenantId, String databaseName, String collectionId, GetRecordsRequest request) throws JsonProcessingException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        return chromaClient.getRecords(tenantId, databaseName, collectionId, jsonRequest, new HashMap<>());
    }

    /**
     * Gets indexing status of a collection.
     *
     * @param tenantId * tenant identifier
     * @param databaseName * database name
     * @param collectionId * collection identifier
     * @return ResponseEntity containing the indexing status
     */
    public ResponseEntity<String> getIndexingStatus(String tenantId, String databaseName, String collectionId) {
        return chromaClient.getIndexingStatus(tenantId, databaseName, collectionId, new HashMap<>());
    }

    /**
     * Queries a collection using dense vector search.
     *
     * @param tenantId * tenant identifier
     * @param databaseName * database name
     * @param collectionId * collection identifier
     * @param request * query request DTO
     * @return ResponseEntity containing query results
     * @throws JsonProcessingException if DTO cannot be serialized to JSON
     */
    public ResponseEntity<String> queryCollection(String tenantId, String databaseName, String collectionId, QueryCollectionRequest request) throws JsonProcessingException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        return chromaClient.queryCollection(tenantId, databaseName, collectionId, null, jsonRequest, new HashMap<>());
    }

    /**
     * Queries a collection using dense vector search with pagination.
     *
     * @param tenantId * tenant identifier
     * @param databaseName * database name
     * @param collectionId * collection identifier
     * @param limit * limit for pagination
     * @param offset * offset for pagination
     * @param request * query request DTO
     * @return ResponseEntity containing query results
     * @throws JsonProcessingException if DTO cannot be serialized to JSON
     */
    public ResponseEntity<String> queryCollection(String tenantId, String databaseName, String collectionId, Integer limit, Integer offset, QueryCollectionRequest request) throws JsonProcessingException {
        String queryParams = QueryParams.forQueryCollection(limit, offset).build();
        String jsonRequest = objectMapper.writeValueAsString(request);
        return chromaClient.queryCollection(tenantId, databaseName, collectionId, queryParams, jsonRequest, new HashMap<>());
    }

    /**
     * Searches records from a collection with dense, sparse, or hybrid vector search.
     *
     * @param tenantId * tenant identifier
     * @param databaseName * database name
     * @param collectionId * collection identifier
     * @param request * search request DTO
     * @return ResponseEntity containing search results
     * @throws JsonProcessingException if DTO cannot be serialized to JSON
     */
    public ResponseEntity<String> searchRecords(String tenantId, String databaseName, String collectionId, SearchRecordsRequest request) throws JsonProcessingException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        return chromaClient.searchRecords(tenantId, databaseName, collectionId, jsonRequest, new HashMap<>());
    }

    /**
     * Updates records in a collection by ID.
     *
     * @param tenantId * tenant identifier
     * @param databaseName * database name
     * @param collectionId * collection identifier
     * @param request * update request DTO
     * @return ResponseEntity containing update response
     * @throws JsonProcessingException if DTO cannot be serialized to JSON
     */
    public ResponseEntity<String> updateRecords(String tenantId, String databaseName, String collectionId, UpdateRecordsRequest request) throws JsonProcessingException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        return chromaClient.updateRecords(tenantId, databaseName, collectionId, jsonRequest, new HashMap<>());
    }

    /**
     * Upserts records in a collection (create if not exists, otherwise update).
     *
     * @param tenantId * tenant identifier
     * @param databaseName * database name
     * @param collectionId * collection identifier
     * @param request * upsert request DTO
     * @return ResponseEntity containing upsert response
     * @throws JsonProcessingException if DTO cannot be serialized to JSON
     */
    public ResponseEntity<String> upsertRecords(String tenantId, String databaseName, String collectionId, UpsertRecordsRequest request) throws JsonProcessingException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        return chromaClient.upsertRecords(tenantId, databaseName, collectionId, jsonRequest, new HashMap<>());
    }
}