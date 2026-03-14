package com.sa.gms.restclient.chromadb.clients;

import com.sa.gms.restclient.chromadb.core.IChromaClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ChromaSystemClient.
 * Tests all system-related operations including healthcheck, heartbeat, reset, version, and pre-flight checks.
 */
@DisplayName("ChromaSystemClient Tests")
class ChromaSystemClientTest {

    @Mock
    private IChromaClient chromaClient;

    @InjectMocks
    private ChromaSystemClient chromaSystemClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ==================== healthcheck Tests ====================

    @Test
    @DisplayName("Should perform healthcheck successfully")
    void testHealthcheck_Success() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"status\":\"healthy\"}");
        when(chromaClient.healthcheck(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaSystemClient.healthcheck();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"status\":\"healthy\"}", response.getBody());
        verify(chromaClient, times(1)).healthcheck(anyMap());
    }

    @Test
    @DisplayName("Should handle unhealthy status")
    void testHealthcheck_Unhealthy() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body("{\"status\":\"unhealthy\"}");
        when(chromaClient.healthcheck(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaSystemClient.healthcheck();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertEquals("{\"status\":\"unhealthy\"}", response.getBody());
        verify(chromaClient, times(1)).healthcheck(anyMap());
    }

    // ==================== heartbeat Tests ====================

    @Test
    @DisplayName("Should get heartbeat successfully")
    void testHeartbeat_Success() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("12345678901234567");
        when(chromaClient.heartbeat(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaSystemClient.heartbeat();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("12345678901234567", response.getBody());
        verify(chromaClient, times(1)).heartbeat(anyMap());
    }

    @Test
    @DisplayName("Should handle heartbeat failure")
    void testHeartbeat_Failure() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.internalServerError().body("Heartbeat failed");
        when(chromaClient.heartbeat(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaSystemClient.heartbeat();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Heartbeat failed", response.getBody());
        verify(chromaClient, times(1)).heartbeat(anyMap());
    }

    // ==================== resetV2 Tests ====================

    @Test
    @DisplayName("Should reset database successfully")
    void testResetV2_Success() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Database reset successfully");
        when(chromaClient.reset(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaSystemClient.resetV2();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Database reset successfully", response.getBody());
        verify(chromaClient, times(1)).reset(anyMap());
    }

    @Test
    @DisplayName("Should handle reset failure")
    void testResetV2_Failure() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body("Unauthorized: Reset requires admin privileges");
        when(chromaClient.reset(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaSystemClient.resetV2();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("Unauthorized: Reset requires admin privileges", response.getBody());
        verify(chromaClient, times(1)).reset(anyMap());
    }

    @Test
    @DisplayName("Should handle reset in progress")
    void testResetV2_InProgress() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.CONFLICT)
            .body("Reset already in progress");
        when(chromaClient.reset(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaSystemClient.resetV2();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Reset already in progress", response.getBody());
        verify(chromaClient, times(1)).reset(anyMap());
    }

    // ==================== getVersion Tests ====================

    @Test
    @DisplayName("Should get version successfully")
    void testGetVersion_Success() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("0.5.0");
        when(chromaClient.getVersion(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaSystemClient.getVersion();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("0.5.0", response.getBody());
        verify(chromaClient, times(1)).getVersion(anyMap());
    }

    @Test
    @DisplayName("Should handle version retrieval failure")
    void testGetVersion_Failure() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.internalServerError().body("Version retrieval failed");
        when(chromaClient.getVersion(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaSystemClient.getVersion();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Version retrieval failed", response.getBody());
        verify(chromaClient, times(1)).getVersion(anyMap());
    }

    // ==================== preFlightChecks Tests ====================

    @Test
    @DisplayName("Should perform pre-flight checks successfully")
    void testPreFlightChecks_Success() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"status\":\"ready\"}");
        when(chromaClient.preFlightChecks(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaSystemClient.preFlightChecks();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"status\":\"ready\"}", response.getBody());
        verify(chromaClient, times(1)).preFlightChecks(anyMap());
    }

    @Test
    @DisplayName("Should handle pre-flight checks failure")
    void testPreFlightChecks_Failure() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body("{\"status\":\"not_ready\"}");
        when(chromaClient.preFlightChecks(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaSystemClient.preFlightChecks();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertEquals("{\"status\":\"not_ready\"}", response.getBody());
        verify(chromaClient, times(1)).preFlightChecks(anyMap());
    }

    // ==================== Integration Tests ====================

    @Test
    @DisplayName("Should perform complete system health check")
    void testCompleteSystemHealthCheck() {
        // Arrange
        when(chromaClient.healthcheck(anyMap()))
            .thenReturn(ResponseEntity.ok("{\"status\":\"healthy\"}"));
        when(chromaClient.heartbeat(anyMap()))
            .thenReturn(ResponseEntity.ok("12345678901234567"));
        when(chromaClient.getVersion(anyMap()))
            .thenReturn(ResponseEntity.ok("0.5.0"));
        when(chromaClient.preFlightChecks(anyMap()))
            .thenReturn(ResponseEntity.ok("{\"status\":\"ready\"}"));

        // Act
        ResponseEntity<String> health = chromaSystemClient.healthcheck();
        ResponseEntity<String> heartbeat = chromaSystemClient.heartbeat();
        ResponseEntity<String> version = chromaSystemClient.getVersion();
        ResponseEntity<String> preFlight = chromaSystemClient.preFlightChecks();

        // Assert
        assertEquals(HttpStatus.OK, health.getStatusCode());
        assertEquals(HttpStatus.OK, heartbeat.getStatusCode());
        assertEquals(HttpStatus.OK, version.getStatusCode());
        assertEquals(HttpStatus.OK, preFlight.getStatusCode());
        
        verify(chromaClient, times(1)).healthcheck(anyMap());
        verify(chromaClient, times(1)).heartbeat(anyMap());
        verify(chromaClient, times(1)).getVersion(anyMap());
        verify(chromaClient, times(1)).preFlightChecks(anyMap());
    }

    @Test
    @DisplayName("Should handle system operations in sequence")
    void testSystemOperations_Sequence() {
        // Arrange
        when(chromaClient.healthcheck(anyMap()))
            .thenReturn(ResponseEntity.ok("{\"status\":\"healthy\"}"));
        when(chromaClient.reset(anyMap()))
            .thenReturn(ResponseEntity.ok("Reset complete"));
        when(chromaClient.healthcheck(anyMap()))
            .thenReturn(ResponseEntity.ok("{\"status\":\"healthy\"}"));

        // Act
        ResponseEntity<String> healthBefore = chromaSystemClient.healthcheck();
        ResponseEntity<String> reset = chromaSystemClient.resetV2();
        ResponseEntity<String> healthAfter = chromaSystemClient.healthcheck();

        // Assert
        assertEquals(HttpStatus.OK, healthBefore.getStatusCode());
        assertEquals(HttpStatus.OK, reset.getStatusCode());
        assertEquals(HttpStatus.OK, healthAfter.getStatusCode());
        
        verify(chromaClient, times(2)).healthcheck(anyMap());
        verify(chromaClient, times(1)).reset(anyMap());
    }
}