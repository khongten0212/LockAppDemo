package thai.app.lockapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import thai.app.lockapp.R;
import thai.app.lockapp.inputfragment.PasswordInputFragment;
import thai.app.lockapp.inputfragment.PatternInputFragment;
import thai.app.lockapp.inputfragment.PinInputFragment;
import thai.app.lockapp.interfaces.InputFragmentImplements;
import thai.app.lockapp.model.LoginToken;

/**
 * Created by nguye on 4/9/2017.
 */

public class RegisterActivity extends AppCompatActivity implements InputFragmentImplements {
    private static final String TOKEN_TYPE_REQUEST = "thai.app.lockapp.activity.RegsiterActivity.TOKEN_TYPE_REQUEST";
    private static final String TOKEN_TYPE_RETURN = "thai.app.lockapp.activity.RegsiterActivity.TOKEN_TYPE_RETURN";
    private Fragment mFragment;
    private int mCount = 1;
    private String mSavedValue;
    private int mTokenType;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.create_new);
        setContentView(R.layout.activity_input);
        mTokenType = getIntent().getIntExtra(TOKEN_TYPE_REQUEST, 1);
        createFragment();
    }

    private void createFragment(){
        FragmentManager fm = getSupportFragmentManager();
        mFragment = fm.findFragmentById(R.id.input_fragment_container);
        if (mFragment == null) {
            switch (mTokenType) {
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

    public static Intent createIntent(Context context, int tokenType) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra(TOKEN_TYPE_REQUEST, tokenType);
        return intent;
    }

    public static String getData(Intent intent){
        return intent.getStringExtra(TOKEN_TYPE_RETURN);
    }

    @Override
    public boolean processInput(String value) {
        if (mCount == 1) {
            mSavedValue = value;
            mCount++;
            Toast.makeText(this, "Please confirm password", Toast.LENGTH_LONG).show();
        } else if (mCount == 2) {
            if(!value.equals(mSavedValue)){
                Toast.makeText(this, "Incorrect", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Correct", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.putExtra(TOKEN_TYPE_RETURN, value);
                setResult(RESULT_OK, intent);
            }
            finish();
        }
        return true;
    }
}
