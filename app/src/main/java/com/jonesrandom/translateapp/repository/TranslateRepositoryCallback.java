package com.jonesrandom.translateapp.repository;

public interface TranslateRepositoryCallback<T> {
    void onSuccess(T data);

    void onFailed(String error);
}
