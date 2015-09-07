package br.usjt.cervejap2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class DetalheCervejaActivity extends ActionBarActivity {
    TextView cervejaNome;
    ImageView cervejaImageView;
    TextView cervejaPreco;
    TextView cervejaEstilo;
    TextView cervejaCor;
    TextView cervejaPais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_cerveja);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(ListaCervejaActivity.CERVEJA);
        Cerveja cerveja = (Cerveja)obj;
        setupViews(cerveja);

    }

    private void setupViews(Cerveja cerveja) {
        cervejaNome = (TextView) findViewById(R.id.txt_cerveja_nome);
        cervejaNome.setText(cerveja.getNome());
        cervejaImageView = (ImageView) findViewById(R.id.cerveja_image_view);
        Drawable drawable = Util.getDrawable(this, cerveja.getImagem());
        cervejaImageView.setImageDrawable(drawable);
        cervejaPreco = (TextView) findViewById(R.id.txt_cerveja_preco);
        Locale locale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        cervejaPreco.setText(""+formatter.format(cerveja.getPreco()));
        cervejaEstilo = (TextView) findViewById(R.id.txt_cerveja_estilo);
        cervejaEstilo.setText(cerveja.getEstilo());
        cervejaCor = (TextView) findViewById(R.id.txt_cerveja_cor);
        cervejaCor.setText(cerveja.getCor());
        cervejaPais = (TextView) findViewById(R.id.txt_cerveja_pais);
        cervejaPais.setText(cerveja.getPais());
    }

}
