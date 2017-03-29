package com.jonesrandom.testranslateapi.MyInterfaces;

import com.jonesrandom.testranslateapi.ModelTranslate.ModelTranslateResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("api/v1.5/tr.json/getLangs")
    Call<ResponseBody> getLangs(@Field("key") String Key, @Field("ui") String Ui);

    @GET("api/v1.5/tr.json/translate")
    Call<ModelTranslateResponse> getTranslate(@Query("key") String Key, @Query("text") String Text, @Query("lang") String Lang);
}
