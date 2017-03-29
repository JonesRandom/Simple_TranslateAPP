package com.jonesrandom.testranslateapi.Common;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jonesrandom.testranslateapi.ModelTranslate.ModelLanguage;
import com.jonesrandom.testranslateapi.R;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter{

    private List<ModelLanguage> data;
    private int rowLayout;

    public SpinnerAdapter(Context context, int resource, List<ModelLanguage> objects) {
        super(context, resource, objects);

        rowLayout = resource;
        data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View view, ViewGroup parent) {

        view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent ,false);

        TextView Bahasa = (TextView) view.findViewById(R.id.row_langs);
        TextView idBahasa = (TextView) view.findViewById(R.id.row_id);

        Bahasa.setText(data.get(position).getLangs());
        idBahasa.setText(data.get(position).getId());

        return view;
    }


}
