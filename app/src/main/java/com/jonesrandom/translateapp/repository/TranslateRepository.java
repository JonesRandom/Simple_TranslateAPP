package com.jonesrandom.translateapp.repository;

import com.jonesrandom.translateapp.entity.Translate;

import okhttp3.ResponseBody;

public interface TranslateRepository {
    void getLanguage(TranslateRepositoryCallback<ResponseBody> callback);

    void translate(String Text, String LangFrom, String LangTo, TranslateRepositoryCallback<Translate> callback);
}
