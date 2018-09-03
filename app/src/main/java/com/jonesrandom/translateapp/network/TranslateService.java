package com.jonesrandom.translateapp.network;

import com.jonesrandom.translateapp.entity.Translate;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TranslateService {

    @GET("api/v1.5/tr.json/getLangs")
    Call<ResponseBody> getLanguage(@Query("key") String key, @Query("ui") String ui);

    @GET("api/v1.5/tr.json/translate")
    Call<Translate> getTranslate(@Query("key") String key, @Query("text") String text, @Query("lang") String lang);
}
