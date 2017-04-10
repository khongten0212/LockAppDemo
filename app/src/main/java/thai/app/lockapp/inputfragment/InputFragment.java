package thai.app.lockapp.inputfragment;

import android.content.Intent;
import android.support.v4.app.Fragment;


import thai.app.lockapp.activity.MainProgramActivity;

import static thai.app.lockapp.model.LoginToken.sLoginToken;

/**
 * Created by nguye on 4/9/2017.
 */

public class InputFragment extends Fragment {

    protected boolean checkToken(String value) {
        if (sLoginToken.getValue1().equals(value))
            return true;
        if (sLoginToken.getValue2() != null)
            if (sLoginToken.getValue2().equals(value))
                return true;
        return false;
    }

    protected void redirectToMainActivity() {
        Intent i = new Intent(getActivity(), MainProgramActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
