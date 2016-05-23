package com.example.oracle.moneyflow.services;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;

import com.example.oracle.moneyflow.util.Prefs;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MyIntentService extends IntentService {

    private static final String ACTION_INSERT_EXPENCY = "com.example.oracle.moneyflow.services.action.INSERT_EXPENCY";

    private static final String EXTRA_INSERT_EXPENCY_NAME = "com.example.oracle.moneyflow.services.extra.INSERT_EXPENCY_NAME";
    private static final String EXTRA_INSERT_EXPENCY_VOLUME = "com.example.oracle.moneyflow.services.extra.INSERT_EXPENCY_VOLUME";


    public MyIntentService() {
        super("MyIntentService");
    }

    public static void startActionInsertExpency(Context context, String name, int volume) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_INSERT_EXPENCY);
        intent.putExtra(EXTRA_INSERT_EXPENCY_NAME, name);
        intent.putExtra(EXTRA_INSERT_EXPENCY_VOLUME, volume);
        context.startService(intent);
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            switch (action) {
                case ACTION_INSERT_EXPENCY:
                    final String name = intent.getStringExtra(EXTRA_INSERT_EXPENCY_NAME);
                    final int volume = intent.getIntExtra(EXTRA_INSERT_EXPENCY_VOLUME, 0);
                    handleActionInsertExpense(name, volume);
                    break;

            }
        }
    }

    private void handleActionInsertExpense(String name, int volume) {
        ContentValues cv = new ContentValues();
        //cv.put(Prefs.EXPENSE_NAMES_FIELD_NAME, name);
        cv.put(Prefs.EXPENSE_FIELD_ID_PASSIVE, 1);
        String date = String.valueOf(Calendar.getInstance().getTimeInMillis());

        cv.put(Prefs.EXPENSE_FIELD_DATE, date);
        cv.put(Prefs.EXPENSE_FIELD_VOLUME, volume);
        getContentResolver().insert(Prefs.URI_EXPENSE, cv);

    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
