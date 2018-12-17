package com.example.michael.myapplication;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.michael.myapplication.data.MyDataContract;

import java.util.Random;

import static android.provider.BaseColumns._ID;
import static com.example.michael.myapplication.data.MyDataContract.MyData.CONTENT_URI;

/**
 * Implementation of App Widget functionality.
 */
public class MyDataWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // data
        String projection[] = {"_ID", "firstname", "lastname"};
        Cursor myData = context.getContentResolver().query(CONTENT_URI, projection, null, null, null);

        Random r = new Random();
        int id = r.nextInt(myData.getCount());

        myData.moveToPosition(id);
        String ID = myData.getString(myData.getColumnIndex(_ID));
        CharSequence widgetText = myData.getString(myData.getColumnIndex(MyDataContract.MyData.COLUMN_FIRST_NAME)) + " " + myData.getString(myData.getColumnIndex(MyDataContract.MyData.COLUMN_LAST_NAME));
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_data_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // On Click Pending Intent
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("ID", ID);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

        // On new
        Intent b_intent = new Intent(context, MyIntentService.class);
        b_intent.setAction(MyIntentService.ACTION_WUP);
        PendingIntent b_pendingIntent = PendingIntent.getService(context, 0, b_intent, 0);
        views.setOnClickPendingIntent(R.id.b_new, b_pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    static public void onUpdateWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        Log.d("I","onUpdateWidgets");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
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
}

