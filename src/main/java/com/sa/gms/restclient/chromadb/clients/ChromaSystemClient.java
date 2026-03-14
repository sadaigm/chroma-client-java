package com.sa.gms.restclient.chromadb.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.gms.restclient.chromadb.core.IChromaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Service facade for Chroma DB system operations.
 * Provides a clean API for system-related operations like healthcheck and heartbeat.
 */
@Service
public class ChromaSystemClient {

    private final IChromaClient chromaClient;
    private final ObjectMapper objectMapper;

    /**
     * Constructor-based dependency injection of IChromaClient implementation.
     *
     * @param chromaClient the Chroma DB client implementation
     */
    @Autowired
    public ChromaSystemClient(IChromaClient chromaClient) {
        this.chromaClient = chromaClient;
        this.objectMapper = new ObjectMapper();
    }

    // ==================== System Operations ====================

    /**
     * Gets the health status of the Chroma DB service.
     *
     * @return ResponseEntity containing the health status
     */
    public ResponseEntity<String> healthcheck() {
        return chromaClient.healthcheck( new HashMap<>());
    }

    /**
     * Gets the heartbeat timestamp in nanoseconds.
     *
     * @return ResponseEntity containing the nanosecond timestamp
     */
    public ResponseEntity<String> heartbeat() {
        return chromaClient.heartbeat(new HashMap<>());
    }

    /**
     * Resets the Chroma DB database.
     * Requires authorization.
     *
     * @return ResponseEntity containing the reset response
     */
    public ResponseEntity<String> resetV2() {
        return chromaClient.reset(new HashMap<>());
    }

    /**
     * Gets the version of the Chroma DB server.
     *
     * @return ResponseEntity containing the server version
     */
    public ResponseEntity<String> getVersion() {
        return chromaClient.getVersion(new HashMap<>());
    }

    /**
     * Gets basic readiness information.
     *
     * @return ResponseEntity containing the pre-flight checks
     */
    public ResponseEntity<String> preFlightChecks() {
        return chromaClient.preFlightChecks(new HashMap<>());
    }
}