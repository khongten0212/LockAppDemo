package thai.app.lockapp.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import thai.app.lockapp.R;
import thai.app.lockapp.inputfragment.PasswordInputFragment;
import thai.app.lockapp.inputfragment.PatternInputFragment;
import thai.app.lockapp.inputfragment.PinInputFragment;
import thai.app.lockapp.interfaces.InputFragmentImplements;
import thai.app.lockapp.model.LoginToken;
import thai.app.lockapp.service.ForegroundAppCheckerService;
import thai.app.lockapp.utils.Utils;

public class AuthenticationActivity extends AppCompatActivity implements InputFragmentImplements {
    private Fragment mFragment;
    private int mCount = 0;
    public static AuthenticationActivity sInstance;
    private LoginToken mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        createFragment();
        sInstance = this;

    }

    private void createFragment() {
        FragmentManager fm = getSupportFragmentManager();
        mFragment = fm.findFragmentById(R.id.input_fragment_container);
        int tokenType = LoginToken.getSavedLoginType(getApplicationContext()).getType();
        if (mFragment == null) {
            switch (tokenType) {
                case LoginToken.INPUT_PASSWORD:
                    mFragment = new PasswordInputFragment();
                    break;
                case LoginToken.INPUT_PIN:
                    mFragment = new PinInputFragment();
                    break;
                case LoginToken.INPUT_PATTERN:
                    mFragment = new PatternInputFragment();
                    break;
            }
            fm.beginTransaction().add(R.id.input_fragment_container, mFragment).commit();
        }
    }

    @Override
    public boolean processInput(String value11, String value2) {
        String token = LoginToken.getSavedLoginType(getApplicationContext()).getValue2();
        if (token.equals(value2)) {
//            ForegroundAppCheckerService.sAuthenticated = true;
            Utils.setAuthenticated(this, true);
            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(ForegroundAppCheckerService.sBlockedProgram);
            LaunchIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity( LaunchIntent );
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    Utils.setAuthenticated(getApplicationContext(), false);
                }
            }, 1000);
        } else {
            mCount++;
            if (mCount == 3) {
                mCount = 0;
                redirectToHomeScreen();
                finish();
            }
        }
        ForegroundAppCheckerService.sAuthenticated = false;
        return true;
    }

    private void redirectToHomeScreen() {
        Intent startHomescreen = new Intent(Intent.ACTION_MAIN);
        startHomescreen.addCategory(Intent.CATEGORY_HOME);
        startHomescreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(startHomescreen);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            redirectToHomeScreen();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
