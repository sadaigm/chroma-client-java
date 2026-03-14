package com.sa.gms.restclient.chromadb.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CreateCollectionRequest.
 * Tests request DTO for creating collections in Chroma DB.
 */
@DisplayName("CreateCollectionRequest Tests")
class CreateCollectionRequestTest {

    // ==================== Constructor Tests ====================

    @Test
    @DisplayName("Should create instance with default constructor")
    void testDefaultConstructor() {
        // Act
        CreateCollectionRequest request = new CreateCollectionRequest();

        // Assert
        assertNotNull(request);
        assertNull(request.getName());
        assertNull(request.getMetadata());
        assertNull(request.getConfiguration());
        assertNull(request.getSchema());
        assertNull(request.getGetOrCreate());
    }

    @Test
    @DisplayName("Should create instance with name constructor")
    void testNameConstructor() {
        // Arrange
        String name = "test-collection";

        // Act
        CreateCollectionRequest request = new CreateCollectionRequest(name);

        // Assert
        assertNotNull(request);
        assertEquals(name, request.getName());
        assertNull(request.getMetadata());
        assertNull(request.getConfiguration());
        assertNull(request.getSchema());
        assertNull(request.getGetOrCreate());
    }

    @Test
    @DisplayName("Should create instance with all fields constructor")
    void testAllFieldsConstructor() {
        // Arrange
        String name = "test-collection";
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("key", "value");
        Object configuration = new Object();
        Object schema = new Object();
        Boolean getOrCreate = true;

        // Act
        CreateCollectionRequest request = new CreateCollectionRequest(name, metadata, configuration, schema, getOrCreate);

        // Assert
        assertEquals(name, request.getName());
        assertEquals(metadata, request.getMetadata());
        assertEquals(configuration, request.getConfiguration());
        assertEquals(schema, request.getSchema());
        assertEquals(getOrCreate, request.getGetOrCreate());
    }

    // ==================== Getter/Setter Tests ====================

    @Test
    @DisplayName("Should set and get name")
    void testSetAndGetName() {
        // Arrange
        CreateCollectionRequest request = new CreateCollectionRequest();
        String name = "my-collection";

        // Act
        request.setName(name);

        // Assert
        assertEquals(name, request.getName());
    }

    @Test
    @DisplayName("Should set and get metadata")
    void testSetAndGetMetadata() {
        // Arrange
        CreateCollectionRequest request = new CreateCollectionRequest();
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("description", "Test collection");
        metadata.put("version", 1);

        // Act
        request.setMetadata(metadata);

        // Assert
        assertEquals(metadata, request.getMetadata());
    }

    @Test
    @DisplayName("Should set and get configuration")
    void testSetAndGetConfiguration() {
        // Arrange
        CreateCollectionRequest request = new CreateCollectionRequest();
        Object configuration = new Object();

        // Act
        request.setConfiguration(configuration);

        // Assert
        assertEquals(configuration, request.getConfiguration());
    }

    @Test
    @DisplayName("Should set and get schema")
    void testSetAndGetSchema() {
        // Arrange
        CreateCollectionRequest request = new CreateCollectionRequest();
        Object schema = new Object();

        // Act
        request.setSchema(schema);

        // Assert
        assertEquals(schema, request.getSchema());
    }

    @Test
    @DisplayName("Should set and get getOrCreate")
    void testSetAndGetGetOrCreate() {
        // Arrange
        CreateCollectionRequest request = new CreateCollectionRequest();

        // Act
        request.setGetOrCreate(true);

        // Assert
        assertTrue(request.getGetOrCreate());
    }

    @Test
    @DisplayName("Should set null values")
    void testSetNullValues() {
        // Arrange
        CreateCollectionRequest request = new CreateCollectionRequest();
        request.setName("test");
        request.setMetadata(new HashMap<>());
        request.setConfiguration(new Object());
        request.setSchema(new Object());
        request.setGetOrCreate(true);

        // Act
        request.setName(null);
        request.setMetadata(null);
        request.setConfiguration(null);
        request.setSchema(null);
        request.setGetOrCreate(null);

        // Assert
        assertNull(request.getName());
        assertNull(request.getMetadata());
        assertNull(request.getConfiguration());
        assertNull(request.getSchema());
        assertNull(request.getGetOrCreate());
    }

    // ==================== Edge Cases Tests ====================

    @Test
    @DisplayName("Should handle empty name")
    void testEmptyName() {
        // Arrange
        CreateCollectionRequest request = new CreateCollectionRequest();

        // Act
        request.setName("");

        // Assert
        assertEquals("", request.getName());
    }

    @Test
    @DisplayName("Should handle empty metadata")
    void testEmptyMetadata() {
        // Arrange
        CreateCollectionRequest request = new CreateCollectionRequest();

        // Act
        request.setMetadata(new HashMap<>());

        // Assert
        assertTrue(request.getMetadata().isEmpty());
    }

    @Test
    @DisplayName("Should handle metadata with complex values")
    void testMetadataWithComplexValues() {
        // Arrange
        CreateCollectionRequest request = new CreateCollectionRequest();
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("string", "value");
        metadata.put("number", 123);
        metadata.put("boolean", true);
        metadata.put("nested", new HashMap<>());

        // Act
        request.setMetadata(metadata);

        // Assert
        assertEquals(metadata, request.getMetadata());
        assertEquals("value", request.getMetadata().get("string"));
        assertEquals(123, request.getMetadata().get("number"));
        assertEquals(true, request.getMetadata().get("boolean"));
    }

    @Test
    @DisplayName("Should handle getOrCreate true")
    void testGetOrCreateTrue() {
        // Arrange
        CreateCollectionRequest request = new CreateCollectionRequest();

        // Act
        request.setGetOrCreate(true);

        // Assert
        assertTrue(request.getGetOrCreate());
    }

    @Test
    @DisplayName("Should handle getOrCreate false")
    void testGetOrCreateFalse() {
        // Arrange
        CreateCollectionRequest request = new CreateCollectionRequest();

        // Act
        request.setGetOrCreate(false);

        // Assert
        assertFalse(request.getGetOrCreate());
    }

    // ==================== toString Tests ====================

    @Test
    @DisplayName("Should generate correct toString")
    void testToString() {
        // Arrange
        CreateCollectionRequest request = new CreateCollectionRequest();
        request.setName("test-collection");
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("key", "value");
        request.setMetadata(metadata);
        request.setGetOrCreate(true);

        // Act
        String result = request.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("test-collection"));
        assertTrue(result.contains("metadata"));
        assertTrue(result.contains("getOrCreate=true"));
    }

    @Test
    @DisplayName("Should generate toString with null values")
    void testToStringWithNullValues() {
        // Arrange
        CreateCollectionRequest request = new CreateCollectionRequest();

        // Act
        String result = request.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("name='null'"));
        assertTrue(result.contains("metadata=null"));
    }

    // ==================== Integration Tests ====================

    @Test
    @DisplayName("Should create complete request with all fields")
    void testCompleteRequest() {
        // Arrange
        String name = "my-collection";
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("description", "A test collection");
        metadata.put("dimension", 1536);
        Object configuration = new Object();
        Object schema = new Object();
        Boolean getOrCreate = true;

        // Act
        CreateCollectionRequest request = new CreateCollectionRequest(name, metadata, configuration, schema, getOrCreate);

        // Assert
        assertEquals(name, request.getName());
        assertEquals(metadata, request.getMetadata());
        assertEquals(configuration, request.getConfiguration());
        assertEquals(schema, request.getSchema());
        assertEquals(getOrCreate, request.getGetOrCreate());
        assertEquals(2, request.getMetadata().size());
    }

    @Test
    @DisplayName("Should handle request modification")
    void testRequestModification() {
        // Arrange
        CreateCollectionRequest request = new CreateCollectionRequest("original-name");

        // Act
        request.setName("new-name");
        Map<String, Object> newMetadata = new HashMap<>();
        newMetadata.put("new-key", "new-value");
        request.setMetadata(newMetadata);
        request.setGetOrCreate(false);

        // Assert
        assertEquals("new-name", request.getName());
        assertEquals(newMetadata, request.getMetadata());
        assertFalse(request.getGetOrCreate());
    }
}