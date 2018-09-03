package com.jonesrandom.translateapp.translate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jonesrandom.translateapp.R;
import com.jonesrandom.translateapp.common.MySoftKey;
import com.jonesrandom.translateapp.entity.Language;
import com.jonesrandom.translateapp.entity.Translate;
import com.jonesrandom.translateapp.repository.TranslateRepositoryImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TranslateActivity extends AppCompatActivity implements TranslateContract.TranslateView {

    private TranslatePresenter translatePresenter;
    private Language language;

    @BindView(R.id.errorLayout)
    LinearLayout errorLayout;

    @BindView(R.id.scrollContent)
    ScrollView scrollContent;

    @BindView(R.id.progress)
    ProgressBar progress;

    @BindView(R.id.spinFrom)
    Spinner spLanguageFrom;

    @BindView(R.id.spinTo)
    Spinner spLanguageTo;

    @BindView(R.id.etTranslate)
    EditText etTranslate;

    @BindView(R.id.tvTranslate)
    TextView tvTranslate;

    @BindView(R.id.outputDari)
    TextView tvFrom;

    @BindView(R.id.outputKe)
    TextView tvTo;

    @BindView(R.id.textError)
    TextView tvError;

    @OnClick({R.id.btnTerjemahan , R.id.btnRefresh})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTerjemahan:

                String text = etTranslate.getText().toString();
                String from = language.getLanguages().get(spLanguageFrom.getSelectedItemPosition()).getId();
                String to = language.getLanguages().get(spLanguageTo.getSelectedItemPosition()).getId();

                MySoftKey.hide(etTranslate);

                translatePresenter.getTextTranslate(text, from, to);
                break;
            case R.id.btnRefresh:
                errorLayout.setVisibility(View.GONE);

                translatePresenter.getLanguage();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        ButterKnife.bind(this);

        translatePresenter = new TranslatePresenter(new TranslateRepositoryImpl());
        spLanguageFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvFrom.setText(language.getLanguages().get(position).getCountry());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spLanguageTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvTo.setText(language.getLanguages().get(position).getCountry());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        translatePresenter.attachTranslateView(this);
        translatePresenter.getLanguage();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        translatePresenter.detachTranslateView();
    }

    @Override
    public void onTextEmpty() {
        Toast.makeText(this , "Masukkan kata untuk diterjemahkan" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLanguagesLoaded(Language language) {
        this.language = language;
        TranslateLanguageAdapter languageAdapter = new TranslateLanguageAdapter(this, R.layout.spinner_language_item, language.getLanguages());

        spLanguageFrom.setAdapter(languageAdapter);
        spLanguageTo.setAdapter(languageAdapter);

        scrollContent.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
    }

    @Override
    public void onLanguagesFailedToLoad(String error) {
        errorLayout.setVisibility(View.VISIBLE);

        tvError.setText(error);
    }

    @Override
    public void onTranslateSuccess(Translate translate) {
        tvTranslate.setText(translate.getText());
    }

    @Override
    public void onTranslateFailed(String error) {
        Toast.makeText(this , error , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowLoading(boolean isShowing) {
        progress.setVisibility(isShowing ? View.VISIBLE : View.GONE);
    }
}
