package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PerformanceTesterTest {
    
    private final PerformanceTester performanceTester = new PerformanceTester();
    
    @Test
    @Timeout(2)
    @DisplayName("Should complete task within 2 seconds")
    void testPerformTaskWithinTimeout() {
        performanceTester.performTask(500);
        assertTrue(true);
    }
    
    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @DisplayName("Should complete heavy compute within 1 second")
    void testComputeHeavyTaskWithinTimeout() {
        long result = performanceTester.computeHeavyTask(10000);
        assertTrue(result >= 0);
    }
    
    @Test
    @Timeout(value = 3, unit = TimeUnit.SECONDS)
    @DisplayName("Should process data within 3 seconds")
    void testProcessDataWithinTimeout() {
        performanceTester.processData(500);
        assertTrue(true);
    }
    
    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @DisplayName("Should perform small task within 500ms")
    void testSmallTaskWithinTimeout() {
        performanceTester.performTask(200);
        assertTrue(true);
    }
    
    @Test
    @DisplayName("Should use assertTimeout for performance testing")
    void testWithAssertTimeout() {
        assertTimeout(java.time.Duration.ofSeconds(2), 
            () -> performanceTester.performTask(500)
        );
        
        assertTimeout(java.time.Duration.ofSeconds(1), 
            () -> {
                long result = performanceTester.computeHeavyTask(10000);
                assertTrue(result >= 0);
            }
        );
    }
    
    @Test
    @DisplayName("Should handle timeout gracefully with assertTimeout")
    void testTimeoutGracefully() {
        // This won't timeout because it's within limit
        assertTimeout(java.time.Duration.ofSeconds(2), 
            () -> performanceTester.performTask(1000)
        );
    }
}