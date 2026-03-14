package com.sa.gms.restclient.chromadb.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.gms.restclient.chromadb.core.IChromaClient;
import com.sa.gms.restclient.chromadb.dto.CreateDatabaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Service facade for Chroma DB database operations.
 * Provides a clean API for database-related operations.
 */
@Service
public class ChromaDBClient {

    private final IChromaClient chromaClient;
    private final ObjectMapper objectMapper;

    /**
     * Constructor-based dependency injection of IChromaClient implementation.
     *
     * @param chromaClient the Chroma DB client implementation
     */
    @Autowired
    public ChromaDBClient(IChromaClient chromaClient) {
        this.chromaClient = chromaClient;
        this.objectMapper = new ObjectMapper();
    }

    // ==================== Database Operations ====================

    /**
     * Creates a new database for a tenant.
     *
     * @param tenantId the tenant identifier
     * @param dbRequest the database creation request as String (JSON format: {"name": "string"})
     * @return ResponseEntity containing the response
     */
    public ResponseEntity<String> createDatabase(String tenantId, String dbRequest) {
        return chromaClient.createDatabase(tenantId, dbRequest, new HashMap<>());
    }

    /**
     * Creates a new database for a tenant using DTO.
     *
     * @param tenantId the tenant identifier
     * @param request the database creation request DTO
     * @return ResponseEntity containing the response
     * @throws JsonProcessingException if JSON serialization fails
     */
    public ResponseEntity<String> createDatabase(String tenantId, CreateDatabaseRequest request) throws JsonProcessingException {
        String requestBody = objectMapper.writeValueAsString(request);
        return chromaClient.createDatabase(tenantId, requestBody, new HashMap<>());
    }

    /**
     * Lists all databases for a tenant.
     * 
     * Usage examples:
     * <pre>{@code
     * // Without pagination
     * chromaDBClient.listDatabases(tenantId, null);
     * 
     * // With pagination using QueryParams utility
     * String queryParams = QueryParams.forListDatabases(10, 0).build();
     * chromaDBClient.listDatabases(tenantId, queryParams);
     * 
     * // With custom query parameters
     * String queryParams = new QueryParams()
     *     .add("limit", 10)
     *     .add("offset", 0)
     *     .build();
     * chromaDBClient.listDatabases(tenantId, queryParams);
     * }</pre>
     *
     * @param tenantId the tenant identifier
     * @param queryParams optional query parameters for pagination (e.g., "limit=10&amp;offset=0").
     * @return ResponseEntity containing the list of databases
     */
    public ResponseEntity<String> listDatabases(String tenantId, String queryParams) {
        return chromaClient.listDatabases(tenantId, queryParams, new HashMap<>());
    }

    /**
     * Gets database information.
     *
     * @param tenantId the tenant identifier
     * @param databaseName the database name
     * @return ResponseEntity containing the database information
     */
    public ResponseEntity<String> getDatabase(String tenantId, String databaseName) {
        return chromaClient.getDatabase(tenantId, databaseName, new HashMap<>());
    }

    /**
     * Deletes a database.
     *
     * @param tenantId the tenant identifier
     * @param databaseName the database name
     * @return ResponseEntity containing the response
     */
    public ResponseEntity<String> deleteDatabase(String tenantId, String databaseName) {
        return chromaClient.deleteDatabase(tenantId, databaseName, new HashMap<>());
    }
}