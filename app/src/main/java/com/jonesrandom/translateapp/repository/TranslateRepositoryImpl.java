package com.jonesrandom.translateapp.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.jonesrandom.translateapp.BuildConfig;
import com.jonesrandom.translateapp.common.StringConverter;
import com.jonesrandom.translateapp.entity.Translate;
import com.jonesrandom.translateapp.network.TranslateClient;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class TranslateRepositoryImpl implements TranslateRepository {

    private static final String TAG = TranslateRepositoryImpl.class.getName();

    @Override
    public void getLanguage(final TranslateRepositoryCallback<ResponseBody> callback) {
        TranslateClient.getTranslateServices(new StringConverter())
                .getLanguage(BuildConfig.YANDEX_API_KEY, "en")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            callback.onSuccess(response.body());
                        } else {
                            Log.d(TAG, "onResponse: getCountry" + response.message());
                            callback.onFailed("Terjadi kesalahan saat menghubungi server");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        Log.d(TAG, "onFailure: getCountry" + t.getMessage());
                        callback.onFailed("tidak dapat terhubung");
                    }
                });
    }

    @Override
    public void translate(String text, String langFrom, String langTo, final TranslateRepositoryCallback<Translate> callback) {
        TranslateClient.getTranslateServices(GsonConverterFactory.create())
                .getTranslate(BuildConfig.YANDEX_API_KEY, text, langFrom + "-" + langTo)
                .enqueue(new Callback<Translate>() {
                    @Override
                    public void onResponse(@NonNull Call<Translate> call, @NonNull Response<Translate> response) {
                        if (response.isSuccessful()){
                            callback.onSuccess(response.body());
                        } else {
                            Log.d(TAG, "onResponse: " + response.message());
                            callback.onFailed("Terjadi kesalahan saat menghubungi server");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Translate> call, @NonNull Throwable t) {
                        Log.d(TAG, "onResponse: " + t.getMessage());
                        callback.onFailed("tidak dapat terhubung");
                    }
                });
    }
}
