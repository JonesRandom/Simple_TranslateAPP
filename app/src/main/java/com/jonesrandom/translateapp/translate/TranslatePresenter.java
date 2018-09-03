package com.jonesrandom.translateapp.translate;

import com.jonesrandom.translateapp.entity.Language;
import com.jonesrandom.translateapp.entity.LanguageItem;
import com.jonesrandom.translateapp.entity.Translate;
import com.jonesrandom.translateapp.repository.TranslateRepositoryCallback;
import com.jonesrandom.translateapp.repository.TranslateRepositoryImpl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;

public class TranslatePresenter implements TranslateContract.TranslatePresenter {

    private TranslateContract.TranslateView translateView;
    private TranslateRepositoryImpl translateRepository;

    protected TranslatePresenter(TranslateRepositoryImpl translateRepository) {
        this.translateRepository = translateRepository;
    }

    @Override
    public void attachTranslateView(TranslateContract.TranslateView view) {
        translateView = view;
    }

    @Override
    public void detachTranslateView() {
        translateView = null;
    }

    @Override
    public void getLanguage() {
        translateView.onShowLoading(true);
        translateRepository.getLanguage(new TranslateRepositoryCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody data) {
                translateView.onShowLoading(false);
                List<String> countryId = new ArrayList<>();
                List<LanguageItem> languages = new ArrayList<>();

                try {
                    String raw = data.string();

                    JSONObject response = new JSONObject(raw);
                    JSONObject language = response.getJSONObject("langs");

                    Iterator<String> iterable = language.keys();

                    while (iterable.hasNext()) {
                        String id = iterable.next();
                        countryId.add(id);
                    }

                    for (String s : countryId) {
                        String country = (String) language.get(s);

                        LanguageItem languageItem = new LanguageItem(s, country);
                        languages.add(languageItem);
                    }

                    Collections.sort(languages, new Comparator<LanguageItem>() {
                        @Override
                        public int compare(LanguageItem o1, LanguageItem o2) {
                            return o1.getCountry().compareTo(o2.getCountry());
                        }
                    });

                    translateView.onLanguagesLoaded(new Language(languages));
                } catch (IOException | JSONException e) {
                    translateView.onLanguagesFailedToLoad("Tidak dapat memuat data");
                }
            }

            @Override
            public void onFailed(String error) {
                translateView.onShowLoading(false);
                translateView.onLanguagesFailedToLoad(error);
            }
        });
    }

    @Override
    public void getTextTranslate(String text, String langFrom, String langTo) {

        if (text.isEmpty()){
            translateView.onTextEmpty();
            return;
        }

        translateRepository.translate(text, langFrom, langTo, new TranslateRepositoryCallback<Translate>() {
            @Override
            public void onSuccess(Translate data) {
                if (data.getCode() == 200){
                    translateView.onTranslateSuccess(data);
                } else {
                    translateView.onTranslateFailed("Tidak dapat menerjemahkan");
                }
            }

            @Override
            public void onFailed(String error) {
                translateView.onTranslateFailed(error);
            }
        });
    }

}
