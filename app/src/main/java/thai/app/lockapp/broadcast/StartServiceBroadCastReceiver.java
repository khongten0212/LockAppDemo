package thai.app.lockapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import thai.app.lockapp.service.ForegroundAppCheckerService;

/**
 * Created by nguye on 4/11/2017.
 */

public class StartServiceBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, ForegroundAppCheckerService.class);
        context.startService(i);
    }
}
