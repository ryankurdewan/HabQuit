package layout;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.content.Intent;
import android.widget.Toast;
import android.app.PendingIntent;

import com.aquamorph.habquit.R;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link widget_activityConfigureActivity widget_activityConfigureActivity}
 */
//Widget activity added by JCW
public class widget_activity extends AppWidgetProvider {

    private static final String CLICK_ACTION = "myCustomAction";

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
        System.out.println("on-update widget");

        for (int widgetId : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget_activity);

            Intent intent = new Intent(context, widget_activity.class);

            intent.setAction(CLICK_ACTION);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, 0);

            remoteViews.setOnClickPendingIntent(R.id.button2,
                    pendingIntent);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
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
        super.onReceive(context, intent);

        String action = intent.getAction();

        System.out.println("onReceive action: " + action);

        if (CLICK_ACTION.equals(action)) {
            System.out.println("clicked ");
            Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show();
        }

    }
}

