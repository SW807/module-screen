package dk.aau.cs.psylog.screen;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import dk.aau.cs.psylog.module_lib.DBAccessContract;
import dk.aau.cs.psylog.module_lib.ISensor;


public class ScreenListener implements ISensor{

    private BroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;
    private Context _context;

    ContentResolver resolver;
    public ScreenListener(Context context){
        _context = context;
        intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);

        resolver = context.getContentResolver();

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Uri uri = Uri.parse(DBAccessContract.DBACCESS_CONTENTPROVIDER + "screenonoff");
                ContentValues values = new ContentValues();
                if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                    values.put("screenon",0);
                    resolver.insert(uri, values);
                } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                    values.put("screenon",1);
                    resolver.insert(uri, values);
                }
            }
        };
    }

    public void startSensor() {
        _context.registerReceiver(broadcastReceiver, intentFilter);
    }
    public void stopSensor() {
        _context.unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void sensorParameters(Intent intent) {
        
    }
}
