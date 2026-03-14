package com.sa.gms.restclient.chromadb.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for AddRecordsRequest.
 * Tests request DTO for adding records to Chroma DB collections.
 */
@DisplayName("AddRecordsRequest Tests")
class AddRecordsRequestTest {

    // ==================== Constructor Tests ====================

    @Test
    @DisplayName("Should create instance with default constructor")
    void testDefaultConstructor() {
        // Act
        AddRecordsRequest request = new AddRecordsRequest();

        // Assert
        assertNotNull(request);
        assertNull(request.getDocuments());
        assertNull(request.getEmbeddings());
        assertNull(request.getIds());
        assertNull(request.getMetadatas());
        assertNull(request.getUris());
    }

    @Test
    @DisplayName("Should create instance with IDs constructor")
    void testIdsConstructor() {
        // Arrange
        List<String> ids = Arrays.asList("id1", "id2", "id3");

        // Act
        AddRecordsRequest request = new AddRecordsRequest(ids);

        // Assert
        assertNotNull(request);
        assertEquals(ids, request.getIds());
        assertNull(request.getDocuments());
        assertNull(request.getEmbeddings());
        assertNull(request.getMetadatas());
        assertNull(request.getUris());
    }

    // ==================== Getter/Setter Tests ====================

    @Test
    @DisplayName("Should set and get documents")
    void testSetAndGetDocuments() {
        // Arrange
        AddRecordsRequest request = new AddRecordsRequest();
        List<String> documents = Arrays.asList("doc1", "doc2", "doc3");

        // Act
        request.setDocuments(documents);

        // Assert
        assertEquals(documents, request.getDocuments());
    }

    @Test
    @DisplayName("Should set and get embeddings")
    void testSetAndGetEmbeddings() {
        // Arrange
        AddRecordsRequest request = new AddRecordsRequest();
        List<List<Float>> embeddings = Arrays.asList(
            Arrays.asList(0.1f, 0.2f, 0.3f),
            Arrays.asList(0.4f, 0.5f, 0.6f)
        );

        // Act
        request.setEmbeddings(embeddings);

        // Assert
        assertEquals(embeddings, request.getEmbeddings());
    }

    @Test
    @DisplayName("Should set and get IDs")
    void testSetAndGetIds() {
        // Arrange
        AddRecordsRequest request = new AddRecordsRequest();
        List<String> ids = Arrays.asList("id1", "id2", "id3");

        // Act
        request.setIds(ids);

        // Assert
        assertEquals(ids, request.getIds());
    }

    @Test
    @DisplayName("Should set and get metadatas")
    void testSetAndGetMetadatas() {
        // Arrange
        AddRecordsRequest request = new AddRecordsRequest();
        Map<String, Object> metadata1 = new HashMap<>();
        metadata1.put("key1", "value1");
        Map<String, Object> metadata2 = new HashMap<>();
        metadata2.put("key2", "value2");
        List<Map<String, Object>> metadatas = Arrays.asList(metadata1, metadata2);

        // Act
        request.setMetadatas(metadatas);

        // Assert
        assertEquals(metadatas, request.getMetadatas());
    }

    @Test
    @DisplayName("Should set and get URIs")
    void testSetAndGetUris() {
        // Arrange
        AddRecordsRequest request = new AddRecordsRequest();
        List<String> uris = Arrays.asList("uri1", "uri2", "uri3");

        // Act
        request.setUris(uris);

        // Assert
        assertEquals(uris, request.getUris());
    }

    @Test
    @DisplayName("Should set null values")
    void testSetNullValues() {
        // Arrange
        AddRecordsRequest request = new AddRecordsRequest();
        request.setDocuments(Arrays.asList("doc1"));
        request.setEmbeddings(Arrays.asList(Arrays.asList(0.1f)));
        request.setIds(Arrays.asList("id1"));
        request.setMetadatas(Arrays.asList(new HashMap<>()));
        request.setUris(Arrays.asList("uri1"));

        // Act
        request.setDocuments(null);
        request.setEmbeddings(null);
        request.setIds(null);
        request.setMetadatas(null);
        request.setUris(null);

        // Assert
        assertNull(request.getDocuments());
        assertNull(request.getEmbeddings());
        assertNull(request.getIds());
        assertNull(request.getMetadatas());
        assertNull(request.getUris());
    }

    @Test
    @DisplayName("Should set empty lists")
    void testSetEmptyLists() {
        // Arrange
        AddRecordsRequest request = new AddRecordsRequest();

        // Act
        request.setDocuments(Arrays.asList());
        request.setEmbeddings(Arrays.asList());
        request.setIds(Arrays.asList());
        request.setMetadatas(Arrays.asList());
        request.setUris(Arrays.asList());

        // Assert
        assertTrue(request.getDocuments().isEmpty());
        assertTrue(request.getEmbeddings().isEmpty());
        assertTrue(request.getIds().isEmpty());
        assertTrue(request.getMetadatas().isEmpty());
        assertTrue(request.getUris().isEmpty());
    }

    // ==================== Builder Pattern Tests ====================

    @Test
    @DisplayName("Should build request using builder with all fields")
    void testBuilderWithAllFields() {
        // Arrange
        List<String> documents = Arrays.asList("doc1", "doc2");
        List<List<Float>> embeddings = Arrays.asList(
            Arrays.asList(0.1f, 0.2f),
            Arrays.asList(0.3f, 0.4f)
        );
        List<String> ids = Arrays.asList("id1", "id2");
        Map<String, Object> metadata1 = new HashMap<>();
        metadata1.put("key", "value");
        List<Map<String, Object>> metadatas = Arrays.asList(metadata1);
        List<String> uris = Arrays.asList("uri1", "uri2");

        // Act
        AddRecordsRequest request = AddRecordsRequest.builder()
            .documents(documents)
            .embeddings(embeddings)
            .ids(ids)
            .metadatas(metadatas)
            .uris(uris)
            .build();

        // Assert
        assertEquals(documents, request.getDocuments());
        assertEquals(embeddings, request.getEmbeddings());
        assertEquals(ids, request.getIds());
        assertEquals(metadatas, request.getMetadatas());
        assertEquals(uris, request.getUris());
    }

    @Test
    @DisplayName("Should build request using builder with only IDs")
    void testBuilderWithOnlyIds() {
        // Arrange
        List<String> ids = Arrays.asList("id1", "id2");

        // Act
        AddRecordsRequest request = AddRecordsRequest.builder()
            .ids(ids)
            .build();

        // Assert
        assertEquals(ids, request.getIds());
        assertNull(request.getDocuments());
        assertNull(request.getEmbeddings());
        assertNull(request.getMetadatas());
        assertNull(request.getUris());
    }

    @Test
    @DisplayName("Should build request using builder with documents and IDs")
    void testBuilderWithDocumentsAndIds() {
        // Arrange
        List<String> documents = Arrays.asList("doc1", "doc2");
        List<String> ids = Arrays.asList("id1", "id2");

        // Act
        AddRecordsRequest request = AddRecordsRequest.builder()
            .documents(documents)
            .ids(ids)
            .build();

        // Assert
        assertEquals(documents, request.getDocuments());
        assertEquals(ids, request.getIds());
        assertNull(request.getEmbeddings());
        assertNull(request.getMetadatas());
        assertNull(request.getUris());
    }

    @Test
    @DisplayName("Should build request using builder with embeddings and IDs")
    void testBuilderWithEmbeddingsAndIds() {
        // Arrange
        List<List<Float>> embeddings = Arrays.asList(
            Arrays.asList(0.1f, 0.2f),
            Arrays.asList(0.3f, 0.4f)
        );
        List<String> ids = Arrays.asList("id1", "id2");

        // Act
        AddRecordsRequest request = AddRecordsRequest.builder()
            .embeddings(embeddings)
            .ids(ids)
            .build();

        // Assert
        assertEquals(embeddings, request.getEmbeddings());
        assertEquals(ids, request.getIds());
        assertNull(request.getDocuments());
        assertNull(request.getMetadatas());
        assertNull(request.getUris());
    }

    @Test
    @DisplayName("Should build request using builder with metadatas and IDs")
    void testBuilderWithMetadatasAndIds() {
        // Arrange
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("key", "value");
        List<Map<String, Object>> metadatas = Arrays.asList(metadata);
        List<String> ids = Arrays.asList("id1");

        // Act
        AddRecordsRequest request = AddRecordsRequest.builder()
            .metadatas(metadatas)
            .ids(ids)
            .build();

        // Assert
        assertEquals(metadatas, request.getMetadatas());
        assertEquals(ids, request.getIds());
        assertNull(request.getDocuments());
        assertNull(request.getEmbeddings());
        assertNull(request.getUris());
    }

    @Test
    @DisplayName("Should build request using builder with URIs and IDs")
    void testBuilderWithUrisAndIds() {
        // Arrange
        List<String> uris = Arrays.asList("uri1", "uri2");
        List<String> ids = Arrays.asList("id1", "id2");

        // Act
        AddRecordsRequest request = AddRecordsRequest.builder()
            .uris(uris)
            .ids(ids)
            .build();

        // Assert
        assertEquals(uris, request.getUris());
        assertEquals(ids, request.getIds());
        assertNull(request.getDocuments());
        assertNull(request.getEmbeddings());
        assertNull(request.getMetadatas());
    }

    @Test
    @DisplayName("Should build request using builder with null values")
    void testBuilderWithNullValues() {
        // Act
        AddRecordsRequest request = AddRecordsRequest.builder()
            .documents(null)
            .embeddings(null)
            .ids(null)
            .metadatas(null)
            .uris(null)
            .build();

        // Assert
        assertNull(request.getDocuments());
        assertNull(request.getEmbeddings());
        assertNull(request.getIds());
        assertNull(request.getMetadatas());
        assertNull(request.getUris());
    }

    @Test
    @DisplayName("Should support method chaining in builder")
    void testBuilderMethodChaining() {
        // Arrange
        List<String> ids = Arrays.asList("id1", "id2");

        // Act
        AddRecordsRequest.Builder builder = AddRecordsRequest.builder();
        AddRecordsRequest.Builder chained = builder.ids(ids).documents(Arrays.asList("doc1"));

        // Assert
        assertSame(builder, chained);
    }

    // ==================== Edge Cases Tests ====================

    @Test
    @DisplayName("Should handle empty embeddings list")
    void testEmptyEmbeddingsList() {
        // Arrange
        AddRecordsRequest request = new AddRecordsRequest();

        // Act
        request.setEmbeddings(Arrays.asList());

        // Assert
        assertTrue(request.getEmbeddings().isEmpty());
    }

    @Test
    @DisplayName("Should handle embeddings with empty inner lists")
    void testEmbeddingsWithEmptyInnerLists() {
        // Arrange
        AddRecordsRequest request = new AddRecordsRequest();
        List<List<Float>> embeddings = Arrays.asList(Arrays.asList(), Arrays.asList());

        // Act
        request.setEmbeddings(embeddings);

        // Assert
        assertEquals(2, request.getEmbeddings().size());
        assertTrue(request.getEmbeddings().get(0).isEmpty());
        assertTrue(request.getEmbeddings().get(1).isEmpty());
    }

    @Test
    @DisplayName("Should handle metadata with complex values")
    void testMetadataWithComplexValues() {
        // Arrange
        AddRecordsRequest request = new AddRecordsRequest();
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("string", "value");
        metadata.put("number", 123);
        metadata.put("boolean", true);
        metadata.put("list", Arrays.asList("a", "b", "c"));
        List<Map<String, Object>> metadatas = Arrays.asList(metadata);

        // Act
        request.setMetadatas(metadatas);

        // Assert
        assertEquals(metadatas, request.getMetadatas());
        assertEquals("value", request.getMetadatas().get(0).get("string"));
        assertEquals(123, request.getMetadatas().get(0).get("number"));
        assertEquals(true, request.getMetadatas().get(0).get("boolean"));
        assertEquals(Arrays.asList("a", "b", "c"), request.getMetadatas().get(0).get("list"));
    }

    // ==================== Integration Tests ====================

    @Test
    @DisplayName("Should create complete request with all fields")
    void testCompleteRequest() {
        // Arrange
        List<String> documents = Arrays.asList("Document 1", "Document 2");
        List<List<Float>> embeddings = Arrays.asList(
            Arrays.asList(0.1f, 0.2f, 0.3f),
            Arrays.asList(0.4f, 0.5f, 0.6f)
        );
        List<String> ids = Arrays.asList("doc-001", "doc-002");
        Map<String, Object> metadata1 = new HashMap<>();
        metadata1.put("author", "John");
        metadata1.put("year", 2023);
        Map<String, Object> metadata2 = new HashMap<>();
        metadata2.put("author", "Jane");
        metadata2.put("year", 2024);
        List<Map<String, Object>> metadatas = Arrays.asList(metadata1, metadata2);
        List<String> uris = Arrays.asList("http://example.com/doc1", "http://example.com/doc2");

        // Act
        AddRecordsRequest request = AddRecordsRequest.builder()
            .documents(documents)
            .embeddings(embeddings)
            .ids(ids)
            .metadatas(metadatas)
            .uris(uris)
            .build();

        // Assert
        assertEquals(2, request.getDocuments().size());
        assertEquals(2, request.getEmbeddings().size());
        assertEquals(2, request.getIds().size());
        assertEquals(2, request.getMetadatas().size());
        assertEquals(2, request.getUris().size());
        assertEquals("Document 1", request.getDocuments().get(0));
        assertEquals("doc-001", request.getIds().get(0));
        assertEquals("John", request.getMetadatas().get(0).get("author"));
    }
}