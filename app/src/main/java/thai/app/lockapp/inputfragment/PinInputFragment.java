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

public class PinInputFragment extends InputFragment {
    private EditText mPasswordField;
    private Button mCancelButton, mOkButton;
    private InputFragmentImplements mCallback;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_input_pin, container, false);
        mPasswordField = (EditText) v.findViewById(R.id.input_pin_password);
        mCancelButton = (Button) v.findViewById(R.id.button_cancel_pin);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        mOkButton = (Button) v.findViewById(R.id.button_ok_pin);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = mPasswordField.getText().toString();
                if(mCallback.processInput(null, pass)){
                    mPasswordField.setText("");
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
