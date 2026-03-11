package com.tmobile.widgetarbitration;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.HashMap;
import java.util.Map;

public class NewsWidgetProvider extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(intent.getAction())) {
            int[] widgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
            if (widgetIds != null) {
                for (int widgetId : widgetIds) {
                    handleWidgetUpdate(context, widgetId);
                }
            }
        }
    }

    private void handleWidgetUpdate(Context context, int widgetId) {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("type", "news");
        metadata.put("lastRefresh", System.currentTimeMillis() - 7200000L); // 2 hrs ago

        String appPackage = context.getPackageName();
        WidgetArbitrationManager.WidgetRefreshDecision decision =
            WidgetArbitrationManager.shouldRefreshWidget(String.valueOf(widgetId), metadata, appPackage);

        if (decision.shouldRefresh) {
            Log.d("WidgetArbitration", "Refreshing widget " + widgetId + ": " + decision.reason);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            views.setTextViewText(R.id.widget_title, "Latest Headlines at " + System.currentTimeMillis());
            AppWidgetManager.getInstance(context).updateAppWidget(widgetId, views);
        } else {
            Log.d("WidgetArbitration", "Suppressing widget " + widgetId + ": " + decision.reason);
        }
    }
}