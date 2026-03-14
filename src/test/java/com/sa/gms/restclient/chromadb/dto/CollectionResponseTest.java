package com.sa.gms.restclient.chromadb.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CollectionResponse.
 * Tests response DTO for collection information from Chroma DB.
 */
@DisplayName("CollectionResponse Tests")
class CollectionResponseTest {

    // ==================== Constructor Tests ====================

    @Test
    @DisplayName("Should create instance with default constructor")
    void testDefaultConstructor() {
        // Act
        CollectionResponse response = new CollectionResponse();

        // Assert
        assertNotNull(response);
        assertNull(response.getId());
        assertNull(response.getName());
        assertNull(response.getTenant());
        assertNull(response.getDatabase());
        assertNull(response.getDimension());
        assertNull(response.getMetadata());
        assertNull(response.getConfigurationJson());
        assertNull(response.getSchema());
        assertNull(response.getVersion());
        assertNull(response.getLogPosition());
    }

    // ==================== Getter/Setter Tests ====================

    @Test
    @DisplayName("Should set and get id")
    void testSetAndGetId() {
        // Arrange
        CollectionResponse response = new CollectionResponse();
        String id = "collection-123";

        // Act
        response.setId(id);

        // Assert
        assertEquals(id, response.getId());
    }

    @Test
    @DisplayName("Should set and get name")
    void testSetAndGetName() {
        // Arrange
        CollectionResponse response = new CollectionResponse();
        String name = "test-collection";

        // Act
        response.setName(name);

        // Assert
        assertEquals(name, response.getName());
    }

    @Test
    @DisplayName("Should set and get tenant")
    void testSetAndGetTenant() {
        // Arrange
        CollectionResponse response = new CollectionResponse();
        String tenant = "tenant-1";

        // Act
        response.setTenant(tenant);

        // Assert
        assertEquals(tenant, response.getTenant());
    }

    @Test
    @DisplayName("Should set and get database")
    void testSetAndGetDatabase() {
        // Arrange
        CollectionResponse response = new CollectionResponse();
        String database = "database-1";

        // Act
        response.setDatabase(database);

        // Assert
        assertEquals(database, response.getDatabase());
    }

    @Test
    @DisplayName("Should set and get dimension")
    void testSetAndGetDimension() {
        // Arrange
        CollectionResponse response = new CollectionResponse();
        Long dimension = 1536L;

        // Act
        response.setDimension(dimension);

        // Assert
        assertEquals(dimension, response.getDimension());
    }

    @Test
    @DisplayName("Should set and get metadata")
    void testSetAndGetMetadata() {
        // Arrange
        CollectionResponse response = new CollectionResponse();
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("key", "value");

        // Act
        response.setMetadata(metadata);

        // Assert
        assertEquals(metadata, response.getMetadata());
    }

    @Test
    @DisplayName("Should set and get configurationJson")
    void testSetAndGetConfigurationJson() {
        // Arrange
        CollectionResponse response = new CollectionResponse();
        CollectionResponse.ConfigurationJson config = new CollectionResponse.ConfigurationJson();

        // Act
        response.setConfigurationJson(config);

        // Assert
        assertEquals(config, response.getConfigurationJson());
    }

    @Test
    @DisplayName("Should set and get schema")
    void testSetAndGetSchema() {
        // Arrange
        CollectionResponse response = new CollectionResponse();
        Object schema = new Object();

        // Act
        response.setSchema(schema);

        // Assert
        assertEquals(schema, response.getSchema());
    }

    @Test
    @DisplayName("Should set and get version")
    void testSetAndGetVersion() {
        // Arrange
        CollectionResponse response = new CollectionResponse();
        Long version = 1L;

        // Act
        response.setVersion(version);

        // Assert
        assertEquals(version, response.getVersion());
    }

    @Test
    @DisplayName("Should set and get logPosition")
    void testSetAndGetLogPosition() {
        // Arrange
        CollectionResponse response = new CollectionResponse();
        Long logPosition = 100L;

        // Act
        response.setLogPosition(logPosition);

        // Assert
        assertEquals(logPosition, response.getLogPosition());
    }

    @Test
    @DisplayName("Should set null values")
    void testSetNullValues() {
        // Arrange
        CollectionResponse response = new CollectionResponse();
        response.setId("id");
        response.setName("name");
        response.setTenant("tenant");
        response.setDatabase("database");
        response.setDimension(1536L);
        response.setMetadata(new HashMap<>());
        response.setConfigurationJson(new CollectionResponse.ConfigurationJson());
        response.setSchema(new Object());
        response.setVersion(1L);
        response.setLogPosition(100L);

        // Act
        response.setId(null);
        response.setName(null);
        response.setTenant(null);
        response.setDatabase(null);
        response.setDimension(null);
        response.setMetadata(null);
        response.setConfigurationJson(null);
        response.setSchema(null);
        response.setVersion(null);
        response.setLogPosition(null);

        // Assert
        assertNull(response.getId());
        assertNull(response.getName());
        assertNull(response.getTenant());
        assertNull(response.getDatabase());
        assertNull(response.getDimension());
        assertNull(response.getMetadata());
        assertNull(response.getConfigurationJson());
        assertNull(response.getSchema());
        assertNull(response.getVersion());
        assertNull(response.getLogPosition());
    }

    // ==================== ConfigurationJson Tests ====================

    @Test
    @DisplayName("Should create ConfigurationJson instance")
    void testConfigurationJsonConstructor() {
        // Act
        CollectionResponse.ConfigurationJson config = new CollectionResponse.ConfigurationJson();

        // Assert
        assertNotNull(config);
        assertNull(config.getEmbeddingFunction());
        assertNull(config.getHnsw());
        assertNull(config.getSpann());
    }

    @Test
    @DisplayName("Should set and get embeddingFunction")
    void testSetAndGetEmbeddingFunction() {
        // Arrange
        CollectionResponse.ConfigurationJson config = new CollectionResponse.ConfigurationJson();
        Object embeddingFunction = new Object();

        // Act
        config.setEmbeddingFunction(embeddingFunction);

        // Assert
        assertEquals(embeddingFunction, config.getEmbeddingFunction());
    }

    @Test
    @DisplayName("Should set and get hnsw")
    void testSetAndGetHnsw() {
        // Arrange
        CollectionResponse.ConfigurationJson config = new CollectionResponse.ConfigurationJson();
        Object hnsw = new Object();

        // Act
        config.setHnsw(hnsw);

        // Assert
        assertEquals(hnsw, config.getHnsw());
    }

    @Test
    @DisplayName("Should set and get spann")
    void testSetAndGetSpann() {
        // Arrange
        CollectionResponse.ConfigurationJson config = new CollectionResponse.ConfigurationJson();
        Object spann = new Object();

        // Act
        config.setSpann(spann);

        // Assert
        assertEquals(spann, config.getSpann());
    }

    // ==================== Edge Cases Tests ====================

    @Test
    @DisplayName("Should handle empty id")
    void testEmptyId() {
        // Arrange
        CollectionResponse response = new CollectionResponse();

        // Act
        response.setId("");

        // Assert
        assertEquals("", response.getId());
    }

    @Test
    @DisplayName("Should handle empty name")
    void testEmptyName() {
        // Arrange
        CollectionResponse response = new CollectionResponse();

        // Act
        response.setName("");

        // Assert
        assertEquals("", response.getName());
    }

    @Test
    @DisplayName("Should handle zero dimension")
    void testZeroDimension() {
        // Arrange
        CollectionResponse response = new CollectionResponse();

        // Act
        response.setDimension(0L);

        // Assert
        assertEquals(0L, response.getDimension());
    }

    @Test
    @DisplayName("Should handle negative dimension")
    void testNegativeDimension() {
        // Arrange
        CollectionResponse response = new CollectionResponse();

        // Act
        response.setDimension(-1L);

        // Assert
        assertEquals(-1L, response.getDimension());
    }

    @Test
    @DisplayName("Should handle large dimension")
    void testLargeDimension() {
        // Arrange
        CollectionResponse response = new CollectionResponse();

        // Act
        response.setDimension(Long.MAX_VALUE);

        // Assert
        assertEquals(Long.MAX_VALUE, response.getDimension());
    }

    @Test
    @DisplayName("Should handle empty metadata")
    void testEmptyMetadata() {
        // Arrange
        CollectionResponse response = new CollectionResponse();

        // Act
        response.setMetadata(new HashMap<>());

        // Assert
        assertTrue(response.getMetadata().isEmpty());
    }

    @Test
    @DisplayName("Should handle metadata with complex values")
    void testMetadataWithComplexValues() {
        // Arrange
        CollectionResponse response = new CollectionResponse();
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("string", "value");
        metadata.put("number", 123);
        metadata.put("boolean", true);
        metadata.put("nested", new HashMap<>());

        // Act
        response.setMetadata(metadata);

        // Assert
        assertEquals(metadata, response.getMetadata());
        assertEquals("value", response.getMetadata().get("string"));
        assertEquals(123, response.getMetadata().get("number"));
        assertEquals(true, response.getMetadata().get("boolean"));
    }

    @Test
    @DisplayName("Should handle zero version")
    void testZeroVersion() {
        // Arrange
        CollectionResponse response = new CollectionResponse();

        // Act
        response.setVersion(0L);

        // Assert
        assertEquals(0L, response.getVersion());
    }

    @Test
    @DisplayName("Should handle zero logPosition")
    void testZeroLogPosition() {
        // Arrange
        CollectionResponse response = new CollectionResponse();

        // Act
        response.setLogPosition(0L);

        // Assert
        assertEquals(0L, response.getLogPosition());
    }

    // ==================== toString Tests ====================

    @Test
    @DisplayName("Should generate correct toString")
    void testToString() {
        // Arrange
        CollectionResponse response = new CollectionResponse();
        response.setId("collection-123");
        response.setName("test-collection");
        response.setTenant("tenant-1");
        response.setDatabase("database-1");
        response.setDimension(1536L);

        // Act
        String result = response.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("collection-123"));
        assertTrue(result.contains("test-collection"));
        assertTrue(result.contains("tenant-1"));
        assertTrue(result.contains("database-1"));
        assertTrue(result.contains("1536"));
    }

    @Test
    @DisplayName("Should generate toString with null values")
    void testToStringWithNullValues() {
        // Arrange
        CollectionResponse response = new CollectionResponse();

        // Act
        String result = response.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("id='null'"));
        assertTrue(result.contains("name='null'"));
    }

    // ==================== Integration Tests ====================

    @Test
    @DisplayName("Should create complete response with all fields")
    void testCompleteResponse() {
        // Arrange
        String id = "collection-123";
        String name = "test-collection";
        String tenant = "tenant-1";
        String database = "database-1";
        Long dimension = 1536L;
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("description", "Test collection");
        CollectionResponse.ConfigurationJson config = new CollectionResponse.ConfigurationJson();
        Object schema = new Object();
        Long version = 1L;
        Long logPosition = 100L;

        // Act
        CollectionResponse response = new CollectionResponse();
        response.setId(id);
        response.setName(name);
        response.setTenant(tenant);
        response.setDatabase(database);
        response.setDimension(dimension);
        response.setMetadata(metadata);
        response.setConfigurationJson(config);
        response.setSchema(schema);
        response.setVersion(version);
        response.setLogPosition(logPosition);

        // Assert
        assertEquals(id, response.getId());
        assertEquals(name, response.getName());
        assertEquals(tenant, response.getTenant());
        assertEquals(database, response.getDatabase());
        assertEquals(dimension, response.getDimension());
        assertEquals(metadata, response.getMetadata());
        assertEquals(config, response.getConfigurationJson());
        assertEquals(schema, response.getSchema());
        assertEquals(version, response.getVersion());
        assertEquals(logPosition, response.getLogPosition());
    }

    @Test
    @DisplayName("Should create complete configurationJson")
    void testCompleteConfigurationJson() {
        // Arrange
        Object embeddingFunction = new Object();
        Object hnsw = new Object();
        Object spann = new Object();

        // Act
        CollectionResponse.ConfigurationJson config = new CollectionResponse.ConfigurationJson();
        config.setEmbeddingFunction(embeddingFunction);
        config.setHnsw(hnsw);
        config.setSpann(spann);

        // Assert
        assertEquals(embeddingFunction, config.getEmbeddingFunction());
        assertEquals(hnsw, config.getHnsw());
        assertEquals(spann, config.getSpann());
    }

    @Test
    @DisplayName("Should handle response modification")
    void testResponseModification() {
        // Arrange
        CollectionResponse response = new CollectionResponse();
        response.setId("original-id");
        response.setName("original-name");

        // Act
        response.setId("new-id");
        response.setName("new-name");
        response.setDimension(2048L);

        // Assert
        assertEquals("new-id", response.getId());
        assertEquals("new-name", response.getName());
        assertEquals(2048L, response.getDimension());
    }
}