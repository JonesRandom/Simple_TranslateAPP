package com.jonesrandom.testranslateapi.MyInterfaces;

import com.jonesrandom.testranslateapi.ModelTranslate.ModelLanguage;

import java.util.List;

/**
 * Created by Masx Developer on 3/27/17.
 * https://masx-dev.blogspot.com
 */

public class ResponseListener {

    public interface Language {
        void onResonseLanguageProggress();

        void onResponseLanguageData(List<ModelLanguage> DataLanguage);

        void onResponselanguageError(String Error);
    }

    public interface Translate {
        void onResponseTranslateProggress();

        void onResponseTranslateData(String StringTranslate);

        void onResponseTranslateError(String Error);
    }
}
