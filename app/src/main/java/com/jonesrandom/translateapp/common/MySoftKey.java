package com.jonesrandom.translateapp.common;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MySoftKey {

    public static void hide(View view){
        InputMethodManager iml = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (iml != null) iml.hideSoftInputFromWindow(view.getWindowToken() , 0);
    }
}
