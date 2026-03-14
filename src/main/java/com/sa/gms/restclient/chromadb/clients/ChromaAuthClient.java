package com.sa.gms.restclient.chromadb.clients;

import com.sa.gms.restclient.chromadb.core.IChromaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Service facade for Chroma DB authentication operations.
 * Provides a clean API for authentication-related operations.
 */
@Service
public class ChromaAuthClient {

    private final IChromaClient chromaClient;

    /**
     * Constructor-based dependency injection of IChromaClient implementation.
     *
     * @param chromaClient the Chroma DB client implementation
     */
    @Autowired
    public ChromaAuthClient(IChromaClient chromaClient) {
        this.chromaClient = chromaClient;
    }

    // ==================== Authentication Operations ====================

    /**
     * Gets the current user's identity, tenant, and databases.
     *
     * @return ResponseEntity containing the user identity information
     */
    public ResponseEntity<String> getIdentity() {
        return chromaClient.getIdentity(new HashMap<>());
    }
}