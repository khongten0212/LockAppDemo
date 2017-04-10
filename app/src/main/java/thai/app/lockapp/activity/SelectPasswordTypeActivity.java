package thai.app.lockapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import thai.app.lockapp.R;
import thai.app.lockapp.model.LoginToken;

public class SelectPasswordTypeActivity extends AppCompatActivity implements View.OnClickListener{

    private View mSetPasswordView;
    private View mSetPinView;
    private View mSetPatternView;
    private static final int CREATE_NEW_TOKEN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_password_type);
        getSupportActionBar().setTitle(R.string.label_select_password_type);
        mSetPasswordView = findViewById(R.id.pass_bt);
        mSetPinView = findViewById(R.id.pin_bt);
        mSetPatternView = findViewById(R.id.pattern_bt);
        mSetPasswordView.setOnClickListener(this);
        mSetPinView.setOnClickListener(this);
        mSetPatternView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int tokenType = 0;
        switch(v.getId()){
            case R.id.pass_bt:
                tokenType = LoginToken.INPUT_PASSWORD;
                break;
            case R.id.pin_bt:
                tokenType = LoginToken.INPUT_PIN;
                break;
            case R.id.pattern_bt:
                tokenType = LoginToken.INPUT_PATTERN;
                break;
        }
        Intent i = RegisterActivity.createIntent(getApplicationContext(), tokenType);
        startActivityForResult(i, CREATE_NEW_TOKEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK){
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
        if(requestCode == CREATE_NEW_TOKEN){

        }
    }
}
