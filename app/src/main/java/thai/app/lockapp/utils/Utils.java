package thai.app.lockapp.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import thai.app.lockapp.activity.AuthenticationActivity;
import thai.app.lockapp.constants.Constants;
import thai.app.lockapp.service.ForegroundAppCheckerService;

/**
 * Created by nguye on 4/11/2017.
 */

public class Utils {

    public static boolean setAppEnabled(Context context, boolean enabled){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.CHECK_TOKEN_TYPE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.APP_ENABLED, enabled);
        return editor.commit();
    }

    public static boolean isServiceEnabled(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.CHECK_TOKEN_TYPE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(Constants.APP_ENABLED, false);
    }

    public static void startBackgroundService(Context context){
        Intent intent = new Intent(context, ForegroundAppCheckerService.class);
        context.getApplicationContext().startService(intent);
    }

    public static void savePreviousPackage(Context context, String packageName){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.CHECK_TOKEN_TYPE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.PREV_PACKAGE, packageName);
        editor.commit();
    }

    public static String getPreviousPackage(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.CHECK_TOKEN_TYPE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Constants.PREV_PACKAGE, "");
    }

    public static boolean stopBackgroundService(Context context){
        Intent intent = new Intent(context, ForegroundAppCheckerService.class);
        return context.stopService(intent);
    }

    public static boolean checkInLockList(String packageName){
        if(packageName.equals("thai.app.lockapp")||packageName.equals("com.sec.android.app.launcher"))
            return false;
        return true;
    }

    public static void startAuthenticateActivity(Context context){
        Intent i = new Intent(context, AuthenticationActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(i);
    }

    public static void initRunningService(Context context){
        boolean isEnabled = Utils.isServiceEnabled(context);
        if(isEnabled)
            Utils.startBackgroundService(context);
    }

    public static void startApp(Context context, String packageName){
        Intent LaunchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        LaunchIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(LaunchIntent);
    }

    public static void setAuthenticated(Context context, boolean authenticated){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.CHECK_TOKEN_TYPE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.AUTHENTICATED, authenticated);
        editor.commit();
    }
    public static boolean isAuthenticated(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.CHECK_TOKEN_TYPE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(Constants.AUTHENTICATED, false);
    }
}
