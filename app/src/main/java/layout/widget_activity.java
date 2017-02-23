package layout;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
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

    private static final String SYNC_CLICKED    = "automaticWidgetSyncButtonClick";
    private static int incCount = 0;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = widget_activityConfigureActivity.loadTitlePref(context, appWidgetId);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_activity);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            System.out.println("on-update widget");

            RemoteViews remoteViews;
            ComponentName watchWidget;

            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_activity);
            watchWidget = new ComponentName(context, widget_activity.class);

            remoteViews.setOnClickPendingIntent(R.id.widget_inc_button, getPendingSelfIntent(context, SYNC_CLICKED));
            appWidgetManager.updateAppWidget(watchWidget, remoteViews);
        }
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
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        super.onReceive(context, intent);
        if(intent != null) {
            if (SYNC_CLICKED.equals(intent.getAction())) {

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

                RemoteViews remoteViews;
                ComponentName watchWidget;

                incCount++;
                String incCountStr = Float.toString(incCount);

                remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_activity);
                watchWidget = new ComponentName(context, widget_activity.class);

                remoteViews.setTextViewText(R.id.widget_inc_button, incCountStr);

                appWidgetManager.updateAppWidget(watchWidget, remoteViews);

            }
        }

    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {

        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}

