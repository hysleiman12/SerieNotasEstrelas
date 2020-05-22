package br.edu.faculdadedelta.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.Seriado;

public class SeriadoDAO {

    private static List<Seriado> listaSeriado = new ArrayList<>();
    private static Long idGerador = 1L;

    public void incluir(Seriado seriado){
        seriado.setId(idGerador++);
        listaSeriado.add(seriado);

    }
    public void excluir(Seriado seriado){
        listaSeriado.remove(seriado);

    }
    public List<Seriado> listar(){

        return listaSeriado;
    }
    public void alterar(Seriado seriado){
        for (Seriado seriado1: listaSeriado) {
            long idSeriado = seriado.getId();
            long idSeriado1 = seriado1.getId();
            if(idSeriado == idSeriado1){
                listaSeriado.remove(seriado1);
                listaSeriado.add(seriado);
                break;

            }

        }
    }


}
