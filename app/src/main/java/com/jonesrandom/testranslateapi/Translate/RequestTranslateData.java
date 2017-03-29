package com.jonesrandom.testranslateapi.Translate;

import com.jonesrandom.testranslateapi.ModelTranslate.ModelTranslateResponse;
import com.jonesrandom.testranslateapi.MyInterfaces.ApiService;
import com.jonesrandom.testranslateapi.MyInterfaces.ResponseListener;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Masx Developer on 3/27/17.
 * https://masx-dev.blogspot.com
 */

public class RequestTranslateData {

    private ResponseListener.Translate Listener;

    public RequestTranslateData(ResponseListener.Translate listener) {
        Listener = listener;
    }

    public void getTranslate(String Key, String Text, String LangFrom, String LangTo) {

        Listener.onResponseTranslateProggress();

        String Lang = LangFrom + "-" + LangTo;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://translate.yandex.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<ModelTranslateResponse> responseCall = service.getTranslate(Key, Text, Lang);
        responseCall.enqueue(new Callback<ModelTranslateResponse>() {
            @Override
            public void onResponse(Call<ModelTranslateResponse> call, Response<ModelTranslateResponse> response) {

                if (response.isSuccessful()) {

                    int Code = response.body().getCode();

                    if (Code != 200){
                        Listener.onResponseTranslateError(response.body().getMessage());
                    } else {

                        String StringTranslate = response.body().getTextTranslate();
                        Listener.onResponseTranslateData(StringTranslate);

                    }

                } else {
                    Listener.onResponseTranslateError("Terjadi Kesalahan Saat Memuat Data");
                }

            }

            @Override
            public void onFailure(Call<ModelTranslateResponse> call, Throwable t) {
                Listener.onResponseTranslateError("Terjadi Kesalahan Saat Menghubungi Server");
            }
        });


    }
}
