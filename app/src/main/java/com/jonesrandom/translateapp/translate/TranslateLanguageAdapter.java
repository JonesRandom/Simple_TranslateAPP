package com.jonesrandom.translateapp.translate;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jonesrandom.translateapp.R;
import com.jonesrandom.translateapp.entity.LanguageItem;

import java.util.List;

public class TranslateLanguageAdapter extends ArrayAdapter{

    private List<LanguageItem> data;
    private int rowLayout;

    protected TranslateLanguageAdapter(Context context, int resource, List<LanguageItem> objects) {
        super(context, resource, objects);
        rowLayout = resource;
        data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, parent);
    }

    private View getCustomView(int position, ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);

        TextView tvCountry = view.findViewById(R.id.row_langs);
        TextView tvIdCountry =  view.findViewById(R.id.row_id);

        tvCountry.setText(data.get(position).getCountry());
        tvIdCountry.setText(data.get(position).getId());

        return view;
    }


}
