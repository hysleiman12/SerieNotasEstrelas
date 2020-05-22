package br.edu.faculdadedelta.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.edu.faculdadedelta.R;
import br.edu.faculdadedelta.modelo.Seriado;



public class SerieAdapter extends BaseAdapter {

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private List<Seriado> listaSeriado;
    private Context context;

    public SerieAdapter(List<Seriado> listaSeriado, Context context) {
        this.listaSeriado = listaSeriado;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaSeriado.size();
    }

    @Override
    public Object getItem(int position) {
        return listaSeriado.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Seriado seriado = (Seriado) getItem(position);

        View viewRetorno = View.inflate(context, R.layout.item_lista, null);

        TextView tvGenero = viewRetorno.findViewById(R.id.tvGenero);
        tvGenero.setText("Genero : "+ seriado.getGenero());

        TextView tvStatus = viewRetorno.findViewById(R.id.tvStatus);
        tvStatus.setText("Status : "+ seriado.getStatus());

        TextView tvNome = viewRetorno.findViewById(R.id.tvNota);
        tvNome.setText("Genero : "+ seriado.getNome());

        TextView tvComentario = viewRetorno.findViewById(R.id.tvComentario);
        tvComentario.setText("Genero : "+ seriado.getComentario());

        /*TextView tvNota = viewRetorno.findViewById(R.id.ratingBar2);
        tvNota.setText("Nota: "+ seriado.getNota());


         */
      TextView tvNota = viewRetorno.findViewById(R.id.tvNota);
      tvNota.setText(String.format("Nota: %d", seriado.getNota()));



        TextView tvDataLan = viewRetorno.findViewById(R.id.tvDataLan);

        tvDataLan.setText("Data lançamento : "+ format.format(seriado.getDataLancamento()));




        return viewRetorno;
    }
}
