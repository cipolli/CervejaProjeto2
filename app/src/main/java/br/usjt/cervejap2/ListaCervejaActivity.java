package br.usjt.cervejap2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class ListaCervejaActivity extends ActionBarActivity {
    ListView listView;
    Activity atividade;
    public final static String CERVEJA = "br.usjt.CERVEJA";
    Cerveja[] cervejas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cerveja);
        atividade = this;

        Especialista especialista = new Especialista();
        //pega a mensagem do intent
        Intent intent = getIntent();
        String cor = intent.getStringExtra(MainActivity.COR);
        String pais = intent.getStringExtra(MainActivity.PAIS);
        String estilo = intent.getStringExtra(MainActivity.ESTILO);
        String modo = intent.getStringExtra(MainActivity.MODO);

        cervejas = especialista.listarMarcas(estilo, cor, pais).toArray(new Cerveja[0]);
        String[] lista = null;

        if(modo.equals(MainActivity.SIMPLES)) {
            lista = new String[cervejas.length];

            for (int i = 0; i < cervejas.length; i++) {
                lista[i] = cervejas[i].getNome();
            }
        }
        //cria a lista de cervejas
        listView = (ListView) findViewById(R.id.view_lista_cerveja);
        BaseAdapter adapter;
        if(modo.equals(MainActivity.SIMPLES)) {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, lista);
        } else {
            adapter = new CervejaAdapter(this, cervejas);
        }
        listView.setAdapter(adapter);

        // listener de click em um item do listview

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // manda para a tela de detalhe
                Intent intent = new Intent(atividade, DetalheCervejaActivity.class);
                intent.putExtra(CERVEJA, cervejas[position]);

                startActivity(intent);

            }

        });
    }

}