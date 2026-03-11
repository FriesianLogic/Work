package com.tmobile.widgetarbitration

data class WidgetRefreshDecision(val shouldRefresh: Boolean, val reason: String)

object EngagementScorer {
    fun scoreEngagement(widgetId: String, metadata: Map<String, Any>): Double {
        // Simulate ML logic
        return 0.85 // Placeholder
    }
}

object OverridePolicyHandler {
    fun isOverrideForced(appPackage: String): Boolean {
        return appPackage.contains("sponsored")
    }
}

object WidgetArbitrationManager {
    fun shouldRefreshWidget(widgetId: String, metadata: Map<String, Any>, appPackage: String): WidgetRefreshDecision {
        if (OverridePolicyHandler.isOverrideForced(appPackage)) {
            return WidgetRefreshDecision(true, "Override by policy")
        }
        val score = EngagementScorer.scoreEngagement(widgetId, metadata)
        return if (score >= 0.6) {
            WidgetRefreshDecision(true, "Engagement score passed")
        } else {
            WidgetRefreshDecision(false, "Suppressed due to low engagement")
        }
    }
}