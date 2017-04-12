package thai.app.lockapp.inputfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import thai.app.lockapp.R;
import thai.app.lockapp.interfaces.InputFragmentImplements;

/**
 * Created by nguye on 4/9/2017.
 */

public class PasswordInputFragment extends InputFragment {
    private EditText mPasswordInput;
    private Button mCancelButton, mOkButton;
    private InputFragmentImplements mCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_input_password, container, false);
        mPasswordInput = (EditText) v.findViewById(R.id.input_text_password);
        mCancelButton = (Button) v.findViewById(R.id.button_cancel_pass);
        mOkButton = (Button) v.findViewById(R.id.button_ok_pass);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = mPasswordInput.getText().toString();
                if(mCallback.processInput(null, pass)){
                    mPasswordInput.setText("");
                }
            }
        });
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (InputFragmentImplements) activity;
    }
}
