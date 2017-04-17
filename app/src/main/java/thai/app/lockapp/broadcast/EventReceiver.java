package thai.app.lockapp.broadcast;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import thai.app.lockapp.service.ForegroundAppCheckerService;

/**
 * Created by nguye on 4/16/2017.
 */

public class EventReceiver extends DeviceAdminReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        Intent i = new Intent(context, ForegroundAppCheckerService.class);
        if(intentAction.equals(Intent.ACTION_SCREEN_ON)){
            context.startService(i);
//            Log.d(TAG, "Screen on");
        }else if(intentAction.equals(Intent.ACTION_SCREEN_OFF)){
            context.stopService(i);
//            Log.d(TAG, "Screen off");
        }
        super.onReceive(context, intent);
    }
}
