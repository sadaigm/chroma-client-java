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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ChromaCollectionClient.
 * Tests all collection-related operations including create, get, update, delete, and list.
 */
@DisplayName("ChromaCollectionClient Tests")
class ChromaCollectionClientTest {

    @Mock
    private IChromaClient chromaClient;

    @InjectMocks
    private ChromaCollectionClient chromaCollectionClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ==================== createCollection Tests ====================

    @Test
    @DisplayName("Should create collection with tenant and database")
    void testCreateCollection_WithTenantAndDatabase() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String collectionRequest = "{\"name\":\"test-collection\"}";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Collection created successfully");
        when(chromaClient.createCollection(eq(tenant), eq(database), eq(collectionRequest), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.createCollection(tenant, database, collectionRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Collection created successfully", response.getBody());
        verify(chromaClient, times(1)).createCollection(eq(tenant), eq(database), eq(collectionRequest), anyMap());
    }

    @Test
    @DisplayName("Should create collection without tenant and database")
    void testCreateCollection_WithoutTenantAndDatabase() {
        // Arrange
        String collectionRequest = "{\"name\":\"test-collection\"}";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Collection created successfully");
        when(chromaClient.createCollection(eq(collectionRequest), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.createCollection(collectionRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Collection created successfully", response.getBody());
        verify(chromaClient, times(1)).createCollection(eq(collectionRequest), anyMap());
    }

    @Test
    @DisplayName("Should handle collection creation failure")
    void testCreateCollection_Failure() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String collectionRequest = "{\"name\":\"invalid-collection\"}";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.badRequest().body("Invalid collection name");
        when(chromaClient.createCollection(eq(tenant), eq(database), eq(collectionRequest), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.createCollection(tenant, database, collectionRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid collection name", response.getBody());
        verify(chromaClient, times(1)).createCollection(eq(tenant), eq(database), eq(collectionRequest), anyMap());
    }

    // ==================== getCollectionByCrn Tests ====================

    @Test
    @DisplayName("Should get collection by CRN successfully")
    void testGetCollectionByCrn_Success() {
        // Arrange
        String crn = "tenant-123:test-db:collection-456";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"id\":\"collection-456\",\"name\":\"test-collection\"}");
        when(chromaClient.getCollectionByCrn(eq(crn), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.getCollectionByCrn(crn);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"id\":\"collection-456\",\"name\":\"test-collection\"}", response.getBody());
        verify(chromaClient, times(1)).getCollectionByCrn(eq(crn), anyMap());
    }

    @Test
    @DisplayName("Should handle collection not found by CRN")
    void testGetCollectionByCrn_NotFound() {
        // Arrange
        String crn = "tenant-999:test-db:collection-999";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.notFound().build();
        when(chromaClient.getCollectionByCrn(eq(crn), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.getCollectionByCrn(crn);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(chromaClient, times(1)).getCollectionByCrn(eq(crn), anyMap());
    }

    // ==================== getCollection Tests ====================

    @Test
    @DisplayName("Should get collection by tenant, database, and collection ID")
    void testGetCollection_WithTenantAndDatabase() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String collectionId = "collection-456";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"id\":\"collection-456\",\"name\":\"test-collection\"}");
        when(chromaClient.getCollection(eq(tenant), eq(database), eq(collectionId), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.getCollection(tenant, database, collectionId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).getCollection(eq(tenant), eq(database), eq(collectionId), anyMap());
    }

    @Test
    @DisplayName("Should get collection by collection ID only")
    void testGetCollection_ByIdOnly() {
        // Arrange
        String collectionId = "collection-456";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"id\":\"collection-456\",\"name\":\"test-collection\"}");
        when(chromaClient.getCollection(eq(collectionId), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.getCollection(collectionId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).getCollection(eq(collectionId), anyMap());
    }

    @Test
    @DisplayName("Should handle collection not found by ID")
    void testGetCollection_NotFound() {
        // Arrange
        String collectionId = "collection-999";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.notFound().build();
        when(chromaClient.getCollection(eq(collectionId), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.getCollection(collectionId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(chromaClient, times(1)).getCollection(eq(collectionId), anyMap());
    }

    // ==================== updateCollection Tests ====================

    @Test
    @DisplayName("Should update collection successfully")
    void testUpdateCollection_Success() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String collectionId = "collection-456";
        String updateRequest = "{\"name\":\"updated-collection\"}";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Collection updated successfully");
        when(chromaClient.updateCollection(eq(tenant), eq(database), eq(collectionId), eq(updateRequest), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.updateCollection(tenant, database, collectionId, updateRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Collection updated successfully", response.getBody());
        verify(chromaClient, times(1)).updateCollection(eq(tenant), eq(database), eq(collectionId), eq(updateRequest), anyMap());
    }

    @Test
    @DisplayName("Should handle collection update failure")
    void testUpdateCollection_Failure() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String collectionId = "collection-456";
        String updateRequest = "{\"name\":\"invalid-name\"}";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.badRequest().body("Invalid collection name");
        when(chromaClient.updateCollection(eq(tenant), eq(database), eq(collectionId), eq(updateRequest), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.updateCollection(tenant, database, collectionId, updateRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid collection name", response.getBody());
        verify(chromaClient, times(1)).updateCollection(eq(tenant), eq(database), eq(collectionId), eq(updateRequest), anyMap());
    }

    // ==================== deleteCollection Tests ====================

    @Test
    @DisplayName("Should delete collection with tenant and database")
    void testDeleteCollection_WithTenantAndDatabase() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String collectionId = "collection-456";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Collection deleted successfully");
        when(chromaClient.deleteCollection(eq(tenant), eq(database), eq(collectionId), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.deleteCollection(tenant, database, collectionId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Collection deleted successfully", response.getBody());
        verify(chromaClient, times(1)).deleteCollection(eq(tenant), eq(database), eq(collectionId), anyMap());
    }

    @Test
    @DisplayName("Should delete collection by ID only")
    void testDeleteCollection_ByIdOnly() {
        // Arrange
        String collectionId = "collection-456";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Collection deleted successfully");
        when(chromaClient.deleteCollection(eq(collectionId), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.deleteCollection(collectionId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Collection deleted successfully", response.getBody());
        verify(chromaClient, times(1)).deleteCollection(eq(collectionId), anyMap());
    }

    @Test
    @DisplayName("Should handle collection not found when deleting")
    void testDeleteCollection_NotFound() {
        // Arrange
        String collectionId = "collection-999";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.notFound().build();
        when(chromaClient.deleteCollection(eq(collectionId), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.deleteCollection(collectionId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(chromaClient, times(1)).deleteCollection(eq(collectionId), anyMap());
    }

    // ==================== forkCollection Tests ====================

    @Test
    @DisplayName("Should fork collection successfully")
    void testForkCollection_Success() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String collectionId = "collection-456";
        String forkRequest = "{\"name\":\"forked-collection\"}";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Collection forked successfully");
        when(chromaClient.forkCollection(eq(tenant), eq(database), eq(collectionId), eq(forkRequest), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.forkCollection(tenant, database, collectionId, forkRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Collection forked successfully", response.getBody());
        verify(chromaClient, times(1)).forkCollection(eq(tenant), eq(database), eq(collectionId), eq(forkRequest), anyMap());
    }

    @Test
    @DisplayName("Should handle fork collection failure")
    void testForkCollection_Failure() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String collectionId = "collection-456";
        String forkRequest = "{\"name\":\"invalid-name\"}";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.badRequest().body("Invalid fork request");
        when(chromaClient.forkCollection(eq(tenant), eq(database), eq(collectionId), eq(forkRequest), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.forkCollection(tenant, database, collectionId, forkRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid fork request", response.getBody());
        verify(chromaClient, times(1)).forkCollection(eq(tenant), eq(database), eq(collectionId), eq(forkRequest), anyMap());
    }

    // ==================== getCollectionsCount Tests ====================

    @Test
    @DisplayName("Should get collections count successfully")
    void testGetCollectionsCount_Success() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("5");
        when(chromaClient.getCollectionsCount(eq(tenant), eq(database), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.getCollectionsCount(tenant, database);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("5", response.getBody());
        verify(chromaClient, times(1)).getCollectionsCount(eq(tenant), eq(database), anyMap());
    }

    @Test
    @DisplayName("Should handle empty collections count")
    void testGetCollectionsCount_Empty() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("0");
        when(chromaClient.getCollectionsCount(eq(tenant), eq(database), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.getCollectionsCount(tenant, database);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("0", response.getBody());
        verify(chromaClient, times(1)).getCollectionsCount(eq(tenant), eq(database), anyMap());
    }

    // ==================== listCollections Tests ====================

    @Test
    @DisplayName("Should list collections with tenant and database")
    void testListCollections_WithTenantAndDatabase() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String queryParams = "limit=10&offset=0";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[{\"id\":\"collection-1\"},{\"id\":\"collection-2\"}]");
        when(chromaClient.listCollections(eq(tenant), eq(database), eq(queryParams), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.listCollections(tenant, database, queryParams);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).listCollections(eq(tenant), eq(database), eq(queryParams), anyMap());
    }

    @Test
    @DisplayName("Should list collections without tenant and database")
    void testListCollections_WithoutTenantAndDatabase() {
        // Arrange
        String queryParams = "limit=10&offset=0";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[{\"id\":\"collection-1\"},{\"id\":\"collection-2\"}]");
        when(chromaClient.listCollections(eq(queryParams), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.listCollections(queryParams);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).listCollections(eq(queryParams), anyMap());
    }

    @Test
    @DisplayName("Should list collections with null query params")
    void testListCollections_NullQueryParams() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[]");
        when(chromaClient.listCollections(eq(tenant), eq(database), isNull(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.listCollections(tenant, database, null);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).listCollections(eq(tenant), eq(database), isNull(), anyMap());
    }

    // ==================== countCollection Tests ====================

    @Test
    @DisplayName("Should count collection items successfully")
    void testCountCollection_Success() {
        // Arrange
        String collectionId = "collection-456";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("1000");
        when(chromaClient.countCollection(eq(collectionId), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.countCollection(collectionId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1000", response.getBody());
        verify(chromaClient, times(1)).countCollection(eq(collectionId), anyMap());
    }

    @Test
    @DisplayName("Should handle empty collection count")
    void testCountCollection_Empty() {
        // Arrange
        String collectionId = "collection-456";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("0");
        when(chromaClient.countCollection(eq(collectionId), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaCollectionClient.countCollection(collectionId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("0", response.getBody());
        verify(chromaClient, times(1)).countCollection(eq(collectionId), anyMap());
    }

    // ==================== Integration Tests ====================

    @Test
    @DisplayName("Should handle complete collection lifecycle")
    void testCompleteCollectionLifecycle() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String collectionRequest = "{\"name\":\"test-collection\"}";
        String updateRequest = "{\"name\":\"updated-collection\"}";
        
        when(chromaClient.createCollection(eq(tenant), eq(database), eq(collectionRequest), anyMap()))
            .thenReturn(ResponseEntity.ok("{\"id\":\"collection-456\"}"));
        when(chromaClient.getCollection(eq(tenant), eq(database), eq("collection-456"), anyMap()))
            .thenReturn(ResponseEntity.ok("{\"id\":\"collection-456\",\"name\":\"test-collection\"}"));
        when(chromaClient.updateCollection(eq(tenant), eq(database), eq("collection-456"), eq(updateRequest), anyMap()))
            .thenReturn(ResponseEntity.ok("Updated"));
        when(chromaClient.deleteCollection(eq(tenant), eq(database), eq("collection-456"), anyMap()))
            .thenReturn(ResponseEntity.ok("Deleted"));

        // Act
        ResponseEntity<String> created = chromaCollectionClient.createCollection(tenant, database, collectionRequest);
        ResponseEntity<String> retrieved = chromaCollectionClient.getCollection(tenant, database, "collection-456");
        ResponseEntity<String> updated = chromaCollectionClient.updateCollection(tenant, database, "collection-456", updateRequest);
        ResponseEntity<String> deleted = chromaCollectionClient.deleteCollection(tenant, database, "collection-456");

        // Assert
        assertEquals(HttpStatus.OK, created.getStatusCode());
        assertEquals(HttpStatus.OK, retrieved.getStatusCode());
        assertEquals(HttpStatus.OK, updated.getStatusCode());
        assertEquals(HttpStatus.OK, deleted.getStatusCode());
        
        verify(chromaClient, times(1)).createCollection(eq(tenant), eq(database), eq(collectionRequest), anyMap());
        verify(chromaClient, times(1)).getCollection(eq(tenant), eq(database), eq("collection-456"), anyMap());
        verify(chromaClient, times(1)).updateCollection(eq(tenant), eq(database), eq("collection-456"), eq(updateRequest), anyMap());
        verify(chromaClient, times(1)).deleteCollection(eq(tenant), eq(database), eq("collection-456"), anyMap());
    }

    @Test
    @DisplayName("Should handle collection operations with different parameters")
    void testCollectionOperations_WithDifferentParameters() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String collectionId = "collection-456";
        
        when(chromaClient.getCollectionByCrn(eq("tenant-123:test-db:collection-456"), anyMap()))
            .thenReturn(ResponseEntity.ok("{\"id\":\"collection-456\"}"));
        when(chromaClient.getCollectionsCount(eq(tenant), eq(database), anyMap()))
            .thenReturn(ResponseEntity.ok("10"));
        when(chromaClient.listCollections(eq(tenant), eq(database), eq("limit=5"), anyMap()))
            .thenReturn(ResponseEntity.ok("[]"));
        when(chromaClient.countCollection(eq(collectionId), anyMap()))
            .thenReturn(ResponseEntity.ok("100"));

        // Act
        ResponseEntity<String> byCrn = chromaCollectionClient.getCollectionByCrn("tenant-123:test-db:collection-456");
        ResponseEntity<String> count = chromaCollectionClient.getCollectionsCount(tenant, database);
        ResponseEntity<String> list = chromaCollectionClient.listCollections(tenant, database, "limit=5");
        ResponseEntity<String> itemCount = chromaCollectionClient.countCollection(collectionId);

        // Assert
        assertEquals(HttpStatus.OK, byCrn.getStatusCode());
        assertEquals(HttpStatus.OK, count.getStatusCode());
        assertEquals(HttpStatus.OK, list.getStatusCode());
        assertEquals(HttpStatus.OK, itemCount.getStatusCode());
        
        verify(chromaClient, times(1)).getCollectionByCrn(eq("tenant-123:test-db:collection-456"), anyMap());
        verify(chromaClient, times(1)).getCollectionsCount(eq(tenant), eq(database), anyMap());
        verify(chromaClient, times(1)).listCollections(eq(tenant), eq(database), eq("limit=5"), anyMap());
        verify(chromaClient, times(1)).countCollection(eq(collectionId), anyMap());
    }
}