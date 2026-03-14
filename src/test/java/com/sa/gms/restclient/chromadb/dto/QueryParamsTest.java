package com.sa.gms.restclient.chromadb.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for QueryParams.
 * Tests query parameter building utility for Chroma DB API calls.
 */
@DisplayName("QueryParams Tests")
class QueryParamsTest {

    // ==================== Basic Operations Tests ====================

    @Test
    @DisplayName("Should create empty QueryParams instance")
    void testEmptyQueryParams() {
        // Act
        QueryParams qp = new QueryParams();

        // Assert
        assertNotNull(qp);
        assertNull(qp.build());
    }

    @Test
    @DisplayName("Should add string parameter")
    void testAddStringParameter() {
        // Act
        QueryParams qp = new QueryParams()
            .add("key1", "value1");

        // Assert
        assertEquals("key1=value1", qp.build());
    }

    @Test
    @DisplayName("Should add integer parameter")
    void testAddIntegerParameter() {
        // Act
        QueryParams qp = new QueryParams()
            .add("limit", 10);

        // Assert
        assertEquals("limit=10", qp.build());
    }

    @Test
    @DisplayName("Should add boolean parameter")
    void testAddBooleanParameter() {
        // Act
        QueryParams qp = new QueryParams()
            .add("enabled", true);

        // Assert
        assertEquals("enabled=true", qp.build());
    }

    @Test
    @DisplayName("Should add multiple parameters")
    void testAddMultipleParameters() {
        // Act
        QueryParams qp = new QueryParams()
            .add("limit", 10)
            .add("offset", 0)
            .add("filter", "active");

        // Assert
        String result = qp.build();
        assertTrue(result.contains("limit=10"));
        assertTrue(result.contains("offset=0"));
        assertTrue(result.contains("filter=active"));
        assertTrue(result.contains("&"));
    }

    @Test
    @DisplayName("Should support method chaining")
    void testMethodChaining() {
        // Act
        QueryParams qp = new QueryParams();
        QueryParams chained = qp.add("key1", "value1").add("key2", "value2");

        // Assert
        assertSame(qp, chained);
        assertEquals("key1=value1&key2=value2", qp.build());
    }

    @Test
    @DisplayName("Should handle null string value")
    void testAddNullStringValue() {
        // Act
        QueryParams qp = new QueryParams()
            .add("key1", (String) null);

        // Assert
        String result = qp.build();
        assertNotNull(result);
        assertTrue(result.contains("key1=null"));
    }

    // ==================== Database Query Parameters Tests ====================

    @Test
    @DisplayName("Should create query params for list databases with limit")
    void testForListDatabasesWithLimit() {
        // Act
        QueryParams qp = QueryParams.forListDatabases(10, null);

        // Assert
        assertEquals("limit=10", qp.build());
    }

    @Test
    @DisplayName("Should create query params for list databases with offset")
    void testForListDatabasesWithOffset() {
        // Act
        QueryParams qp = QueryParams.forListDatabases(null, 5);

        // Assert
        assertEquals("offset=5", qp.build());
    }

    @Test
    @DisplayName("Should create query params for list databases with limit and offset")
    void testForListDatabasesWithLimitAndOffset() {
        // Act
        QueryParams qp = QueryParams.forListDatabases(10, 5);

        // Assert
        String result = qp.build();
        assertTrue(result.contains("limit=10"));
        assertTrue(result.contains("offset=5"));
    }

    @Test
    @DisplayName("Should create empty query params for list databases")
    void testForListDatabasesEmpty() {
        // Act
        QueryParams qp = QueryParams.forListDatabases(null, null);

        // Assert
        assertNull(qp.build());
    }

    // ==================== Collection Query Parameters Tests ====================

    @Test
    @DisplayName("Should create query params for list collections with limit")
    void testForListCollectionsWithLimit() {
        // Act
        QueryParams qp = QueryParams.forListCollections(20, null);

        // Assert
        assertEquals("limit=20", qp.build());
    }

    @Test
    @DisplayName("Should create query params for list collections with offset")
    void testForListCollectionsWithOffset() {
        // Act
        QueryParams qp = QueryParams.forListCollections(null, 10);

        // Assert
        assertEquals("offset=10", qp.build());
    }

    @Test
    @DisplayName("Should create query params for list collections with limit and offset")
    void testForListCollectionsWithLimitAndOffset() {
        // Act
        QueryParams qp = QueryParams.forListCollections(20, 10);

        // Assert
        String result = qp.build();
        assertTrue(result.contains("limit=20"));
        assertTrue(result.contains("offset=10"));
    }

    @Test
    @DisplayName("Should create empty query params for list collections")
    void testForListCollectionsEmpty() {
        // Act
        QueryParams qp = QueryParams.forListCollections(null, null);

        // Assert
        assertNull(qp.build());
    }

    @Test
    @DisplayName("Should create query params for get collections with tenant")
    void testForGetCollectionsWithTenant() {
        // Act
        QueryParams qp = QueryParams.forGetCollections("tenant1", null, null, null);

        // Assert
        assertEquals("tenant=tenant1", qp.build());
    }

    @Test
    @DisplayName("Should create query params for get collections with database")
    void testForGetCollectionsWithDatabase() {
        // Act
        QueryParams qp = QueryParams.forGetCollections(null, "db1", null, null);

        // Assert
        assertEquals("database=db1", qp.build());
    }

    @Test
    @DisplayName("Should create query params for get collections with all parameters")
    void testForGetCollectionsWithAllParameters() {
        // Act
        QueryParams qp = QueryParams.forGetCollections("tenant1", "db1", 10, 5);

        // Assert
        String result = qp.build();
        assertTrue(result.contains("tenant=tenant1"));
        assertTrue(result.contains("database=db1"));
        assertTrue(result.contains("limit=10"));
        assertTrue(result.contains("offset=5"));
    }

    @Test
    @DisplayName("Should create empty query params for get collections")
    void testForGetCollectionsEmpty() {
        // Act
        QueryParams qp = QueryParams.forGetCollections(null, null, null, null);

        // Assert
        assertNull(qp.build());
    }

    // ==================== Document Query Parameters Tests ====================

    @Test
    @DisplayName("Should create query params for query documents with nResults")
    void testForQueryDocumentsWithNResults() {
        // Act
        QueryParams qp = QueryParams.forQueryDocuments(5);

        // Assert
        assertEquals("n_results=5", qp.build());
    }

    @Test
    @DisplayName("Should create empty query params for query documents")
    void testForQueryDocumentsEmpty() {
        // Act
        QueryParams qp = QueryParams.forQueryDocuments(null);

        // Assert
        assertNull(qp.build());
    }

    @Test
    @DisplayName("Should create query params for get documents with ids")
    void testForGetDocumentsWithIds() {
        // Act
        QueryParams qp = QueryParams.forGetDocuments("id1,id2,id3", null, null);

        // Assert
        assertEquals("ids=id1,id2,id3", qp.build());
    }

    @Test
    @DisplayName("Should create query params for get documents with limit")
    void testForGetDocumentsWithLimit() {
        // Act
        QueryParams qp = QueryParams.forGetDocuments(null, 10, null);

        // Assert
        assertEquals("limit=10", qp.build());
    }

    @Test
    @DisplayName("Should create query params for get documents with offset")
    void testForGetDocumentsWithOffset() {
        // Act
        QueryParams qp = QueryParams.forGetDocuments(null, null, 5);

        // Assert
        assertEquals("offset=5", qp.build());
    }

    @Test
    @DisplayName("Should create query params for get documents with all parameters")
    void testForGetDocumentsWithAllParameters() {
        // Act
        QueryParams qp = QueryParams.forGetDocuments("id1,id2", 10, 5);

        // Assert
        String result = qp.build();
        assertTrue(result.contains("ids=id1,id2"));
        assertTrue(result.contains("limit=10"));
        assertTrue(result.contains("offset=5"));
    }

    @Test
    @DisplayName("Should create empty query params for get documents")
    void testForGetDocumentsEmpty() {
        // Act
        QueryParams qp = QueryParams.forGetDocuments(null, null, null);

        // Assert
        assertNull(qp.build());
    }

    @Test
    @DisplayName("Should create query params for count records with read level")
    void testForCountRecordsWithReadLevel() {
        // Act
        QueryParams qp = QueryParams.forCountRecords("index_and_wal");

        // Assert
        assertEquals("read_level=index_and_wal", qp.build());
    }

    @Test
    @DisplayName("Should create empty query params for count records")
    void testForCountRecordsEmpty() {
        // Act
        QueryParams qp = QueryParams.forCountRecords(null);

        // Assert
        assertNull(qp.build());
    }

    @Test
    @DisplayName("Should create query params for query collection with limit")
    void testForQueryCollectionWithLimit() {
        // Act
        QueryParams qp = QueryParams.forQueryCollection(15, null);

        // Assert
        assertEquals("limit=15", qp.build());
    }

    @Test
    @DisplayName("Should create query params for query collection with offset")
    void testForQueryCollectionWithOffset() {
        // Act
        QueryParams qp = QueryParams.forQueryCollection(null, 3);

        // Assert
        assertEquals("offset=3", qp.build());
    }

    @Test
    @DisplayName("Should create query params for query collection with limit and offset")
    void testForQueryCollectionWithLimitAndOffset() {
        // Act
        QueryParams qp = QueryParams.forQueryCollection(15, 3);

        // Assert
        String result = qp.build();
        assertTrue(result.contains("limit=15"));
        assertTrue(result.contains("offset=3"));
    }

    @Test
    @DisplayName("Should create empty query params for query collection")
    void testForQueryCollectionEmpty() {
        // Act
        QueryParams qp = QueryParams.forQueryCollection(null, null);

        // Assert
        assertNull(qp.build());
    }

    // ==================== Edge Cases Tests ====================

    @Test
    @DisplayName("Should handle zero values")
    void testZeroValues() {
        // Act
        QueryParams qp = new QueryParams()
            .add("limit", 0)
            .add("offset", 0);

        // Assert
        String result = qp.build();
        assertTrue(result.contains("limit=0"));
        assertTrue(result.contains("offset=0"));
    }

    @Test
    @DisplayName("Should handle negative values")
    void testNegativeValues() {
        // Act
        QueryParams qp = new QueryParams()
            .add("limit", -1)
            .add("offset", -5);

        // Assert
        String result = qp.build();
        assertTrue(result.contains("limit=-1"));
        assertTrue(result.contains("offset=-5"));
    }

    @Test
    @DisplayName("Should handle large values")
    void testLargeValues() {
        // Act
        QueryParams qp = new QueryParams()
            .add("limit", Integer.MAX_VALUE)
            .add("offset", Integer.MIN_VALUE);

        // Assert
        String result = qp.build();
        assertTrue(result.contains("limit=" + Integer.MAX_VALUE));
        assertTrue(result.contains("offset=" + Integer.MIN_VALUE));
    }

    @Test
    @DisplayName("Should handle special characters in string values")
    void testSpecialCharactersInStringValues() {
        // Act
        QueryParams qp = new QueryParams()
            .add("filter", "value with spaces")
            .add("special", "a&b=c");

        // Assert
        String result = qp.build();
        assertTrue(result.contains("filter=value with spaces"));
        assertTrue(result.contains("special=a&b=c"));
    }

    @Test
    @DisplayName("Should handle empty string values")
    void testEmptyStringValues() {
        // Act
        QueryParams qp = new QueryParams()
            .add("key1", "")
            .add("key2", "value");

        // Assert
        String result = qp.build();
        assertTrue(result.contains("key1="));
        assertTrue(result.contains("key2=value"));
    }

    @Test
    @DisplayName("Should handle boolean false value")
    void testBooleanFalseValue() {
        // Act
        QueryParams qp = new QueryParams()
            .add("enabled", false);

        // Assert
        assertEquals("enabled=false", qp.build());
    }

    // ==================== Integration Tests ====================

    @Test
    @DisplayName("Should build complex query string")
    void testComplexQueryString() {
        // Act
        QueryParams qp = new QueryParams()
            .add("tenant", "tenant1")
            .add("database", "db1")
            .add("limit", 10)
            .add("offset", 0)
            .add("filter", "active")
            .add("enabled", true);

        // Assert
        String result = qp.build();
        assertTrue(result.contains("tenant=tenant1"));
        assertTrue(result.contains("database=db1"));
        assertTrue(result.contains("limit=10"));
        assertTrue(result.contains("offset=0"));
        assertTrue(result.contains("filter=active"));
        assertTrue(result.contains("enabled=true"));
        assertEquals(5, result.chars().filter(ch -> ch == '&').count());
    }

    @Test
    @DisplayName("Should handle multiple build calls")
    void testMultipleBuildCalls() {
        // Arrange
        QueryParams qp = new QueryParams()
            .add("key1", "value1")
            .add("key2", "value2");

        // Act
        String result1 = qp.build();
        String result2 = qp.build();
        String result3 = qp.build();

        // Assert
        assertEquals(result1, result2);
        assertEquals(result2, result3);
        assertEquals("key1=value1&key2=value2", result1);
    }

    @Test
    @DisplayName("Should create query params for different API endpoints")
    void testDifferentApiEndpoints() {
        // Act
        QueryParams dbParams = QueryParams.forListDatabases(10, 0);
        QueryParams collectionParams = QueryParams.forListCollections(20, 0);
        QueryParams documentParams = QueryParams.forQueryDocuments(5);
        QueryParams countParams = QueryParams.forCountRecords("index_only");

        // Assert - HashMap doesn't maintain order, so check both parameters are present
        String dbResult = dbParams.build();
        assertTrue(dbResult.contains("limit=10"));
        assertTrue(dbResult.contains("offset=0"));
        
        String collectionResult = collectionParams.build();
        assertTrue(collectionResult.contains("limit=20"));
        assertTrue(collectionResult.contains("offset=0"));
        
        assertEquals("n_results=5", documentParams.build());
        assertEquals("read_level=index_only", countParams.build());
    }
}