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
 * Unit tests for ChromaAuthClient.
 * Tests all authentication-related operations including getIdentity.
 */
@DisplayName("ChromaAuthClient Tests")
class ChromaAuthClientTest {

    @Mock
    private IChromaClient chromaClient;

    @InjectMocks
    private ChromaAuthClient chromaAuthClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ==================== getIdentity Tests ====================

    @Test
    @DisplayName("Should get identity successfully")
    void testGetIdentity_Success() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.ok(
            "{\"id\":\"user-123\",\"tenant\":\"tenant-456\",\"databases\":[\"db1\",\"db2\"]}"
        );
        when(chromaClient.getIdentity(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaAuthClient.getIdentity();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"id\":\"user-123\",\"tenant\":\"tenant-456\",\"databases\":[\"db1\",\"db2\"]}", response.getBody());
        verify(chromaClient, times(1)).getIdentity(anyMap());
    }

    @Test
    @DisplayName("Should handle unauthorized access")
    void testGetIdentity_Unauthorized() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("{\"error\":\"Unauthorized\"}");
        when(chromaClient.getIdentity(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaAuthClient.getIdentity();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("{\"error\":\"Unauthorized\"}", response.getBody());
        verify(chromaClient, times(1)).getIdentity(anyMap());
    }

    @Test
    @DisplayName("Should handle identity retrieval failure")
    void testGetIdentity_Failure() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.internalServerError()
            .body("{\"error\":\"Internal server error\"}");
        when(chromaClient.getIdentity(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaAuthClient.getIdentity();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("{\"error\":\"Internal server error\"}", response.getBody());
        verify(chromaClient, times(1)).getIdentity(anyMap());
    }

    @Test
    @DisplayName("Should handle empty identity response")
    void testGetIdentity_EmptyResponse() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{}");
        when(chromaClient.getIdentity(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaAuthClient.getIdentity();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{}", response.getBody());
        verify(chromaClient, times(1)).getIdentity(anyMap());
    }

    @Test
    @DisplayName("Should handle identity with no databases")
    void testGetIdentity_NoDatabases() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.ok(
            "{\"id\":\"user-123\",\"tenant\":\"tenant-456\",\"databases\":[]}"
        );
        when(chromaClient.getIdentity(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaAuthClient.getIdentity();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"id\":\"user-123\",\"tenant\":\"tenant-456\",\"databases\":[]}", response.getBody());
        verify(chromaClient, times(1)).getIdentity(anyMap());
    }

    @Test
    @DisplayName("Should handle identity with multiple databases")
    void testGetIdentity_MultipleDatabases() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.ok(
            "{\"id\":\"user-123\",\"tenant\":\"tenant-456\",\"databases\":[\"db1\",\"db2\",\"db3\",\"db4\"]}"
        );
        when(chromaClient.getIdentity(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaAuthClient.getIdentity();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"id\":\"user-123\",\"tenant\":\"tenant-456\",\"databases\":[\"db1\",\"db2\",\"db3\",\"db4\"]}", response.getBody());
        verify(chromaClient, times(1)).getIdentity(anyMap());
    }

    @Test
    @DisplayName("Should handle identity with null tenant")
    void testGetIdentity_NullTenant() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.ok(
            "{\"id\":\"user-123\",\"tenant\":null,\"databases\":[]}"
        );
        when(chromaClient.getIdentity(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaAuthClient.getIdentity();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"id\":\"user-123\",\"tenant\":null,\"databases\":[]}", response.getBody());
        verify(chromaClient, times(1)).getIdentity(anyMap());
    }

    @Test
    @DisplayName("Should handle service unavailable")
    void testGetIdentity_ServiceUnavailable() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body("{\"error\":\"Service temporarily unavailable\"}");
        when(chromaClient.getIdentity(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaAuthClient.getIdentity();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertEquals("{\"error\":\"Service temporarily unavailable\"}", response.getBody());
        verify(chromaClient, times(1)).getIdentity(anyMap());
    }

    // ==================== Integration Tests ====================

    @Test
    @DisplayName("Should handle multiple identity calls")
    void testMultipleIdentityCalls() {
        // Arrange
        ResponseEntity<String> response1 = ResponseEntity.ok(
            "{\"id\":\"user-123\",\"tenant\":\"tenant-456\",\"databases\":[\"db1\"]}"
        );
        ResponseEntity<String> response2 = ResponseEntity.ok(
            "{\"id\":\"user-123\",\"tenant\":\"tenant-456\",\"databases\":[\"db1\",\"db2\"]}"
        );
        
        when(chromaClient.getIdentity(anyMap()))
            .thenReturn(response1)
            .thenReturn(response2);

        // Act
        ResponseEntity<String> firstCall = chromaAuthClient.getIdentity();
        ResponseEntity<String> secondCall = chromaAuthClient.getIdentity();

        // Assert
        assertEquals(HttpStatus.OK, firstCall.getStatusCode());
        assertEquals(HttpStatus.OK, secondCall.getStatusCode());
        assertNotEquals(firstCall.getBody(), secondCall.getBody());
        
        verify(chromaClient, times(2)).getIdentity(anyMap());
    }

    @Test
    @DisplayName("Should handle identity with special characters in database names")
    void testGetIdentity_SpecialCharactersInDatabases() {
        // Arrange
        ResponseEntity<String> expectedResponse = ResponseEntity.ok(
            "{\"id\":\"user-123\",\"tenant\":\"tenant-456\",\"databases\":[\"db-1\",\"db_2\",\"db.3\"]}"
        );
        when(chromaClient.getIdentity(anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaAuthClient.getIdentity();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"id\":\"user-123\",\"tenant\":\"tenant-456\",\"databases\":[\"db-1\",\"db_2\",\"db.3\"]}", response.getBody());
        verify(chromaClient, times(1)).getIdentity(anyMap());
    }
}