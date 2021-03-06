package thai.app.lockapp.activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import thai.app.lockapp.R;
import thai.app.lockapp.broadcast.ScreenOnBroadCastReceiver;
import thai.app.lockapp.inputfragment.InputFragment;
import thai.app.lockapp.inputfragment.PasswordInputFragment;
import thai.app.lockapp.inputfragment.PatternInputFragment;
import thai.app.lockapp.inputfragment.PinInputFragment;
import thai.app.lockapp.interfaces.InputFragmentImplements;
import thai.app.lockapp.model.LoginToken;
import thai.app.lockapp.service.ForegroundAppCheckerService;
import thai.app.lockapp.utils.Utils;

import static thai.app.lockapp.model.LoginToken.sLoginToken;

public class StartActivity extends AppCompatActivity implements InputFragmentImplements {
    private InputFragment mInputFragment;
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        getSupportActionBar().setTitle(R.string.input_prompt);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.input_fragment_container);
        LoginToken token = new LoginToken(LoginToken.INPUT_PATTERN, "012543678", "012543678");
        LoginToken.saveLoginType(getApplicationContext(), token);

        Utils.setAuthenticated(this, false);
        if (fragment == null) {
            sLoginToken = LoginToken.getSavedLoginType(getApplicationContext());
            if (sLoginToken == null) {
                Intent i = new Intent(getApplicationContext(), MainProgramActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            } else {
                switch (sLoginToken.getType()) {
                    case LoginToken.INPUT_PASSWORD:
                        mInputFragment = new PasswordInputFragment();
                        break;
                    case LoginToken.INPUT_PATTERN:
                        mInputFragment = new PatternInputFragment();
                        break;
                    case LoginToken.INPUT_PIN:
                        mInputFragment = new PinInputFragment();
                        break;
                    default:
                        System.exit(0);
                }
                fm.beginTransaction().add(R.id.input_fragment_container, mInputFragment)
                        .commit();
            }
        }
//        Intent i = new Intent(getApplicationContext(), ForegroundAppCheckerService.class);
//        startService(i);
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenOnBroadCastReceiver();
        registerReceiver(mReceiver, filter);
//        Utils.initRunningService(this);
    }

    @Override
    public boolean processInput(String value11, String value2) {
        if (sLoginToken.getValue2().equals(value2)) {
            Intent i = new Intent(getApplicationContext(), MainProgramActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            return true;
        } else {
            mCount++;
            if(mCount == 3){
                finish();
            }
            Toast.makeText(getApplicationContext(), R.string.notification_incorrect, Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
    }
}
