package com.tmobile.widgetarbitration;

import java.util.Map;

public class WidgetArbitrationManager {

    public static class WidgetRefreshDecision {
        public final boolean shouldRefresh;
        public final String reason;

        public WidgetRefreshDecision(boolean shouldRefresh, String reason) {
            this.shouldRefresh = shouldRefresh;
            this.reason = reason;
        }
    }

    public static boolean isOverrideForced(String appPackage) {
        return appPackage.contains("sponsored");
    }

    public static double scoreEngagement(String widgetId, Map<String, Object> metadata) {
        // Placeholder ML logic
        return 0.85;
    }

    public static WidgetRefreshDecision shouldRefreshWidget(String widgetId, Map<String, Object> metadata, String appPackage) {
        if (isOverrideForced(appPackage)) {
            return new WidgetRefreshDecision(true, "Override by policy");
        }
        double score = scoreEngagement(widgetId, metadata);
        if (score >= 0.6) {
            return new WidgetRefreshDecision(true, "Engagement score passed");
        } else {
            return new WidgetRefreshDecision(false, "Suppressed due to low engagement");
        }
    }
}