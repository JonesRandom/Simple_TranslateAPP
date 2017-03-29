package com.jonesrandom.testranslateapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.jonesrandom.testranslateapi.Common.SpinnerAdapter;
import com.jonesrandom.testranslateapi.ModelTranslate.ModelLanguage;
import com.jonesrandom.testranslateapi.MyInterfaces.ResponseListener;
import com.jonesrandom.testranslateapi.Translate.RequestLanguageData;
import com.jonesrandom.testranslateapi.Translate.RequestTranslateData;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    RequestLanguageData LanguageData;
    RequestTranslateData TranslateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        LanguageData = new RequestLanguageData(new ResponseListener.Language() {
            @Override
            public void onResonseLanguageProggress() {
                Log.d("LOG", "onResonseLanguageProggress: Start Request");
            }

            @Override
            public void onResponseLanguageData(List<ModelLanguage> DataLanguage) {
                Log.d("LOG", "onResonseLanguageProggress: Success");
            }

            @Override
            public void onResponselanguageError(String Error) {
                Log.d("LOG", "onResonseLanguageProggress: " + Error);
            }
        });
        TranslateData = new RequestTranslateData(new ResponseListener.Translate() {
            @Override
            public void onResponseTranslateProggress() {
                Log.d("LOG", "onResponseTranslateData: Start Request");
            }

            @Override
            public void onResponseTranslateData(String StringTranslate) {
                Log.d("LOG", "onResponseTranslateData: " + StringTranslate);
            }

            @Override
            public void onResponseTranslateError(String Error) {
                Log.d("LOG", "onResponseTranslateData: " + Error);
            }
        });


    }

}
