package com.jonesrandom.translateapp.translate;

import com.jonesrandom.translateapp.entity.Language;
import com.jonesrandom.translateapp.entity.Translate;

import java.util.List;

public interface TranslateContract {

    interface TranslateView {

        void onTextEmpty();

        void onLanguagesLoaded(Language language);

        void onLanguagesFailedToLoad(String error);

        void onTranslateSuccess(Translate translate);

        void onTranslateFailed(String error);

        void onShowLoading(boolean isShowing);
    }

    interface TranslatePresenter {
        void getLanguage();

        void getTextTranslate(String text, String langFrom, String langTo);

        void attachTranslateView(TranslateView view);

        void detachTranslateView();
    }
}
