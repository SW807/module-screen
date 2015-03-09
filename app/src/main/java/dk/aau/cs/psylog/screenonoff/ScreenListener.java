package dk.aau.cs.psylog.screenonoff;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import dk.aau.cs.psylog.module_lib.ISensor;


public class ScreenListener implements ISensor{

    private BroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;
    private Context _context;

    public ScreenListener(Context context){
        _context = context;
        intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                    Log.d("PsyLogScreenOFF", Intent.ACTION_SCREEN_OFF);
                } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                    Log.d("PsyLogScreenON", Intent.ACTION_SCREEN_ON);
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
