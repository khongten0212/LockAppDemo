package thai.app.lockapp.inputfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;

import java.util.List;

import thai.app.lockapp.R;
import thai.app.lockapp.interfaces.InputFragmentImplements;

/**
 * Created by nguye on 4/9/2017.
 */

public class PatternInputFragment extends InputFragment {
    private PatternLockView mPatternInput;
    private InputFragmentImplements mCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_input_lock_pattern, container, false);
        mPatternInput = (PatternLockView) v.findViewById(R.id.input_pattern);
        mPatternInput.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                String pass = "";
                for (PatternLockView.Dot dot : pattern) {
                    pass += dot.getId();
                }
                mCallback.processInput(null, pass);
                mPatternInput.clearPattern();
            }

            @Override
            public void onCleared() {

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
