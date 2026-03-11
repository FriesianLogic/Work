package com.tmobile.widgetarbitration

import org.junit.Assert.*
import org.junit.Test

class TestWidgetArbitration {

    @Test
    fun testRefreshDecision_highEngagement_shouldRefresh() {
        val metadata = mapOf("type" to "news", "lastRefresh" to System.currentTimeMillis() - 60000)
        val result = WidgetArbitrationManager.shouldRefreshWidget("101", metadata, "com.tmobile.test")
        assertTrue(result.shouldRefresh)
    }

    @Test
    fun testRefreshDecision_lowEngagement_shouldSuppress() {
        val metadata = mapOf("type" to "ads", "lastRefresh" to System.currentTimeMillis())
        val result = WidgetArbitrationManager.shouldRefreshWidget("102", metadata, "com.unknown.app")
        assertFalse(result.shouldRefresh)
    }
}