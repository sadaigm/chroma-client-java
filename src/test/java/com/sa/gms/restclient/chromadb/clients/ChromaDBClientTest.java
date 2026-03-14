package com.sa.gms.restclient.chromadb.clients;

import com.sa.gms.restclient.chromadb.core.IChromaClient;
import com.sa.gms.restclient.chromadb.dto.CreateDatabaseRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ChromaDBClient.
 * Tests all database-related operations including create, list, get, and delete.
 */
@DisplayName("ChromaDBClient Tests")
class ChromaDBClientTest {

    @Mock
    private IChromaClient chromaClient;

    @InjectMocks
    private ChromaDBClient chromaDBClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ==================== createDatabase Tests ====================

    @Test
    @DisplayName("Should create database with string request")
    void testCreateDatabase_StringRequest_Success() {
        // Arrange
        String tenantId = "tenant-123";
        String dbRequest = "{\"name\":\"test-db\"}";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Database created successfully");
        when(chromaClient.createDatabase(eq(tenantId), eq(dbRequest), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDBClient.createDatabase(tenantId, dbRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Database created successfully", response.getBody());
        verify(chromaClient, times(1)).createDatabase(eq(tenantId), eq(dbRequest), anyMap());
    }

    @Test
    @DisplayName("Should create database with DTO")
    void testCreateDatabase_Dto_Success() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        CreateDatabaseRequest request = new CreateDatabaseRequest("test-db");
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Database created successfully");
        when(chromaClient.createDatabase(eq(tenantId), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDBClient.createDatabase(tenantId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Database created successfully", response.getBody());
        verify(chromaClient, times(1)).createDatabase(eq(tenantId), anyString(), anyMap());
    }

    @Test
    @DisplayName("Should handle database creation failure")
    void testCreateDatabase_Failure() {
        // Arrange
        String tenantId = "tenant-123";
        String dbRequest = "{\"name\":\"invalid-db\"}";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.badRequest().body("Invalid database name");
        when(chromaClient.createDatabase(eq(tenantId), eq(dbRequest), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDBClient.createDatabase(tenantId, dbRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid database name", response.getBody());
        verify(chromaClient, times(1)).createDatabase(eq(tenantId), eq(dbRequest), anyMap());
    }

    @Test
    @DisplayName("Should create database with special characters in name")
    void testCreateDatabase_SpecialCharacters() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        CreateDatabaseRequest request = new CreateDatabaseRequest("test-db_123@example.com");
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Database created");
        when(chromaClient.createDatabase(eq(tenantId), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDBClient.createDatabase(tenantId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).createDatabase(eq(tenantId), anyString(), anyMap());
    }

    @Test
    @DisplayName("Should create database with empty name")
    void testCreateDatabase_EmptyName() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        CreateDatabaseRequest request = new CreateDatabaseRequest("");
        
        ResponseEntity<String> expectedResponse = ResponseEntity.badRequest().body("Database name cannot be empty");
        when(chromaClient.createDatabase(eq(tenantId), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDBClient.createDatabase(tenantId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(chromaClient, times(1)).createDatabase(eq(tenantId), anyString(), anyMap());
    }

    // ==================== listDatabases Tests ====================

    @Test
    @DisplayName("Should list databases successfully")
    void testListDatabases_Success() {
        // Arrange
        String tenantId = "tenant-123";
        String queryParams = "limit=10&offset=0";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[{\"name\":\"db1\"},{\"name\":\"db2\"}]");
        when(chromaClient.listDatabases(eq(tenantId), eq(queryParams), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDBClient.listDatabases(tenantId, queryParams);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("[{\"name\":\"db1\"},{\"name\":\"db2\"}]", response.getBody());
        verify(chromaClient, times(1)).listDatabases(eq(tenantId), eq(queryParams), anyMap());
    }

    @Test
    @DisplayName("Should list databases with null query params")
    void testListDatabases_NullQueryParams() {
        // Arrange
        String tenantId = "tenant-123";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[]");
        when(chromaClient.listDatabases(eq(tenantId), isNull(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDBClient.listDatabases(tenantId, null);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("[]", response.getBody());
        verify(chromaClient, times(1)).listDatabases(eq(tenantId), isNull(), anyMap());
    }

    @Test
    @DisplayName("Should list databases with empty query params")
    void testListDatabases_EmptyQueryParams() {
        // Arrange
        String tenantId = "tenant-123";
        String queryParams = "";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[]");
        when(chromaClient.listDatabases(eq(tenantId), eq(queryParams), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDBClient.listDatabases(tenantId, queryParams);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).listDatabases(eq(tenantId), eq(queryParams), anyMap());
    }

    // ==================== getDatabase Tests ====================

    @Test
    @DisplayName("Should get database successfully")
    void testGetDatabase_Success() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"name\":\"test-db\",\"id\":\"db-456\"}");
        when(chromaClient.getDatabase(eq(tenantId), eq(databaseName), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDBClient.getDatabase(tenantId, databaseName);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"name\":\"test-db\",\"id\":\"db-456\"}", response.getBody());
        verify(chromaClient, times(1)).getDatabase(eq(tenantId), eq(databaseName), anyMap());
    }

    @Test
    @DisplayName("Should handle database not found")
    void testGetDatabase_NotFound() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "non-existent-db";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.notFound().build();
        when(chromaClient.getDatabase(eq(tenantId), eq(databaseName), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDBClient.getDatabase(tenantId, databaseName);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(chromaClient, times(1)).getDatabase(eq(tenantId), eq(databaseName), anyMap());
    }

    @Test
    @DisplayName("Should get database with null database name")
    void testGetDatabase_NullDatabaseName() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = null;
        
        ResponseEntity<String> expectedResponse = ResponseEntity.badRequest().build();
        when(chromaClient.getDatabase(eq(tenantId), isNull(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDBClient.getDatabase(tenantId, databaseName);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(chromaClient, times(1)).getDatabase(eq(tenantId), isNull(), anyMap());
    }

    // ==================== deleteDatabase Tests ====================

    @Test
    @DisplayName("Should delete database successfully")
    void testDeleteDatabase_Success() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Database deleted successfully");
        when(chromaClient.deleteDatabase(eq(tenantId), eq(databaseName), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDBClient.deleteDatabase(tenantId, databaseName);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Database deleted successfully", response.getBody());
        verify(chromaClient, times(1)).deleteDatabase(eq(tenantId), eq(databaseName), anyMap());
    }

    @Test
    @DisplayName("Should handle database not found when deleting")
    void testDeleteDatabase_NotFound() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "non-existent-db";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.notFound().build();
        when(chromaClient.deleteDatabase(eq(tenantId), eq(databaseName), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDBClient.deleteDatabase(tenantId, databaseName);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(chromaClient, times(1)).deleteDatabase(eq(tenantId), eq(databaseName), anyMap());
    }

    @Test
    @DisplayName("Should handle database deletion failure")
    void testDeleteDatabase_Failure() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.internalServerError().body("Database deletion failed");
        when(chromaClient.deleteDatabase(eq(tenantId), eq(databaseName), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDBClient.deleteDatabase(tenantId, databaseName);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Database deletion failed", response.getBody());
        verify(chromaClient, times(1)).deleteDatabase(eq(tenantId), eq(databaseName), anyMap());
    }

    // ==================== Integration Tests ====================

    @Test
    @DisplayName("Should handle complete database lifecycle")
    void testCompleteDatabaseLifecycle() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        CreateDatabaseRequest createRequest = new CreateDatabaseRequest("test-db");
        String dbRequest = "{\"name\":\"test-db\"}";
        
        when(chromaClient.createDatabase(eq(tenantId), anyString(), anyMap()))
            .thenReturn(ResponseEntity.ok("{\"id\":\"db-456\"}"));
        when(chromaClient.getDatabase(eq(tenantId), eq("test-db"), anyMap()))
            .thenReturn(ResponseEntity.ok("{\"name\":\"test-db\",\"id\":\"db-456\"}"));
        when(chromaClient.listDatabases(eq(tenantId), isNull(), anyMap()))
            .thenReturn(ResponseEntity.ok("[{\"name\":\"test-db\"}]"));
        when(chromaClient.deleteDatabase(eq(tenantId), eq("test-db"), anyMap()))
            .thenReturn(ResponseEntity.ok("Deleted"));

        // Act
        ResponseEntity<String> created = chromaDBClient.createDatabase(tenantId, createRequest);
        ResponseEntity<String> retrieved = chromaDBClient.getDatabase(tenantId, "test-db");
        ResponseEntity<String> listed = chromaDBClient.listDatabases(tenantId, null);
        ResponseEntity<String> deleted = chromaDBClient.deleteDatabase(tenantId, "test-db");

        // Assert
        assertEquals(HttpStatus.OK, created.getStatusCode());
        assertEquals(HttpStatus.OK, retrieved.getStatusCode());
        assertEquals(HttpStatus.OK, listed.getStatusCode());
        assertEquals(HttpStatus.OK, deleted.getStatusCode());
        
        verify(chromaClient, times(1)).createDatabase(eq(tenantId), anyString(), anyMap());
        verify(chromaClient, times(1)).getDatabase(eq(tenantId), eq("test-db"), anyMap());
        verify(chromaClient, times(1)).listDatabases(eq(tenantId), isNull(), anyMap());
        verify(chromaClient, times(1)).deleteDatabase(eq(tenantId), eq("test-db"), anyMap());
    }

    @Test
    @DisplayName("Should handle database operations with different parameters")
    void testDatabaseOperations_WithDifferentParameters() {
        // Arrange
        String tenantId = "tenant-123";
        
        when(chromaClient.createDatabase(eq(tenantId), eq("{\"name\":\"db1\"}"), anyMap()))
            .thenReturn(ResponseEntity.ok("Created"));
        when(chromaClient.listDatabases(eq(tenantId), eq("limit=5"), anyMap()))
            .thenReturn(ResponseEntity.ok("[]"));
        when(chromaClient.getDatabase(eq(tenantId), eq("db1"), anyMap()))
            .thenReturn(ResponseEntity.ok("{\"name\":\"db1\"}"));

        // Act
        ResponseEntity<String> created = chromaDBClient.createDatabase(tenantId, "{\"name\":\"db1\"}");
        ResponseEntity<String> listed = chromaDBClient.listDatabases(tenantId, "limit=5");
        ResponseEntity<String> retrieved = chromaDBClient.getDatabase(tenantId, "db1");

        // Assert
        assertEquals(HttpStatus.OK, created.getStatusCode());
        assertEquals(HttpStatus.OK, listed.getStatusCode());
        assertEquals(HttpStatus.OK, retrieved.getStatusCode());
        
        verify(chromaClient, times(1)).createDatabase(eq(tenantId), eq("{\"name\":\"db1\"}"), anyMap());
        verify(chromaClient, times(1)).listDatabases(eq(tenantId), eq("limit=5"), anyMap());
        verify(chromaClient, times(1)).getDatabase(eq(tenantId), eq("db1"), anyMap());
    }
}