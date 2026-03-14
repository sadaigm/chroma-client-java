package com.sa.gms.restclient.chromadb.connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for KasqlRestClientImpl.
 * Tests all HTTP operations (GET, POST, PATCH, PUT, DELETE) with various parameter combinations.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("KasqlRestClientImpl Tests")
class ChromaRestClientImplTest {

    @Mock
    private RestTemplate restTemplate;

    private ChromaRestClientImpl restClient;

    @BeforeEach
    void setUp() {
        restClient = new ChromaRestClientImpl(restTemplate);
    }

    // ==================== Constructor Tests ====================

    @Test
    @DisplayName("Should create instance with default constructor")
    void testDefaultConstructor() {
        ChromaRestClientImpl client = new ChromaRestClientImpl();
        assertNotNull(client.getRestTemplate());
    }

    @Test
    @DisplayName("Should create instance with custom RestTemplate")
    void testCustomRestTemplateConstructor() {
        ChromaRestClientImpl client = new ChromaRestClientImpl(restTemplate);
        assertNotNull(client);
        assertEquals(restTemplate, client.getRestTemplate());
    }

    @Test
    @DisplayName("Should return RestTemplate instance")
    void testGetRestTemplate() {
        assertEquals(restTemplate, restClient.getRestTemplate());
    }

    // ==================== GET Operation Tests ====================

    @Test
    @DisplayName("Should perform GET request without parameters")
    void testGetWithoutParameters() {
        // Arrange
        String url = "http://example.com/api/test";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Success");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.get(url);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(urlCaptor.capture(), eq(HttpMethod.GET), entityCaptor.capture(), eq(String.class));
        assertEquals(url, urlCaptor.getValue());
        assertEquals(MediaType.APPLICATION_JSON, entityCaptor.getValue().getHeaders().getContentType());
    }

    @Test
    @DisplayName("Should perform GET request with query parameters")
    void testGetWithQueryParams() {
        // Arrange
        String url = "http://example.com/api/test";
        String queryParams = "param1=value1&param2=value2";
        String fullUrl = url + "?" + queryParams;
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Success");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.get(url, queryParams);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        verify(restTemplate).exchange(urlCaptor.capture(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class));
        assertEquals(fullUrl, urlCaptor.getValue());
    }

    @Test
    @DisplayName("Should perform GET request with empty query parameters")
    void testGetWithEmptyQueryParams() {
        // Arrange
        String url = "http://example.com/api/test";
        String queryParams = "";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Success");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.get(url, queryParams);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        verify(restTemplate).exchange(urlCaptor.capture(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class));
        assertEquals(url, urlCaptor.getValue());
    }

    @Test
    @DisplayName("Should perform GET request with null query parameters")
    void testGetWithNullQueryParams() {
        // Arrange
        String url = "http://example.com/api/test";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Success");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.get(url, (String) null);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        verify(restTemplate).exchange(urlCaptor.capture(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class));
        assertEquals(url, urlCaptor.getValue());
    }

    @Test
    @DisplayName("Should perform GET request with headers map")
    void testGetWithHeadersMap() {
        // Arrange
        String url = "http://example.com/api/test";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer token123");
        headers.put("X-Custom-Header", "custom-value");
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Success");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.get(url, headers);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.GET), entityCaptor.capture(), eq(String.class));
        HttpHeaders httpHeaders = entityCaptor.getValue().getHeaders();
        assertEquals(MediaType.APPLICATION_JSON, httpHeaders.getContentType());
        assertEquals("Bearer token123", httpHeaders.getFirst("Authorization"));
        assertEquals("custom-value", httpHeaders.getFirst("X-Custom-Header"));
    }

    @Test
    @DisplayName("Should perform GET request with query parameters and headers")
    void testGetWithQueryParamsAndHeaders() {
        // Arrange
        String url = "http://example.com/api/test";
        String queryParams = "param1=value1";
        String fullUrl = url + "?" + queryParams;
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer token123");
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Success");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.get(url, queryParams, headers);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(urlCaptor.capture(), eq(HttpMethod.GET), entityCaptor.capture(), eq(String.class));
        assertEquals(fullUrl, urlCaptor.getValue());
        assertEquals("Bearer token123", entityCaptor.getValue().getHeaders().getFirst("Authorization"));
    }

    @Test
    @DisplayName("Should perform GET request with empty headers map")
    void testGetWithEmptyHeadersMap() {
        // Arrange
        String url = "http://example.com/api/test";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Success");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.get(url, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class));
    }

    @Test
    @DisplayName("Should perform GET request with null headers map")
    void testGetWithNullHeadersMap() {
        // Arrange
        String url = "http://example.com/api/test";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Success");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.get(url, (Map<String, String>) null);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class));
    }

    // ==================== POST Operation Tests ====================

    @Test
    @DisplayName("Should perform POST request without body")
    void testPostWithoutBody() {
        // Arrange
        String url = "http://example.com/api/test";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Created");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.post(url);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.POST), entityCaptor.capture(), eq(String.class));
        assertEquals(MediaType.APPLICATION_JSON, entityCaptor.getValue().getHeaders().getContentType());
        assertNull(entityCaptor.getValue().getBody());
    }

    @Test
    @DisplayName("Should perform POST request with body")
    void testPostWithBody() {
        // Arrange
        String url = "http://example.com/api/test";
        String requestBody = "{\"name\":\"test\"}";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Created");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.post(url, requestBody);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.POST), entityCaptor.capture(), eq(String.class));
        assertEquals(MediaType.APPLICATION_JSON, entityCaptor.getValue().getHeaders().getContentType());
        assertEquals(requestBody, entityCaptor.getValue().getBody());
    }

    @Test
    @DisplayName("Should perform POST request with body and headers")
    void testPostWithBodyAndHeaders() {
        // Arrange
        String url = "http://example.com/api/test";
        String requestBody = "{\"name\":\"test\"}";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer token123");
        headers.put("X-Custom-Header", "custom-value");
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Created");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.post(url, requestBody, headers);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.POST), entityCaptor.capture(), eq(String.class));
        HttpHeaders httpHeaders = entityCaptor.getValue().getHeaders();
        assertEquals(MediaType.APPLICATION_JSON, httpHeaders.getContentType());
        assertEquals("Bearer token123", httpHeaders.getFirst("Authorization"));
        assertEquals("custom-value", httpHeaders.getFirst("X-Custom-Header"));
        assertEquals(requestBody, entityCaptor.getValue().getBody());
    }

    @Test
    @DisplayName("Should perform POST request with null body and headers")
    void testPostWithNullBodyAndHeaders() {
        // Arrange
        String url = "http://example.com/api/test";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer token123");
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Created");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.post(url, null, headers);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.POST), entityCaptor.capture(), eq(String.class));
        assertEquals("Bearer token123", entityCaptor.getValue().getHeaders().getFirst("Authorization"));
        assertNull(entityCaptor.getValue().getBody());
    }

    @Test
    @DisplayName("Should perform POST request with body and empty headers")
    void testPostWithBodyAndEmptyHeaders() {
        // Arrange
        String url = "http://example.com/api/test";
        String requestBody = "{\"name\":\"test\"}";
        Map<String, String> headers = new HashMap<>();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Created");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.post(url, requestBody, headers);

        // Assert
        assertEquals(expectedResponse, response);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class));
    }

    // ==================== PATCH Operation Tests ====================

    @Test
    @DisplayName("Should perform PATCH request without body")
    void testPatchWithoutBody() {
        // Arrange
        String url = "http://example.com/api/test";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Updated");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PATCH), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.patch(url);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.PATCH), entityCaptor.capture(), eq(String.class));
        assertEquals(MediaType.APPLICATION_JSON, entityCaptor.getValue().getHeaders().getContentType());
        assertNull(entityCaptor.getValue().getBody());
    }

    @Test
    @DisplayName("Should perform PATCH request with body")
    void testPatchWithBody() {
        // Arrange
        String url = "http://example.com/api/test";
        String requestBody = "{\"name\":\"updated\"}";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Updated");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PATCH), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.patch(url, requestBody);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.PATCH), entityCaptor.capture(), eq(String.class));
        assertEquals(MediaType.APPLICATION_JSON, entityCaptor.getValue().getHeaders().getContentType());
        assertEquals(requestBody, entityCaptor.getValue().getBody());
    }

    @Test
    @DisplayName("Should perform PATCH request with body and headers")
    void testPatchWithBodyAndHeaders() {
        // Arrange
        String url = "http://example.com/api/test";
        String requestBody = "{\"name\":\"updated\"}";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer token123");
        headers.put("X-Custom-Header", "custom-value");
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Updated");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PATCH), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.patch(url, requestBody, headers);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.PATCH), entityCaptor.capture(), eq(String.class));
        HttpHeaders httpHeaders = entityCaptor.getValue().getHeaders();
        assertEquals(MediaType.APPLICATION_JSON, httpHeaders.getContentType());
        assertEquals("Bearer token123", httpHeaders.getFirst("Authorization"));
        assertEquals("custom-value", httpHeaders.getFirst("X-Custom-Header"));
        assertEquals(requestBody, entityCaptor.getValue().getBody());
    }

    @Test
    @DisplayName("Should perform PATCH request with null body and headers")
    void testPatchWithNullBodyAndHeaders() {
        // Arrange
        String url = "http://example.com/api/test";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer token123");
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Updated");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PATCH), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.patch(url, null, headers);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.PATCH), entityCaptor.capture(), eq(String.class));
        assertEquals("Bearer token123", entityCaptor.getValue().getHeaders().getFirst("Authorization"));
        assertNull(entityCaptor.getValue().getBody());
    }

    // ==================== PUT Operation Tests ====================

    @Test
    @DisplayName("Should perform PUT request with body")
    void testPutWithBody() {
        // Arrange
        String url = "http://example.com/api/test";
        String requestBody = "{\"name\":\"replaced\"}";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Replaced");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.put(url, requestBody);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.PUT), entityCaptor.capture(), eq(String.class));
        assertEquals(MediaType.APPLICATION_JSON, entityCaptor.getValue().getHeaders().getContentType());
        assertEquals(requestBody, entityCaptor.getValue().getBody());
    }

    @Test
    @DisplayName("Should perform PUT request with body and headers")
    void testPutWithBodyAndHeaders() {
        // Arrange
        String url = "http://example.com/api/test";
        String requestBody = "{\"name\":\"replaced\"}";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer token123");
        headers.put("X-Custom-Header", "custom-value");
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Replaced");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.put(url, requestBody, headers);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.PUT), entityCaptor.capture(), eq(String.class));
        HttpHeaders httpHeaders = entityCaptor.getValue().getHeaders();
        assertEquals(MediaType.APPLICATION_JSON, httpHeaders.getContentType());
        assertEquals("Bearer token123", httpHeaders.getFirst("Authorization"));
        assertEquals("custom-value", httpHeaders.getFirst("X-Custom-Header"));
        assertEquals(requestBody, entityCaptor.getValue().getBody());
    }

    @Test
    @DisplayName("Should perform PUT request with null body and headers")
    void testPutWithNullBodyAndHeaders() {
        // Arrange
        String url = "http://example.com/api/test";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer token123");
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Replaced");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.put(url, null, headers);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.PUT), entityCaptor.capture(), eq(String.class));
        assertEquals("Bearer token123", entityCaptor.getValue().getHeaders().getFirst("Authorization"));
        assertNull(entityCaptor.getValue().getBody());
    }

    // ==================== DELETE Operation Tests ====================

    @Test
    @DisplayName("Should perform DELETE request without parameters")
    void testDeleteWithoutParameters() {
        // Arrange
        String url = "http://example.com/api/test";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Deleted");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.DELETE), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.delete(url);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.DELETE), entityCaptor.capture(), eq(String.class));
        assertEquals(MediaType.APPLICATION_JSON, entityCaptor.getValue().getHeaders().getContentType());
    }

    @Test
    @DisplayName("Should perform DELETE request with query parameters")
    void testDeleteWithQueryParams() {
        // Arrange
        String url = "http://example.com/api/test";
        String queryParams = "id=123";
        String fullUrl = url + "?" + queryParams;
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Deleted");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.DELETE), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.delete(url, queryParams);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        verify(restTemplate).exchange(urlCaptor.capture(), eq(HttpMethod.DELETE), any(HttpEntity.class), eq(String.class));
        assertEquals(fullUrl, urlCaptor.getValue());
    }

    @Test
    @DisplayName("Should perform DELETE request with empty query parameters")
    void testDeleteWithEmptyQueryParams() {
        // Arrange
        String url = "http://example.com/api/test";
        String queryParams = "";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Deleted");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.DELETE), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.delete(url, queryParams);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        verify(restTemplate).exchange(urlCaptor.capture(), eq(HttpMethod.DELETE), any(HttpEntity.class), eq(String.class));
        assertEquals(url, urlCaptor.getValue());
    }

    @Test
    @DisplayName("Should perform DELETE request with headers map")
    void testDeleteWithHeadersMap() {
        // Arrange
        String url = "http://example.com/api/test";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer token123");
        headers.put("X-Custom-Header", "custom-value");
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Deleted");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.DELETE), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.delete(url, headers);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.DELETE), entityCaptor.capture(), eq(String.class));
        HttpHeaders httpHeaders = entityCaptor.getValue().getHeaders();
        assertEquals(MediaType.APPLICATION_JSON, httpHeaders.getContentType());
        assertEquals("Bearer token123", httpHeaders.getFirst("Authorization"));
        assertEquals("custom-value", httpHeaders.getFirst("X-Custom-Header"));
    }

    @Test
    @DisplayName("Should perform DELETE request with multiple headers")
    void testDeleteWithMultipleHeaders() {
        // Arrange
        String url = "http://example.com/api/test";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer token123");
        headers.put("X-Tenant-ID", "tenant-001");
        headers.put("X-Request-ID", "req-123");
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Deleted");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.DELETE), any(HttpEntity.class), eq(String.class)))
            .thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = restClient.delete(url, headers);

        // Assert
        assertEquals(expectedResponse, response);
        ArgumentCaptor<HttpEntity> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.DELETE), entityCaptor.capture(), eq(String.class));
        HttpHeaders httpHeaders = entityCaptor.getValue().getHeaders();
        assertEquals("Bearer token123", httpHeaders.getFirst("Authorization"));
        assertEquals("tenant-001", httpHeaders.getFirst("X-Tenant-ID"));
        assertEquals("req-123", httpHeaders.getFirst("X-Request-ID"));
    }

    // ==================== Error Handling Tests ====================

    @Test
    @DisplayName("Should handle RestTemplate exception in GET request")
    void testGetWithRestTemplateException() {
        // Arrange
        String url = "http://example.com/api/test";
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
            .thenThrow(new RuntimeException("Connection error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> restClient.get(url));
    }

    @Test
    @DisplayName("Should handle RestTemplate exception in POST request")
    void testPostWithRestTemplateException() {
        // Arrange
        String url = "http://example.com/api/test";
        String requestBody = "{\"name\":\"test\"}";
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
            .thenThrow(new RuntimeException("Connection error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> restClient.post(url, requestBody));
    }

    @Test
    @DisplayName("Should handle RestTemplate exception in DELETE request")
    void testDeleteWithRestTemplateException() {
        // Arrange
        String url = "http://example.com/api/test";
        when(restTemplate.exchange(anyString(), eq(HttpMethod.DELETE), any(HttpEntity.class), eq(String.class)))
            .thenThrow(new RuntimeException("Connection error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> restClient.delete(url));
    }

    // ==================== Integration Tests ====================

    @Test
    @DisplayName("Should handle complete HTTP workflow")
    void testCompleteHttpWorkflow() {
        // Arrange
        String url = "http://example.com/api/resource";
        String requestBody = "{\"name\":\"test\"}";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer token123");
        
        ResponseEntity<String> postResponse = ResponseEntity.status(HttpStatus.CREATED).body("Created");
        ResponseEntity<String> getResponse = ResponseEntity.ok("Success");
        ResponseEntity<String> putResponse = ResponseEntity.ok("Updated");
        ResponseEntity<String> patchResponse = ResponseEntity.ok("Patched");
        ResponseEntity<String> deleteResponse = ResponseEntity.ok("Deleted");
        
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
            .thenReturn(postResponse);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
            .thenReturn(getResponse);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(HttpEntity.class), eq(String.class)))
            .thenReturn(putResponse);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PATCH), any(HttpEntity.class), eq(String.class)))
            .thenReturn(patchResponse);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.DELETE), any(HttpEntity.class), eq(String.class)))
            .thenReturn(deleteResponse);

        // Act
        ResponseEntity<String> created = restClient.post(url, requestBody, headers);
        ResponseEntity<String> retrieved = restClient.get(url, headers);
        ResponseEntity<String> updated = restClient.put(url, requestBody, headers);
        ResponseEntity<String> patched = restClient.patch(url, requestBody, headers);
        ResponseEntity<String> deleted = restClient.delete(url, headers);

        // Assert
        assertEquals(HttpStatus.CREATED, created.getStatusCode());
        assertEquals(HttpStatus.OK, retrieved.getStatusCode());
        assertEquals(HttpStatus.OK, updated.getStatusCode());
        assertEquals(HttpStatus.OK, patched.getStatusCode());
        assertEquals(HttpStatus.OK, deleted.getStatusCode());
        
        verify(restTemplate, times(1)).exchange(eq(url), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class));
        verify(restTemplate, times(1)).exchange(eq(url), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class));
        verify(restTemplate, times(1)).exchange(eq(url), eq(HttpMethod.PUT), any(HttpEntity.class), eq(String.class));
        verify(restTemplate, times(1)).exchange(eq(url), eq(HttpMethod.PATCH), any(HttpEntity.class), eq(String.class));
        verify(restTemplate, times(1)).exchange(eq(url), eq(HttpMethod.DELETE), any(HttpEntity.class), eq(String.class));
    }
}