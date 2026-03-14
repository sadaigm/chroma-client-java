package com.sa.gms.restclient.chromadb.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sa.gms.restclient.chromadb.core.IChromaClient;
import com.sa.gms.restclient.chromadb.dto.CreateTenantRequest;
import com.sa.gms.restclient.chromadb.dto.UpdateTenantRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
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
 * Unit tests for ChromaTenantClient.
 * Tests all tenant-related operations including create, get, and update.
 */
@DisplayName("ChromaTenantClient Tests")
class ChromaTenantClientTest {

    @Mock
    private IChromaClient chromaClient;

    @InjectMocks
    private ChromaTenantClient chromaTenantClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ==================== createTenant Tests ====================

    @Test
    @DisplayName("Should create tenant successfully with DTO")
    void testCreateTenant_Success() throws JsonProcessingException {
        // Arrange
        CreateTenantRequest request = new CreateTenantRequest("test-tenant");
        String expectedJson = "{\"name\":\"test-tenant\"}";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Tenant created successfully");
        
        when(chromaClient.createTenant(eq(expectedJson), any(Map.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaTenantClient.createTenant(request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tenant created successfully", response.getBody());
        verify(chromaClient, times(1)).createTenant(eq(expectedJson), any(Map.class));
    }

    @Test
    @DisplayName("Should create tenant successfully with DTO and custom headers")
    void testCreateTenant_WithHeaders_Success() throws JsonProcessingException {
        // Arrange
        CreateTenantRequest request = new CreateTenantRequest("test-tenant");
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer token123");
        headers.put("X-Custom-Header", "custom-value");
        
        String expectedJson = "{\"name\":\"test-tenant\"}";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Tenant created successfully");
        
        when(chromaClient.createTenant(eq(expectedJson), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaTenantClient.createTenant(request, headers);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tenant created successfully", response.getBody());
        verify(chromaClient, times(1)).createTenant(eq(expectedJson), eq(headers));
    }

    @Test
    @DisplayName("Should create tenant with empty name")
    void testCreateTenant_EmptyName() throws JsonProcessingException {
        // Arrange
        CreateTenantRequest request = new CreateTenantRequest("");
        String expectedJson = "{\"name\":\"\"}";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Tenant created");
        
        when(chromaClient.createTenant(eq(expectedJson), any(Map.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaTenantClient.createTenant(request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).createTenant(eq(expectedJson), any(Map.class));
    }

    @Test
    @DisplayName("Should create tenant with special characters in name")
    void testCreateTenant_SpecialCharacters() throws JsonProcessingException {
        // Arrange
        CreateTenantRequest request = new CreateTenantRequest("tenant-123_test@example.com");
        String expectedJson = "{\"name\":\"tenant-123_test@example.com\"}";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Tenant created");
        
        when(chromaClient.createTenant(eq(expectedJson), any(Map.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaTenantClient.createTenant(request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).createTenant(eq(expectedJson), any(Map.class));
    }

    // ==================== getTenant Tests ====================

    @Test
    @DisplayName("Should get tenant successfully")
    void testGetTenant_Success() {
        // Arrange
        String tenantId = "tenant-123";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("{\"id\":\"tenant-123\",\"name\":\"test-tenant\"}");
        
        when(chromaClient.getTenant(eq(tenantId), any(Map.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaTenantClient.getTenant(tenantId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"id\":\"tenant-123\",\"name\":\"test-tenant\"}", response.getBody());
        verify(chromaClient, times(1)).getTenant(eq(tenantId), any(Map.class));
    }

    @Test
    @DisplayName("Should handle tenant not found")
    void testGetTenant_NotFound() {
        // Arrange
        String tenantId = "non-existent-tenant";
        ResponseEntity<String> expectedResponse = ResponseEntity.notFound().build();
        
        when(chromaClient.getTenant(eq(tenantId), any(Map.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaTenantClient.getTenant(tenantId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(chromaClient, times(1)).getTenant(eq(tenantId), any(Map.class));
    }

    @Test
    @DisplayName("Should get tenant with null tenant ID")
    void testGetTenant_NullTenantId() {
        // Arrange
        String tenantId = null;
        ResponseEntity<String> expectedResponse = ResponseEntity.badRequest().build();
        
        when(chromaClient.getTenant(isNull(), any(Map.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaTenantClient.getTenant(tenantId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(chromaClient, times(1)).getTenant(isNull(), any(Map.class));
    }

    @Test
    @DisplayName("Should get tenant with empty tenant ID")
    void testGetTenant_EmptyTenantId() {
        // Arrange
        String tenantId = "";
        ResponseEntity<String> expectedResponse = ResponseEntity.badRequest().build();
        
        when(chromaClient.getTenant(eq(tenantId), any(Map.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaTenantClient.getTenant(tenantId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(chromaClient, times(1)).getTenant(eq(tenantId), any(Map.class));
    }

    // ==================== updateTenant Tests ====================

    @Test
    @DisplayName("Should update tenant successfully with string request")
    void testUpdateTenant_StringRequest_Success() {
        // Arrange
        String tenantId = "tenant-123";
        String updateRequest = "{\"resource_name\":\"updated-tenant\"}";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Tenant updated successfully");
        
        when(chromaClient.updateTenant(eq(tenantId), eq(updateRequest), any(Map.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaTenantClient.updateTenant(tenantId, updateRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tenant updated successfully", response.getBody());
        verify(chromaClient, times(1)).updateTenant(eq(tenantId), eq(updateRequest), any(Map.class));
    }

    @Test
    @DisplayName("Should update tenant successfully with DTO")
    void testUpdateTenant_Dto_Success() throws JsonProcessingException {
        // Arrange
        String tenantId = "tenant-123";
        UpdateTenantRequest request = new UpdateTenantRequest("updated-tenant");
        String expectedJson = "{\"resource_name\":\"updated-tenant\"}";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Tenant updated successfully");
        
        when(chromaClient.updateTenant(eq(tenantId), eq(expectedJson), any(Map.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaTenantClient.updateTenant(tenantId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tenant updated successfully", response.getBody());
        verify(chromaClient, times(1)).updateTenant(eq(tenantId), eq(expectedJson), any(Map.class));
    }

    @Test
    @DisplayName("Should update tenant successfully with DTO and custom headers")
    void testUpdateTenant_Dto_WithHeaders_Success() throws JsonProcessingException {
        // Arrange
        String tenantId = "tenant-123";
        UpdateTenantRequest request = new UpdateTenantRequest("updated-tenant");
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer token123");
        headers.put("X-Custom-Header", "custom-value");
        
        String expectedJson = "{\"resource_name\":\"updated-tenant\"}";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Tenant updated successfully");
        
        when(chromaClient.updateTenant(eq(tenantId), eq(expectedJson), eq(headers)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaTenantClient.updateTenant(tenantId, request, headers);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tenant updated successfully", response.getBody());
        verify(chromaClient, times(1)).updateTenant(eq(tenantId), eq(expectedJson), eq(headers));
    }

    @Test
    @DisplayName("Should handle tenant not found when updating")
    void testUpdateTenant_NotFound() {
        // Arrange
        String tenantId = "non-existent-tenant";
        String updateRequest = "{\"resource_name\":\"updated-tenant\"}";
        ResponseEntity<String> expectedResponse = ResponseEntity.notFound().build();
        
        when(chromaClient.updateTenant(eq(tenantId), eq(updateRequest), any(Map.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaTenantClient.updateTenant(tenantId, updateRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(chromaClient, times(1)).updateTenant(eq(tenantId), eq(updateRequest), any(Map.class));
    }

    @Test
    @DisplayName("Should update tenant with null resource name in DTO")
    void testUpdateTenant_Dto_NullResourceName() throws JsonProcessingException {
        // Arrange
        String tenantId = "tenant-123";
        UpdateTenantRequest request = new UpdateTenantRequest(null);
        String expectedJson = "{\"resource_name\":null}";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Tenant updated");
        
        when(chromaClient.updateTenant(eq(tenantId), eq(expectedJson), any(Map.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaTenantClient.updateTenant(tenantId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).updateTenant(eq(tenantId), eq(expectedJson), any(Map.class));
    }

    @Test
    @DisplayName("Should update tenant with empty resource name in DTO")
    void testUpdateTenant_Dto_EmptyResourceName() throws JsonProcessingException {
        // Arrange
        String tenantId = "tenant-123";
        UpdateTenantRequest request = new UpdateTenantRequest("");
        String expectedJson = "{\"resource_name\":\"\"}";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Tenant updated");
        
        when(chromaClient.updateTenant(eq(tenantId), eq(expectedJson), any(Map.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaTenantClient.updateTenant(tenantId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).updateTenant(eq(tenantId), eq(expectedJson), any(Map.class));
    }

    @Test
    @DisplayName("Should update tenant with special characters in resource name")
    void testUpdateTenant_Dto_SpecialCharacters() throws JsonProcessingException {
        // Arrange
        String tenantId = "tenant-123";
        UpdateTenantRequest request = new UpdateTenantRequest("updated-tenant_123@example.com");
        String expectedJson = "{\"resource_name\":\"updated-tenant_123@example.com\"}";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Tenant updated");
        
        when(chromaClient.updateTenant(eq(tenantId), eq(expectedJson), any(Map.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaTenantClient.updateTenant(tenantId, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(chromaClient, times(1)).updateTenant(eq(tenantId), eq(expectedJson), any(Map.class));
    }

    @Test
    @DisplayName("Should update tenant with empty string request")
    void testUpdateTenant_EmptyStringRequest() {
        // Arrange
        String tenantId = "tenant-123";
        String updateRequest = "";
        ResponseEntity<String> expectedResponse = ResponseEntity.badRequest().build();
        
        when(chromaClient.updateTenant(eq(tenantId), eq(updateRequest), any(Map.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = chromaTenantClient.updateTenant(tenantId, updateRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(chromaClient, times(1)).updateTenant(eq(tenantId), eq(updateRequest), any(Map.class));
    }

    // ==================== Integration Tests ====================

    @Test
    @DisplayName("Should handle complete tenant lifecycle")
    void testCompleteTenantLifecycle() throws JsonProcessingException {
        // Arrange
        CreateTenantRequest createRequest = new CreateTenantRequest("lifecycle-tenant");
        String createJson = "{\"name\":\"lifecycle-tenant\"}";
        ResponseEntity<String> createResponse = ResponseEntity.ok("{\"id\":\"tenant-456\"}");
        
        String tenantId = "tenant-456";
        ResponseEntity<String> getResponse = ResponseEntity.ok("{\"id\":\"tenant-456\",\"name\":\"lifecycle-tenant\"}");
        
        UpdateTenantRequest updateRequest = new UpdateTenantRequest("updated-lifecycle-tenant");
        String updateJson = "{\"resource_name\":\"updated-lifecycle-tenant\"}";
        ResponseEntity<String> updateResponse = ResponseEntity.ok("Tenant updated");
        
        when(chromaClient.createTenant(eq(createJson), any(Map.class)))
            .thenReturn(createResponse);
        when(chromaClient.getTenant(eq(tenantId), any(Map.class)))
            .thenReturn(getResponse);
        when(chromaClient.updateTenant(eq(tenantId), eq(updateJson), any(Map.class)))
            .thenReturn(updateResponse);

        // Act
        ResponseEntity<String> created = chromaTenantClient.createTenant(createRequest);
        ResponseEntity<String> retrieved = chromaTenantClient.getTenant(tenantId);
        ResponseEntity<String> updated = chromaTenantClient.updateTenant(tenantId, updateRequest);

        // Assert
        assertEquals(HttpStatus.OK, created.getStatusCode());
        assertEquals(HttpStatus.OK, retrieved.getStatusCode());
        assertEquals(HttpStatus.OK, updated.getStatusCode());
        
        verify(chromaClient, times(1)).createTenant(eq(createJson), any(Map.class));
        verify(chromaClient, times(1)).getTenant(eq(tenantId), any(Map.class));
        verify(chromaClient, times(1)).updateTenant(eq(tenantId), eq(updateJson), any(Map.class));
    }

    @Test
    @DisplayName("Should handle multiple custom headers correctly")
    void testMultipleCustomHeaders() throws JsonProcessingException {
        // Arrange
        CreateTenantRequest createRequest = new CreateTenantRequest("test-tenant");
        Map<String, String> createHeaders = new HashMap<>();
        createHeaders.put("Authorization", "Bearer token123");
        createHeaders.put("X-Request-ID", "req-123");
        createHeaders.put("X-Tenant-ID", "tenant-456");
        
        String createJson = "{\"name\":\"test-tenant\"}";
        ResponseEntity<String> createResponse = ResponseEntity.ok("Tenant created");
        
        String tenantId = "tenant-456";
        UpdateTenantRequest updateRequest = new UpdateTenantRequest("updated-tenant");
        Map<String, String> updateHeaders = new HashMap<>();
        updateHeaders.put("Authorization", "Bearer token456");
        updateHeaders.put("X-Request-ID", "req-456");
        
        String updateJson = "{\"resource_name\":\"updated-tenant\"}";
        ResponseEntity<String> updateResponse = ResponseEntity.ok("Tenant updated");
        
        when(chromaClient.createTenant(eq(createJson), eq(createHeaders)))
            .thenReturn(createResponse);
        when(chromaClient.updateTenant(eq(tenantId), eq(updateJson), eq(updateHeaders)))
            .thenReturn(updateResponse);

        // Act
        ResponseEntity<String> created = chromaTenantClient.createTenant(createRequest, createHeaders);
        ResponseEntity<String> updated = chromaTenantClient.updateTenant(tenantId, updateRequest, updateHeaders);

        // Assert
        assertEquals(HttpStatus.OK, created.getStatusCode());
        assertEquals(HttpStatus.OK, updated.getStatusCode());
        
        verify(chromaClient, times(1)).createTenant(eq(createJson), eq(createHeaders));
        verify(chromaClient, times(1)).updateTenant(eq(tenantId), eq(updateJson), eq(updateHeaders));
    }
}