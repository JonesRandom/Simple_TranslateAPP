package com.jonesrandom.testranslateapi.Translate;

import com.jonesrandom.testranslateapi.Common.StringConverter;
import com.jonesrandom.testranslateapi.ModelTranslate.ModelLanguage;
import com.jonesrandom.testranslateapi.MyInterfaces.ApiService;
import com.jonesrandom.testranslateapi.MyInterfaces.ResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Masx Developer on 3/27/17.
 * https://masx-dev.blogspot.com
 */

public class RequestLanguageData {

    private ResponseListener.Language Listener;

    public RequestLanguageData(ResponseListener.Language listener) {
        Listener = listener;
    }

    public void getLanguage(String Key, String UI) {

        Listener.onResonseLanguageProggress();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://translate.yandex.net/")
                .addConverterFactory(new StringConverter())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<ResponseBody> call = service.getLangs(Key, UI);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {

                    List<ModelLanguage> dataLanguage = new ArrayList<>();
                    List<String> listID = new ArrayList<>();

                    try {

                        String JSONData = response.body().string();

                        JSONObject objectData = new JSONObject(JSONData);
                        JSONObject objectLanguage = objectData.getJSONObject("langs");

                        Iterator<String> iterator = objectLanguage.keys();

                        while (iterator.hasNext()){
                            String KeyObject = iterator.next();
                            listID.add(KeyObject);
                        }

                        for (int i = 0; i < listID.size(); i++){
                            String ID = listID.get(i);
                            String Language = (String) objectLanguage.get(ID);

                            ModelLanguage language = new ModelLanguage();
                            language.setId(ID);
                            language.setLangs(Language);
                            dataLanguage.add(language);
                        }

                        Collections.sort(dataLanguage, new Comparator<ModelLanguage>() {
                            @Override
                            public int compare(ModelLanguage modelLanguage, ModelLanguage t1) {
                                return modelLanguage.getLangs().compareTo(t1.getLangs());
                            }
                        });

                        Listener.onResponseLanguageData(dataLanguage);


                    } catch (IOException | JSONException e) {
                        Listener.onResponselanguageError("Terjadi Kesalahan Saat Mengolah Data");
                    }


                } else {
                    Listener.onResponselanguageError("Terjadi Kesalahan Saat Memuat Data");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Listener.onResponselanguageError("Terjadi Kesalahan Saat Menghubungi Server");
            }
        });


    }
}
