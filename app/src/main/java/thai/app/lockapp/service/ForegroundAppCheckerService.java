package thai.app.lockapp.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.rvalerio.fgchecker.AppChecker;

import java.util.List;

import thai.app.lockapp.activity.AuthenticationActivity;
import thai.app.lockapp.model.LoginToken;
import thai.app.lockapp.utils.Utils;

/**
 * Created by nguye on 4/11/2017.
 */

public class ForegroundAppCheckerService extends Service {
    private static final String TAG = "thai.app.lockapp.check";
    private static String sPreviousPackage = null;
    public static String sCurrentProgram;
    public static boolean sAuthenticated;
    public static String sBlockedProgram;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onTaskRemoved(intent);
        AppChecker appChecker = new AppChecker();
        Context context = getApplicationContext();
        String packageName = appChecker.getForegroundApp(context);
       // Log.d(TAG, packageName);
        if (!Utils.isAuthenticated(getApplicationContext())) {
            if (packageName != null && Utils.checkInLockList(packageName) && !packageName.equals("thai.app.lockapp")) {
                if (!packageName.equals(sPreviousPackage)) {
                    sBlockedProgram = packageName;
                    Utils.startAuthenticateActivity(context);
                }
            }
        }
        sPreviousPackage = sCurrentProgram;
        sCurrentProgram = packageName;
        return Service.START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartIntent = new Intent(getApplicationContext(), this.getClass());
        restartIntent.setPackage(getPackageName());
        startService(restartIntent);
        super.onTaskRemoved(rootIntent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
