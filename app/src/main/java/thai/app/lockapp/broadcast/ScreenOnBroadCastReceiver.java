package thai.app.lockapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import thai.app.lockapp.service.ForegroundAppCheckerService;

/**
 * Created by nguye on 4/16/2017.
 */

public class ScreenOnBroadCastReceiver extends BroadcastReceiver {
    private static final String TAG = "thai.BroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        Intent i = new Intent(context, ForegroundAppCheckerService.class);
        if(intentAction.equals(Intent.ACTION_SCREEN_ON)){
            context.startService(i);
            Log.d(TAG, "Screen on");
        }else if(intentAction.equals(Intent.ACTION_SCREEN_OFF)){
            context.stopService(i);
            Log.d(TAG, "Screen off");
        }
    }
}
