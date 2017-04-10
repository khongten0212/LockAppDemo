package thai.app.lockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import thai.app.lockapp.R;

/**
 * Created by nguye on 4/9/2017.
 */

public class MainProgramActivity extends AppCompatActivity {
    private TextView mTextViewLockType;
    private View mViewItem1;
    private View mViewItem2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_program);
        getSupportActionBar().setTitle(R.string.app_name);
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
    }
}
