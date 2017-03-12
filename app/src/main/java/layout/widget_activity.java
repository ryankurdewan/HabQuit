package layout;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.content.Intent;
import android.widget.Toast;
import android.app.PendingIntent;

import com.aquamorph.habquit.R;

import static android.R.style.Widget;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link widget_activityConfigureActivity widget_activityConfigureActivity}
 */

//Current issue: remove widget and the newly added widget does not update correctly
//Widget activity added by JCW
public class widget_activity extends AppWidgetProvider {

    private static final String SYNC_CLICKED    = "AppWidgetManager.ACTION_APPWIDGET_UPDATE";
    public static int incCount = 0;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // System.out.println("updateAppWidget(): widgetId = " + String.valueOf(appWidgetId) + "... Count : " +  String.valueOf(incCount));
        // Toast.makeText(context, "updateAppWidget(): widgetId = " + String.valueOf(appWidgetId) + "... Count : " +  String.valueOf(incCount), Toast.LENGTH_LONG).show();

        // set up remote view and set/update text views
        RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.widget_activity);
        updateViews.setTextViewText(R.id.appWidgetText, String.valueOf(incCount));
        updateViews.setTextViewText(R.id.appwidget_text, "app widget id: [" + String.valueOf(appWidgetId) + "]");

        ComponentName thisWidget = new ComponentName(context, widget_activity.class);

        Intent intent = new Intent(context, widget_activity.class);
        Bundle extras = new Bundle();
        extras.putInt("ID", appWidgetId);
        intent.setAction(SYNC_CLICKED);
        intent.putExtras(extras);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, appWidgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        updateViews.setOnClickPendingIntent(R.id.appWidgetButton, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(thisWidget, updateViews);

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (SYNC_CLICKED.equals(intent.getAction())) {

            Bundle extras = intent.getExtras();

            if(extras!=null) {

                incCount++;

                int widgetId = extras.getInt("ID", 0);

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

                //Toast.makeText(context, "onReceive(): widgetId = " + String.valueOf(widgetId) + "... Count : " +  String.valueOf(incCount), Toast.LENGTH_LONG).show();
                //System.out.println("onReceive(): widgetId = " + String.valueOf(widgetId) + "... Count : " +  String.valueOf(incCount));

                updateAppWidget(context, appWidgetManager, widgetId);

            }
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.

        for (int appWidgetId : appWidgetIds) {

            widget_activityConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created

        // Toast.makeText(context, "onEnabled()", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled

        // need to update database here

        // Toast.makeText(context, "onDisabled()", Toast.LENGTH_LONG).show();
    }


}

