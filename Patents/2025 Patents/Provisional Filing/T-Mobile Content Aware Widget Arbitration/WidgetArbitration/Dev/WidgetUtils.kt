package com.tmobile.widgetarbitration.util

import android.content.Context
import android.util.Log

object WidgetUtils {

    fun getCurrentTimestamp(): Long {
        return System.currentTimeMillis()
    }

    fun logDecision(tag: String = "WidgetArbitration", widgetId: String, decision: String, reason: String) {
        Log.d(tag, "Widget [$widgetId] => Decision: $decision | Reason: $reason")
    }

    fun simulateMetadata(contentType: String, minutesAgo: Int): Map<String, Any> {
        return mapOf(
            "type" to contentType,
            "lastRefresh" to System.currentTimeMillis() - (minutesAgo * 60 * 1000)
        )
    }
}