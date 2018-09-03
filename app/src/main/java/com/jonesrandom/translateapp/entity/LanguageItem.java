package com.jonesrandom.translateapp.entity;

public class LanguageItem {

    private String id;
    private String country;

    public LanguageItem(String id, String country) {
        this.id = id;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public String getId() {
        return id;
    }
}
