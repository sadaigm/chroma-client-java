package com.sa.gms.restclient.chromadb.context;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for TenantContextHolder.
 * Tests thread-local tenant context management.
 */
@DisplayName("TenantContextHolder Tests")
class TenantContextHolderTest {

    @BeforeEach
    void setUp() {
        // Clear any existing tenant context before each test
        TenantContextHolder.clear();
    }

    @AfterEach
    void tearDown() {
        // Clean up after each test
        TenantContextHolder.clear();
    }

    // ==================== Basic Operations Tests ====================

    @Test
    @DisplayName("Should set and get tenant ID")
    void testSetAndGetTenantId() {
        // Arrange
        Integer tenantId = 12345;

        // Act
        TenantContextHolder.setTenantId(tenantId);
        Integer retrievedId = TenantContextHolder.getTenantId();

        // Assert
        assertEquals(tenantId, retrievedId);
    }

    @Test
    @DisplayName("Should return null when tenant ID is not set")
    void testGetTenantIdWhenNotSet() {
        // Act
        Integer tenantId = TenantContextHolder.getTenantId();

        // Assert
        assertNull(tenantId);
    }

    @Test
    @DisplayName("Should clear tenant ID")
    void testClearTenantId() {
        // Arrange
        TenantContextHolder.setTenantId(12345);
        assertNotNull(TenantContextHolder.getTenantId());

        // Act
        TenantContextHolder.clear();

        // Assert
        assertNull(TenantContextHolder.getTenantId());
    }

    @Test
    @DisplayName("Should allow setting null tenant ID")
    void testSetNullTenantId() {
        // Act
        TenantContextHolder.setTenantId(null);
        Integer retrievedId = TenantContextHolder.getTenantId();

        // Assert
        assertNull(retrievedId);
    }

    @Test
    @DisplayName("Should allow setting zero as tenant ID")
    void testSetZeroTenantId() {
        // Arrange
        Integer tenantId = 0;

        // Act
        TenantContextHolder.setTenantId(tenantId);
        Integer retrievedId = TenantContextHolder.getTenantId();

        // Assert
        assertEquals(tenantId, retrievedId);
    }

    @Test
    @DisplayName("Should allow setting negative tenant ID")
    void testSetNegativeTenantId() {
        // Arrange
        Integer tenantId = -1;

        // Act
        TenantContextHolder.setTenantId(tenantId);
        Integer retrievedId = TenantContextHolder.getTenantId();

        // Assert
        assertEquals(tenantId, retrievedId);
    }

    @Test
    @DisplayName("Should allow setting large tenant ID")
    void testSetLargeTenantId() {
        // Arrange
        Integer tenantId = Integer.MAX_VALUE;

        // Act
        TenantContextHolder.setTenantId(tenantId);
        Integer retrievedId = TenantContextHolder.getTenantId();

        // Assert
        assertEquals(tenantId, retrievedId);
    }

    // ==================== Multiple Set Operations Tests ====================

    @Test
    @DisplayName("Should overwrite existing tenant ID")
    void testOverwriteTenantId() {
        // Arrange
        TenantContextHolder.setTenantId(12345);
        assertEquals(12345, TenantContextHolder.getTenantId());

        // Act
        TenantContextHolder.setTenantId(67890);
        Integer retrievedId = TenantContextHolder.getTenantId();

        // Assert
        assertEquals(67890, retrievedId);
    }

    @Test
    @DisplayName("Should handle multiple set and clear operations")
    void testMultipleSetAndClearOperations() {
        // Act & Assert
        TenantContextHolder.setTenantId(1);
        assertEquals(1, TenantContextHolder.getTenantId());
        
        TenantContextHolder.clear();
        assertNull(TenantContextHolder.getTenantId());
        
        TenantContextHolder.setTenantId(2);
        assertEquals(2, TenantContextHolder.getTenantId());
        
        TenantContextHolder.setTenantId(3);
        assertEquals(3, TenantContextHolder.getTenantId());
        
        TenantContextHolder.clear();
        assertNull(TenantContextHolder.getTenantId());
    }

    // ==================== Thread Safety Tests ====================

    @Test
    @DisplayName("Should maintain separate tenant context in different threads")
    void testThreadIsolation() throws InterruptedException {
        // Arrange
        Integer mainThreadTenantId = 100;
        Integer childThreadTenantId = 200;
        
        TenantContextHolder.setTenantId(mainThreadTenantId);
        assertEquals(mainThreadTenantId, TenantContextHolder.getTenantId());

        // Act
        Thread childThread = new Thread(() -> {
            // Child thread should not see parent's tenant ID
            assertNull(TenantContextHolder.getTenantId());
            
            // Set tenant ID in child thread
            TenantContextHolder.setTenantId(childThreadTenantId);
            assertEquals(childThreadTenantId, TenantContextHolder.getTenantId());
            
            // Clear in child thread
            TenantContextHolder.clear();
            assertNull(TenantContextHolder.getTenantId());
        });

        childThread.start();
        childThread.join();

        // Assert - Main thread should still have its tenant ID
        assertEquals(mainThreadTenantId, TenantContextHolder.getTenantId());
    }

    @Test
    @DisplayName("Should handle concurrent access from multiple threads")
    void testConcurrentAccess() throws InterruptedException {
        // Arrange
        int threadCount = 10;
        Thread[] threads = new Thread[threadCount];

        // Act - Create multiple threads that set different tenant IDs
        for (int i = 0; i < threadCount; i++) {
            final int tenantId = i;
            threads[i] = new Thread(() -> {
                TenantContextHolder.setTenantId(tenantId);
                try {
                    Thread.sleep(10); // Simulate some work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                assertEquals(tenantId, TenantContextHolder.getTenantId());
                TenantContextHolder.clear();
            });
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        // Assert - Main thread should still have no tenant ID
        assertNull(TenantContextHolder.getTenantId());
    }

    // ==================== Edge Cases Tests ====================

    @Test
    @DisplayName("Should handle multiple clear operations safely")
    void testMultipleClearOperations() {
        // Arrange
        TenantContextHolder.setTenantId(12345);

        // Act
        TenantContextHolder.clear();
        TenantContextHolder.clear();
        TenantContextHolder.clear();

        // Assert
        assertNull(TenantContextHolder.getTenantId());
    }

    @Test
    @DisplayName("Should handle clear operation when tenant ID is not set")
    void testClearWhenNotSet() {
        // Act - Should not throw exception
        TenantContextHolder.clear();

        // Assert
        assertNull(TenantContextHolder.getTenantId());
    }

    @Test
    @DisplayName("Should handle setting tenant ID after clear")
    void testSetAfterClear() {
        // Arrange
        TenantContextHolder.setTenantId(12345);
        TenantContextHolder.clear();
        assertNull(TenantContextHolder.getTenantId());

        // Act
        TenantContextHolder.setTenantId(67890);
        Integer retrievedId = TenantContextHolder.getTenantId();

        // Assert
        assertEquals(67890, retrievedId);
    }

    // ==================== Integration Tests ====================

    @Test
    @DisplayName("Should simulate complete request lifecycle")
    void testCompleteRequestLifecycle() {
        // Simulate request start
        assertNull(TenantContextHolder.getTenantId());
        
        // Set tenant ID at request start
        TenantContextHolder.setTenantId(12345);
        assertEquals(12345, TenantContextHolder.getTenantId());
        
        // Simulate request processing - tenant ID should remain
        assertEquals(12345, TenantContextHolder.getTenantId());
        
        // Clear tenant ID at request end
        TenantContextHolder.clear();
        assertNull(TenantContextHolder.getTenantId());
    }

    @Test
    @DisplayName("Should handle rapid tenant ID changes")
    void testRapidTenantIdChanges() {
        // Act & Assert
        for (int i = 0; i < 100; i++) {
            TenantContextHolder.setTenantId(i);
            assertEquals(i, TenantContextHolder.getTenantId());
        }
        
        TenantContextHolder.clear();
        assertNull(TenantContextHolder.getTenantId());
    }
}