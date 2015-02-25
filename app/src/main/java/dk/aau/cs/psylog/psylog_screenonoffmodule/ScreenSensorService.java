package dk.aau.cs.psylog.psylog_screenonoffmodule;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ScreenSensorService extends Service {
    boolean isRunning = false;
    ScreenListener screenListener;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy()
    {
        screenListener.stopSensor();
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int startid)
    {
        if(!isRunning){
            isRunning = true;
            screenListener = new ScreenListener(this);
        }

        //Skal være START_STICKY hvis servicen skal køre hele tiden, selv hvis den bliver dræbt. START_NOT_STICKY hjælper når man programmere.
        return Service.START_NOT_STICKY;
    }

}
