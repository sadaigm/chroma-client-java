package com.sa.gms.restclient.chromadb.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.gms.restclient.chromadb.core.IChromaClient;
import com.sa.gms.restclient.chromadb.dto.CreateTenantRequest;
import com.sa.gms.restclient.chromadb.dto.UpdateTenantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service facade for Chroma DB tenant operations.
 * Provides a clean API for tenant-related operations.
 */
@Service
public class ChromaTenantClient {

    private final IChromaClient chromaClient;
    private final ObjectMapper objectMapper;

    /**
     * Constructor-based dependency injection of IChromaClient implementation.
     *
     * @param chromaClient the Chroma DB client implementation
     */
    @Autowired
    public ChromaTenantClient(IChromaClient chromaClient) {
        this.chromaClient = chromaClient;
        this.objectMapper = new ObjectMapper();
    }

    // ==================== Tenant Operations ====================

    /**
     * Creates a new tenant using DTO.
     *
     * @param request the tenant creation request DTO
     * @return ResponseEntity containing the response
     * @throws JsonProcessingException if JSON serialization fails
     */
    public ResponseEntity<String> createTenant(CreateTenantRequest request) throws JsonProcessingException {
        String requestBody = objectMapper.writeValueAsString(request);
        return chromaClient.createTenant(requestBody, new HashMap<>());
    }
        /**
         * Creates a new tenant using DTO with custom headers.
         *
         * @param request tenant creation request DTO
         * @param headers custom HTTP headers to include
         * @return ResponseEntity containing the response
         * @throws JsonProcessingException if JSON serialization fails
         */
        public ResponseEntity<String> createTenant (CreateTenantRequest request, Map < String, String > headers) throws
        JsonProcessingException {
            String requestBody = objectMapper.writeValueAsString(request);
            return chromaClient.createTenant(requestBody, headers);
        }


    /**
     * Gets tenant information.
     *
     * @param tenantId the tenant identifier
     * @return ResponseEntity containing the tenant information
     */
    public ResponseEntity<String> getTenant(String tenantId) {
        return chromaClient.getTenant(tenantId, new HashMap<>());
    }

    /**
     * Updates an existing tenant.
     *
     * @param tenantId      the tenant identifier
     * @param updateRequest the update request as String (JSON format: {"resource_name": "string"})
     * @return ResponseEntity containing the response
     */
    public ResponseEntity<String> updateTenant(String tenantId, String updateRequest) {
        return chromaClient.updateTenant(tenantId, updateRequest, new HashMap<>());
    }

    /**
     * Updates an existing tenant using DTO.
     *
     * @param tenantId the tenant identifier
     * @param request  the update request DTO
     * @return ResponseEntity containing the response
     * @throws JsonProcessingException if JSON serialization fails
     */
    public ResponseEntity<String> updateTenant(String tenantId, UpdateTenantRequest request) throws JsonProcessingException {
        String requestBody = objectMapper.writeValueAsString(request);
        return chromaClient.updateTenant(tenantId, requestBody, new HashMap<>());
    }

    /**
     * Updates an existing tenant using DTO with custom headers.
     *
     * @param tenantId tenant identifier
     * @param request  update request DTO
     * @param headers  custom HTTP headers to include
     * @return ResponseEntity containing the response
     * @throws JsonProcessingException if JSON serialization fails
     */
    public ResponseEntity<String> updateTenant(String tenantId, UpdateTenantRequest request, Map<String, String> headers) throws JsonProcessingException {
        String requestBody = objectMapper.writeValueAsString(request);
        return chromaClient.updateTenant(tenantId, requestBody, headers);
    }
}