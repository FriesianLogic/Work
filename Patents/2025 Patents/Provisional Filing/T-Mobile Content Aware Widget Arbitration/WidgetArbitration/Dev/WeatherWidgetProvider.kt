package com.tmobile.widgetarbitration

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews

class WeatherWidgetProvider : AppWidgetProvider() {

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
            val widgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS)
            widgetIds?.forEach { widgetId ->
                handleWidgetUpdate(context, widgetId)
            }
        }
    }

    private fun handleWidgetUpdate(context: Context, widgetId: Int) {
        val metadata = mapOf(
            "type" to "weather",
            "lastRefresh" to System.currentTimeMillis() - 3600000L // 1 hour ago
        )

        val appPackage = context.packageName
        val decision = WidgetArbitrationManager.shouldRefreshWidget(widgetId.toString(), metadata, appPackage)

        if (decision.shouldRefresh) {
            Log.d("WidgetArbitration", "Refreshing Weather widget $widgetId: ${decision.reason}")
            val views = RemoteViews(context.packageName, R.layout.weather_widget_layout)
            views.setTextViewText(R.id.widget_weather_info, "72°F, Sunny @ ${System.currentTimeMillis()}")
            AppWidgetManager.getInstance(context).updateAppWidget(widgetId, views)
        } else {
            Log.d("WidgetArbitration", "Suppressing Weather widget $widgetId: ${decision.reason}")
        }
    }
}