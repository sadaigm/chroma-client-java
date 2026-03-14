package com.sa.gms.restclient.chromadb.core;

import com.sa.gms.restclient.chromadb.config.ChromaProperties;
import com.sa.gms.restclient.chromadb.connection.IRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ChromaClientImpl.
 * Tests URL construction and proper delegation to IRestClient for all API operations.
 */
@DisplayName("ChromaClientImpl Tests")
class ChromaClientImplTest {

    @Mock
    private IRestClient restClient;

    @Mock
    private ChromaProperties chromaProperties;

    private ChromaClientImpl chromaClientImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(chromaProperties.getHost()).thenReturn("http://localhost:8000");
        chromaClientImpl = new ChromaClientImpl(restClient, chromaProperties);
    }

    // ==================== Constructor Tests ====================

    @Test
    @DisplayName("Should initialize with default constructor")
    void testDefaultConstructor() {
        // Act
        ChromaClientImpl client = new ChromaClientImpl();

        // Assert
        assertNotNull(client);
        assertEquals("http://localhost:8000", client.getBaseUrl());
    }

    // ==================== Base URL Management Tests ====================

    @Test
    @DisplayName("Should set base URL")
    void testSetBaseUrl() {
        // Arrange
        String newBaseUrl = "http://new-host:9000";

        // Act
        chromaClientImpl.setBaseUrl(newBaseUrl);

        // Assert
        assertEquals(newBaseUrl, chromaClientImpl.getBaseUrl());
    }

    @Test
    @DisplayName("Should get base URL")
    void testGetBaseUrl() {
        // Act
        String baseUrl = chromaClientImpl.getBaseUrl();

        // Assert
        assertEquals("http://localhost:8000", baseUrl);
    }

    // ==================== Tenant Operations Tests ====================

    @Test
    @DisplayName("Should create tenant with correct URL")
    void testCreateTenant() {
        // Arrange
        String tenantRequest = "{\"name\":\"test-tenant\"}";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Tenant created");
        when(restClient.post(anyString(), eq(tenantRequest), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.createTenant(tenantRequest, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).post(eq("http://localhost:8000/api/v2/tenants"), eq(tenantRequest), eq(headers));
    }

    @Test
    @DisplayName("Should get tenant with correct URL")
    void testGetTenant() {
        // Arrange
        String tenantId = "tenant-123";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"id\":\"tenant-123\"}");
        when(restClient.get(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.getTenant(tenantId, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/tenants/tenant-123"), eq(headers));
    }

    @Test
    @DisplayName("Should update tenant with correct URL")
    void testUpdateTenant() {
        // Arrange
        String tenantId = "tenant-123";
        String updateRequest = "{\"name\":\"updated-tenant\"}";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Tenant updated");
        when(restClient.patch(anyString(), eq(updateRequest), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.updateTenant(tenantId, updateRequest, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).patch(eq("http://localhost:8000/api/v2/tenants/tenant-123"), eq(updateRequest), eq(headers));
    }

    // ==================== Database Operations Tests ====================

    @Test
    @DisplayName("Should create database with correct URL")
    void testCreateDatabase() {
        // Arrange
        String tenantId = "tenant-123";
        String dbRequest = "{\"name\":\"test-db\"}";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Database created");
        when(restClient.post(anyString(), eq(dbRequest), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.createDatabase(tenantId, dbRequest, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).post(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases"), eq(dbRequest), eq(headers));
    }

    @Test
    @DisplayName("Should list databases with correct URL")
    void testListDatabases() {
        // Arrange
        String tenantId = "tenant-123";
        String queryParams = "limit=10&offset=0";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[{\"name\":\"db1\"}]");
        when(restClient.get(anyString(), eq(queryParams), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.listDatabases(tenantId, queryParams, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases"), eq(queryParams), eq(headers));
    }

    @Test
    @DisplayName("Should get database with correct URL")
    void testGetDatabase() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"name\":\"test-db\"}");
        when(restClient.get(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.getDatabase(tenantId, databaseName, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db"), eq(headers));
    }

    @Test
    @DisplayName("Should delete database with correct URL")
    void testDeleteDatabase() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Database deleted");
        when(restClient.delete(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.deleteDatabase(tenantId, databaseName, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).delete(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db"), eq(headers));
    }

    // ==================== Collection Operations Tests ====================

    @Test
    @DisplayName("Should create collection with tenant and database")
    void testCreateCollection_WithTenantAndDatabase() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String collectionRequest = "{\"name\":\"test-collection\"}";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Collection created");
        when(restClient.post(anyString(), eq(collectionRequest), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.createCollection(tenant, database, collectionRequest, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).post(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections"), eq(collectionRequest), eq(headers));
    }

    @Test
    @DisplayName("Should create collection without tenant and database")
    void testCreateCollection_WithoutTenantAndDatabase() {
        // Arrange
        String collectionRequest = "{\"name\":\"test-collection\"}";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Collection created");
        when(restClient.post(anyString(), eq(collectionRequest), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.createCollection(collectionRequest, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).post(eq("http://localhost:8000/api/v2/collections"), eq(collectionRequest), eq(headers));
    }

    @Test
    @DisplayName("Should get collection by CRN")
    void testGetCollectionByCrn() {
        // Arrange
        String crn = "tenant-123:test-db:collection-456";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"id\":\"collection-456\"}");
        when(restClient.get(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.getCollectionByCrn(crn, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/collections/tenant-123:test-db:collection-456"), eq(headers));
    }

    @Test
    @DisplayName("Should get collection with tenant and database")
    void testGetCollection_WithTenantAndDatabase() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String collectionId = "collection-456";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"id\":\"collection-456\"}");
        when(restClient.get(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.getCollection(tenant, database, collectionId, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections/collection-456"), eq(headers));
    }

    @Test
    @DisplayName("Should get collection by ID only")
    void testGetCollection_ByIdOnly() {
        // Arrange
        String collectionId = "collection-456";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"id\":\"collection-456\"}");
        when(restClient.get(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.getCollection(collectionId, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/collections/collection-456"), eq(headers));
    }

    @Test
    @DisplayName("Should update collection with correct URL")
    void testUpdateCollection() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String collectionId = "collection-456";
        String updateRequest = "{\"name\":\"updated-collection\"}";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Collection updated");
        when(restClient.put(anyString(), eq(updateRequest), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.updateCollection(tenant, database, collectionId, updateRequest, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).put(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections/collection-456"), eq(updateRequest), eq(headers));
    }

    @Test
    @DisplayName("Should delete collection with tenant and database")
    void testDeleteCollection_WithTenantAndDatabase() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String collectionId = "collection-456";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Collection deleted");
        when(restClient.delete(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.deleteCollection(tenant, database, collectionId, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).delete(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections/collection-456"), eq(headers));
    }

    @Test
    @DisplayName("Should delete collection by ID only")
    void testDeleteCollection_ByIdOnly() {
        // Arrange
        String collectionId = "collection-456";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Collection deleted");
        when(restClient.delete(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.deleteCollection(collectionId, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).delete(eq("http://localhost:8000/api/v2/collections/collection-456"), eq(headers));
    }

    @Test
    @DisplayName("Should fork collection with correct URL")
    void testForkCollection() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String collectionId = "collection-456";
        String forkRequest = "{\"name\":\"forked-collection\"}";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Collection forked");
        when(restClient.post(anyString(), eq(forkRequest), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.forkCollection(tenant, database, collectionId, forkRequest, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).post(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections/collection-456/fork"), eq(forkRequest), eq(headers));
    }

    @Test
    @DisplayName("Should get collections count with correct URL")
    void testGetCollectionsCount() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("5");
        when(restClient.get(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.getCollectionsCount(tenant, database, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections_count"), eq(headers));
    }

    @Test
    @DisplayName("Should list collections with tenant and database")
    void testListCollections_WithTenantAndDatabase() {
        // Arrange
        String tenant = "tenant-123";
        String database = "test-db";
        String queryParams = "limit=10&offset=0";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[{\"id\":\"collection-1\"}]");
        when(restClient.get(anyString(), eq(queryParams), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.listCollections(tenant, database, queryParams, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections"), eq(queryParams), eq(headers));
    }

    @Test
    @DisplayName("Should list collections without tenant and database")
    void testListCollections_WithoutTenantAndDatabase() {
        // Arrange
        String queryParams = "limit=10&offset=0";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[{\"id\":\"collection-1\"}]");
        when(restClient.get(anyString(), eq(queryParams), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.listCollections(queryParams, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/collections"), eq(queryParams), eq(headers));
    }

    @Test
    @DisplayName("Should count collection items with correct URL")
    void testCountCollection() {
        // Arrange
        String collectionId = "collection-456";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("1000");
        when(restClient.get(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.countCollection(collectionId, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/collections/collection-456/count"), eq(headers));
    }

    // ==================== Document Operations Tests ====================

    @Test
    @DisplayName("Should add records with correct URL")
    void testAddRecords() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        String recordsRequest = "{\"ids\":[\"id1\",\"id2\"]}";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Records added");
        when(restClient.post(anyString(), eq(recordsRequest), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.addRecords(tenantId, databaseName, collectionId, recordsRequest, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).post(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections/collection-456/add"), eq(recordsRequest), eq(headers));
    }

    @Test
    @DisplayName("Should count records with correct URL")
    void testCountRecords() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        String queryParams = "read_level=index_and_wal";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("100");
        when(restClient.get(anyString(), eq(queryParams), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.countRecords(tenantId, databaseName, collectionId, queryParams, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections/collection-456/count"), eq(queryParams), eq(headers));
    }

    @Test
    @DisplayName("Should delete records with correct URL")
    void testDeleteRecords() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        String deleteRequest = "{\"ids\":[\"id1\",\"id2\"]}";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Records deleted");
        when(restClient.post(anyString(), eq(deleteRequest), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.deleteRecords(tenantId, databaseName, collectionId, deleteRequest, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).post(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections/collection-456/delete"), eq(deleteRequest), eq(headers));
    }

    @Test
    @DisplayName("Should get records with correct URL")
    void testGetRecords() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        String getRequest = "{\"ids\":[\"id1\",\"id2\"]}";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[{\"id\":\"id1\"}]");
        when(restClient.post(anyString(), eq(getRequest), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.getRecords(tenantId, databaseName, collectionId, getRequest, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).post(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections/collection-456/get"), eq(getRequest), eq(headers));
    }

    @Test
    @DisplayName("Should get indexing status with correct URL")
    void testGetIndexingStatus() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"status\":\"indexed\"}");
        when(restClient.get(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.getIndexingStatus(tenantId, databaseName, collectionId, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections/collection-456/indexing_status"), eq(headers));
    }

    @Test
    @DisplayName("Should query collection with null query params")
    void testQueryCollection_NullQueryParams() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        String queryRequest = "{\"n_results\":5}";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[]");
        when(restClient.post(anyString(), eq(queryRequest), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.queryCollection(tenantId, databaseName, collectionId, null, queryRequest, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).post(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections/collection-456/query"), eq(queryRequest), eq(headers));
    }

    @Test
    @DisplayName("Should query collection with query params")
    void testQueryCollection_WithQueryParams() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        String queryParams = "limit=10&offset=0";
        String queryRequest = "{\"n_results\":5}";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[]");
        when(restClient.post(anyString(), eq(queryRequest), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.queryCollection(tenantId, databaseName, collectionId, queryParams, queryRequest, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).post(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections/collection-456/query?limit=10&offset=0"), eq(queryRequest), eq(headers));
    }

    @Test
    @DisplayName("Should search records with correct URL")
    void testSearchRecords() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        String requestBody = "{\"n_results\":5}";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("[]");
        when(restClient.post(anyString(), eq(requestBody), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.searchRecords(tenantId, databaseName, collectionId, requestBody, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).post(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections/collection-456/search"), eq(requestBody), eq(headers));
    }

    @Test
    @DisplayName("Should update records with correct URL")
    void testUpdateRecords() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        String requestBody = "{\"ids\":[\"id1\"],\"documents\":[\"updated\"]}";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Records updated");
        when(restClient.post(anyString(), eq(requestBody), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.updateRecords(tenantId, databaseName, collectionId, requestBody, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).post(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections/collection-456/update"), eq(requestBody), eq(headers));
    }

    @Test
    @DisplayName("Should upsert records with correct URL")
    void testUpsertRecords() {
        // Arrange
        String tenantId = "tenant-123";
        String databaseName = "test-db";
        String collectionId = "collection-456";
        String requestBody = "{\"ids\":[\"id1\"],\"documents\":[\"doc1\"]}";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Records upserted");
        when(restClient.post(anyString(), eq(requestBody), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.upsertRecords(tenantId, databaseName, collectionId, requestBody, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).post(eq("http://localhost:8000/api/v2/tenants/tenant-123/databases/test-db/collections/collection-456/upsert"), eq(requestBody), eq(headers));
    }

    // ==================== Authentication Operations Tests ====================

    @Test
    @DisplayName("Should get identity with correct URL")
    void testGetIdentity() {
        // Arrange
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"id\":\"user-123\"}");
        when(restClient.get(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.getIdentity(headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/auth/identity"), eq(headers));
    }

    // ==================== System Operations Tests ====================

    @Test
    @DisplayName("Should perform healthcheck with correct URL")
    void testHealthcheck() {
        // Arrange
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"status\":\"healthy\"}");
        when(restClient.get(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.healthcheck(headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/healthcheck"), eq(headers));
    }

    @Test
    @DisplayName("Should get heartbeat with correct URL")
    void testHeartbeat() {
        // Arrange
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("12345678901234567");
        when(restClient.get(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.heartbeat(headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/heartbeat"), eq(headers));
    }

    @Test
    @DisplayName("Should reset with correct URL")
    void testReset() {
        // Arrange
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Reset complete");
        when(restClient.post(anyString(), isNull(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.reset(headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).post(eq("http://localhost:8000/api/v2/reset"), isNull(), eq(headers));
    }

    @Test
    @DisplayName("Should get version with correct URL")
    void testGetVersion() {
        // Arrange
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("0.5.0");
        when(restClient.get(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.getVersion(headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/version"), eq(headers));
    }

    @Test
    @DisplayName("Should perform pre-flight checks with correct URL")
    void testPreFlightChecks() {
        // Arrange
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"status\":\"ready\"}");
        when(restClient.get(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.preFlightChecks(headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/pre-flight-checks"), eq(headers));
    }

    // ==================== Integration Tests ====================
    
    @Test
    @DisplayName("Should handle URL construction with special characters")
    void testUrlConstructionWithSpecialCharacters() {
        // Arrange
        String tenantId = "tenant-123_test";
        String databaseName = "test-db.example.com";
        String collectionId = "collection-456_special";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("OK");
        when(restClient.get(anyString(), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaClientImpl.getCollection(tenantId, databaseName, collectionId, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restClient, times(1)).get(eq("http://localhost:8000/api/v2/tenants/tenant-123_test/databases/test-db.example.com/collections/collection-456_special"), eq(headers));
    }
}