package thai.app.lockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import thai.app.lockapp.R;
import thai.app.lockapp.service.ForegroundAppCheckerService;
import thai.app.lockapp.utils.Utils;

/**
 * Created by nguye on 4/9/2017.
 */

public class MainProgramActivity extends AppCompatActivity {

    private TextView mTextViewLockType;
    private View mViewItem1;
    private View mViewItem2;
    private View mViewItem3;
    private View mViewItem4;
    private Switch mSwitch;
    private int mCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_program);
        getSupportActionBar().setTitle(R.string.app_name);
        Utils.initRunningService(getApplicationContext());
        mViewItem1 = findViewById(R.id.setting_1);
        mViewItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
            }
        });
        mViewItem2 = findViewById(R.id.setting_2);
        mViewItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SelectPasswordTypeActivity.class);
                startActivity(i);
            }
        });
        mSwitch = (Switch) findViewById(R.id.is_app_enabled);
        mSwitch.setChecked(Utils.isServiceEnabled(this));
        mViewItem3 = findViewById(R.id.setting_3);
        mViewItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boolean enabled = Utils.isServiceEnabled(getApplicationContext());
                boolean enabled = mSwitch.isChecked();
                Intent i = new Intent(getApplicationContext(), ForegroundAppCheckerService.class);
                Utils.startBackgroundService(getApplicationContext());
                if(enabled){
                    if(Utils.stopBackgroundService(getApplicationContext())){
                        mSwitch.setChecked(!enabled);
                        Utils.setAppEnabled(getApplicationContext(), false);
                    }
                }else{
                    Utils.startBackgroundService(getApplicationContext());
                    Utils.setAppEnabled(getApplicationContext(), true);
                    mSwitch.setChecked(true);
                }
            }
        });
        mViewItem4 = findViewById(R.id.setting_4);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (mCount == 0) {
                Toast.makeText(getApplicationContext(), R.string.quit_app_notification, Toast.LENGTH_SHORT).show();
                mCount++;
                return true;
            }
            if (mCount == 2) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
