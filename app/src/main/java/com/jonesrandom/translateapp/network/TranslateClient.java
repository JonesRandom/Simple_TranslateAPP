package com.jonesrandom.translateapp.network;

import com.jonesrandom.translateapp.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class TranslateClient {

    public static <T extends Converter.Factory> TranslateService getTranslateServices(T converter) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG){
            builder.addInterceptor(interceptor);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://translate.yandex.net/")
                .client(builder.build())
                .addConverterFactory(converter)
                .build();

        return retrofit.create(TranslateService.class);
    }
}
