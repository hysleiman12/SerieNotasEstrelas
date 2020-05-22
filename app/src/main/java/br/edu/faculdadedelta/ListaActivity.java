package br.edu.faculdadedelta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.faculdadedelta.adapter.SerieAdapter;
import br.edu.faculdadedelta.dao.SeriadoDAO;
import br.edu.faculdadedelta.modelo.Seriado;

public class ListaActivity extends AppCompatActivity {

    private ListView lvListar;
    private SeriadoDAO dao = new SeriadoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvListar = findViewById(R.id.lvlista);

        lvListar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Seriado seriadoSelecionado = (Seriado) parent.getItemAtPosition(position);

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("seriadoSelecionado", seriadoSelecionado);
                startActivity(intent);

            }
        });

        lvListar.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Seriado seriadoSelecionado = (Seriado) parent.getItemAtPosition(position);
                dao.excluir(seriadoSelecionado);
                carregarLista();
                return false;
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
    private void carregarLista(){
        SerieAdapter adapter = new SerieAdapter(dao.listar(), getBaseContext());
        lvListar.setAdapter(adapter);
    }
    public void novo(View view){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);


    }


}
