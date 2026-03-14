package com.sa.gms.restclient.chromadb.clients;

import com.sa.gms.restclient.chromadb.core.IChromaClient;
import com.sa.gms.restclient.chromadb.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ChromaDocumentClient.
 * Tests all document-related operations including add, get, update, delete, query, and search.
 */
@DisplayName("ChromaDocumentClient Tests")
class ChromaDocumentClientTest {

    @Mock
    private IChromaClient chromaClient;

    @InjectMocks
    private ChromaDocumentClient chromaDocumentClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ==================== addRecords Tests ====================

    @Test
    @DisplayName("Should add records successfully")
    void testAddRecords_Success() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        AddRecordsRequest request = AddRecordsRequest.builder()
                .ids(Arrays.asList("id1", "id2"))
                .documents(Arrays.asList("doc1", "doc2"))
                .build();
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Records added successfully");
        when(chromaClient.addRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.addRecords(tenantId, databaseName, collectionId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Records added successfully", response.getBody());
        verify(chromaClient, times(1)).addRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
    }

    @Test
    @DisplayName("Should add records with embeddings")
    void testAddRecords_WithEmbeddings() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        List<Float> embedding = Arrays.asList(0.1f, 0.2f, 0.3f);
        AddRecordsRequest request = AddRecordsRequest.builder()
                .ids(Arrays.asList("id1"))
                .embeddings(Collections.singletonList(embedding))
                .build();
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Records added");
        when(chromaClient.addRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.addRecords(tenantId, databaseName, collectionId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).addRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
    }

    // ==================== countRecords Tests ====================

    @Test
    @DisplayName("Should count records successfully")
    void testCountRecords_Success() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        String readLevel = "index_and_wal";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("100");
        when(chromaClient.countRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.countRecords(tenantId, databaseName, collectionId, readLevel);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("100", response.getBody());
        verify(chromaClient, times(1)).countRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
    }

    @Test
    @DisplayName("Should count records with null read level")
    void testCountRecords_NullReadLevel() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("100");
        when(chromaClient.countRecords(eq(tenantId), eq(databaseName), eq(collectionId), isNull(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.countRecords(tenantId, databaseName, collectionId, null);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).countRecords(eq(tenantId), eq(databaseName), eq(collectionId), isNull(), anyMap());
    }

    // ==================== deleteRecords Tests ====================

    @Test
    @DisplayName("Should delete records successfully")
    void testDeleteRecords_Success() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        DeleteRecordsRequest request = DeleteRecordsRequest.builder()
                .ids(Arrays.asList("id1", "id2"))
                .build();
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Records deleted successfully");
        when(chromaClient.deleteRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.deleteRecords(tenantId, databaseName, collectionId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Records deleted successfully", response.getBody());
        verify(chromaClient, times(1)).deleteRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
    }

    @Test
    @DisplayName("Should delete records with where clause")
    void testDeleteRecords_WithWhere() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        DeleteRecordsRequest request = DeleteRecordsRequest.builder()
                .where("{\"category\":\"test\"}")
                .build();
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Records deleted");
        when(chromaClient.deleteRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.deleteRecords(tenantId, databaseName, collectionId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).deleteRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
    }

    // ==================== getRecords Tests ====================

    @Test
    @DisplayName("Should get records successfully")
    void testGetRecords_Success() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        GetRecordsRequest request = GetRecordsRequest.builder()
                .ids(Arrays.asList("id1", "id2"))
                .build();
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[{\"id\":\"id1\",\"document\":\"doc1\"}]");
        when(chromaClient.getRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.getRecords(tenantId, databaseName, collectionId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).getRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
    }

    @Test
    @DisplayName("Should get records with limit and offset")
    void testGetRecords_WithPagination() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        GetRecordsRequest request = GetRecordsRequest.builder()
                .limit(10)
                .offset(0)
                .build();
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[]");
        when(chromaClient.getRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.getRecords(tenantId, databaseName, collectionId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).getRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
    }

    // ==================== getIndexingStatus Tests ====================

    @Test
    @DisplayName("Should get indexing status successfully")
    void testGetIndexingStatus_Success() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"status\":\"indexed\"}");
        when(chromaClient.getIndexingStatus(eq(tenantId), eq(databaseName), eq(collectionId), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.getIndexingStatus(tenantId, databaseName, collectionId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"status\":\"indexed\"}", response.getBody());
        verify(chromaClient, times(1)).getIndexingStatus(eq(tenantId), eq(databaseName), eq(collectionId), anyMap());
    }

    // ==================== queryCollection Tests ====================

    @Test
    @DisplayName("Should query collection successfully")
    void testQueryCollection_Success() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        QueryCollectionRequest request = QueryCollectionRequest.builder()
                .nResults(5)
                .queryEmbeddings(Collections.singletonList(Arrays.asList(0.1f, 0.2f, 0.3f)))
                .build();
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[{\"id\":\"id1\",\"distance\":0.5}]");
        when(chromaClient.queryCollection(eq(tenantId), eq(databaseName), eq(collectionId), isNull(), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.queryCollection(tenantId, databaseName, collectionId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).queryCollection(eq(tenantId), eq(databaseName), eq(collectionId), isNull(), anyString(), anyMap());
    }

    @Test
    @DisplayName("Should query collection with pagination")
    void testQueryCollection_WithPagination() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        Integer limit = 10;
        Integer offset = 0;
        QueryCollectionRequest request = QueryCollectionRequest.builder()
                .nResults(5)
                .build();
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[]");
        when(chromaClient.queryCollection(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.queryCollection(tenantId, databaseName, collectionId, limit, offset, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).queryCollection(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyString(), anyMap());
    }

    // ==================== searchRecords Tests ====================

    @Test
    @DisplayName("Should search records successfully")
    void testSearchRecords_Success() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        SearchRecordsRequest.SearchQuery searchQuery = SearchRecordsRequest.SearchQuery.builder()
                .nResults(5)
                .queryTexts(Collections.singletonList("search term"))
                .build();
        SearchRecordsRequest request = SearchRecordsRequest.builder()
                .searches(Collections.singletonList(searchQuery))
                .build();
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[{\"id\":\"id1\",\"score\":0.9}]");
        when(chromaClient.searchRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.searchRecords(tenantId, databaseName, collectionId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).searchRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
    }

    @Test
    @DisplayName("Should search records with embeddings")
    void testSearchRecords_WithEmbeddings() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        SearchRecordsRequest.SearchQuery searchQuery = SearchRecordsRequest.SearchQuery.builder()
                .nResults(10)
                .queryEmbeddings(Collections.singletonList(Arrays.asList(0.1f, 0.2f, 0.3f)))
                .build();
        SearchRecordsRequest request = SearchRecordsRequest.builder()
                .searches(Collections.singletonList(searchQuery))
                .build();
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[]");
        when(chromaClient.searchRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.searchRecords(tenantId, databaseName, collectionId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).searchRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
    }

    // ==================== updateRecords Tests ====================

    @Test
    @DisplayName("Should update records successfully")
    void testUpdateRecords_Success() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        UpdateRecordsRequest request = UpdateRecordsRequest.builder()
                .ids(Arrays.asList("id1", "id2"))
                .documents(Arrays.asList("updated doc1", "updated doc2"))
                .build();
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Records updated successfully");
        when(chromaClient.updateRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.updateRecords(tenantId, databaseName, collectionId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Records updated successfully", response.getBody());
        verify(chromaClient, times(1)).updateRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
    }

    @Test
    @DisplayName("Should update records with metadata")
    void testUpdateRecords_WithMetadata() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        UpdateRecordsRequest request = UpdateRecordsRequest.builder()
                .ids(Arrays.asList("id1"))
                .metadatas(Collections.singletonList(Collections.singletonMap("key", "value")))
                .build();
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Records updated");
        when(chromaClient.updateRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.updateRecords(tenantId, databaseName, collectionId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).updateRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
    }

    // ==================== upsertRecords Tests ====================

    @Test
    @DisplayName("Should upsert records successfully")
    void testUpsertRecords_Success() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        UpsertRecordsRequest request = UpsertRecordsRequest.builder()
                .ids(Arrays.asList("id1", "id2"))
                .documents(Arrays.asList("doc1", "doc2"))
                .build();
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Records upserted successfully");
        when(chromaClient.upsertRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.upsertRecords(tenantId, databaseName, collectionId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Records upserted successfully", response.getBody());
        verify(chromaClient, times(1)).upsertRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
    }

    @Test
    @DisplayName("Should upsert records with embeddings")
    void testUpsertRecords_WithEmbeddings() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        List<Float> embedding = Arrays.asList(0.1f, 0.2f, 0.3f);
        UpsertRecordsRequest request = UpsertRecordsRequest.builder()
                .ids(Arrays.asList("id1"))
                .embeddings(Collections.singletonList(embedding))
                .build();
        
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Records upserted");
        when(chromaClient.upsertRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaDocumentClient.upsertRecords(tenantId, databaseName, collectionId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).upsertRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
    }

    // ==================== Integration Tests ====================

    @Test
    @DisplayName("Should handle complete document lifecycle")
    void testCompleteDocumentLifecycle() throws Exception {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        
        AddRecordsRequest addRequest = AddRecordsRequest.builder()
                .ids(Arrays.asList("id1"))
                .documents(Collections.singletonList("doc1"))
                .build();
        
        GetRecordsRequest getRequest = GetRecordsRequest.builder()
                .ids(Arrays.asList("id1"))
                .build();
        
        UpdateRecordsRequest updateRequest = UpdateRecordsRequest.builder()
                .ids(Arrays.asList("id1"))
                .documents(Collections.singletonList("updated doc1"))
                .build();
        
        DeleteRecordsRequest deleteRequest = DeleteRecordsRequest.builder()
                .ids(Arrays.asList("id1"))
                .build();
        
        when(chromaClient.addRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(ResponseEntity.ok("Added"));
        when(chromaClient.getRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(ResponseEntity.ok("[{\"id\":\"id1\"}]"));
        when(chromaClient.updateRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(ResponseEntity.ok("Updated"));
        when(chromaClient.deleteRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap()))
            .thenReturn(ResponseEntity.ok("Deleted"));

        // Act
        ResponseEntity<String> added = chromaDocumentClient.addRecords(tenantId, databaseName, collectionId, addRequest);
        ResponseEntity<String> retrieved = chromaDocumentClient.getRecords(tenantId, databaseName, collectionId, getRequest);
        ResponseEntity<String> updated = chromaDocumentClient.updateRecords(tenantId, databaseName, collectionId, updateRequest);
        ResponseEntity<String> deleted = chromaDocumentClient.deleteRecords(tenantId, databaseName, collectionId, deleteRequest);

        // Assert
        assertEquals(HttpStatus.OK, added.getStatusCode());
        assertEquals(HttpStatus.OK, retrieved.getStatusCode());
        assertEquals(HttpStatus.OK, updated.getStatusCode());
        assertEquals(HttpStatus.OK, deleted.getStatusCode());
        
        verify(chromaClient, times(1)).addRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
        verify(chromaClient, times(1)).getRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
        verify(chromaClient, times(1)).updateRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
        verify(chromaClient, times(1)).deleteRecords(eq(tenantId), eq(databaseName), eq(collectionId), anyString(), anyMap());
    }
}