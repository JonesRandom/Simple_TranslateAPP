package com.jonesrandom.translateapp.entity;


import java.util.List;

public class Language {

    private List<LanguageItem> languages;

    public Language(List<LanguageItem> languages) {
        this.languages = languages;
    }

    public List<LanguageItem> getLanguages() {
        return languages;
    }
}
