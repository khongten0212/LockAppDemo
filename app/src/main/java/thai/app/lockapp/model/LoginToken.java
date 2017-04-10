package thai.app.lockapp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.HashMap;

import thai.app.lockapp.constants.Constants;

/**
 * Created by nguye on 4/9/2017.
 */

public class LoginToken {
    public static final int INPUT_PASSWORD = 1;
    public static final int INPUT_PIN = 2;
    public static final int INPUT_PATTERN = 3;
    public static final int INPUT_FACE_PASSWORD = 4;
    public static final int INPUT_FACE_PIN = 5;
    public static final int INPUT_FACE_PATTERN = 6;
    public static LoginToken sLoginToken;
    private int mType;
    private String mTypeName;

    private String mValue1;
    private String mValue2;
    private static HashMap<Integer, String> sLockType = new HashMap<>();
    static{
        sLockType.put(new Integer(1), "Password");
        sLockType.put(new Integer(2), "Pin");
        sLockType.put(new Integer(3), "Pattern");
        sLockType.put(new Integer(4), "Face, Password");
        sLockType.put(new Integer(5), "Face, Pin");
        sLockType.put(new Integer(6), "Face, Pattern");
    }

    public LoginToken(int type, String value1, String value2) {
        mType = type;
        mValue1 = value1;
        mValue2 = value2;
        mTypeName = sLockType.get(mType);
    }

    public String getTypeName() {
        return mTypeName;
    }

    public void setTypeName(String typeName) {
        mTypeName = typeName;
    }

    public String getValue2() {
        return mValue2;
    }

    public void setValue2(String value2) {
        mValue2 = value2;
    }

    public int getType() {
        return mType;
    }

    public String getValue1() {
        return mValue1;
    }

    public void setValue1(String value1) {
        mValue1 = value1;
    }

    @Nullable
    public static LoginToken getSavedLoginType(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.CHECK_TOKEN_TYPE, Context.MODE_PRIVATE);
        int logintype = sharedPreferences.getInt(Constants.LOGIN_TYPE, -1);
        if (logintype == -1)
            return null;
        LoginToken token = new LoginToken(logintype,
                sharedPreferences.getString(Constants.LOGIN_VALUE, null),
                sharedPreferences.getString(Constants.LOGIN_VALUE_2, null));
        return token;
    }

    public static boolean saveLoginType(Context context, LoginToken loginToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.CHECK_TOKEN_TYPE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.LOGIN_TYPE, loginToken.getType());
        editor.putString(Constants.LOGIN_VALUE, loginToken.getValue1());
        editor.putString(Constants.LOGIN_VALUE_2, loginToken.getValue2());
        return editor.commit();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LoginToken))
            return false;
        LoginToken o = (LoginToken) obj;
        if (o.getType() != getType())
            return false;
        if (!o.getValue1().equals(getValue1()))
            return false;
        if(!o.getValue2().equals(getValue2()))
            return false;
        return true;
    }
}
