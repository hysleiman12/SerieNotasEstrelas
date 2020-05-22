package br.edu.faculdadedelta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.faculdadedelta.dao.SeriadoDAO;
import br.edu.faculdadedelta.modelo.Seriado;

public class MainActivity extends AppCompatActivity {

    private EditText etGenero;
    private EditText etStatus;
    private EditText etNome;
    private EditText etComentario;
   // private EditText etNota;
    private RatingBar etNota;
    private EditText etDataLan;
    private Seriado seriado = new Seriado();
    private SeriadoDAO dao = new SeriadoDAO();
    Date dataFormat = null;
    Date atual = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        etNota = (RatingBar) findViewById(R.id.ratingBar2);
        etNota.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(),String.valueOf(rating),Toast.LENGTH_LONG).show();
            }
        });
        etGenero = findViewById(R.id.etGenero);
        etStatus = findViewById(R.id.etStatus);
        etNome = findViewById(R.id.etNome);
        etComentario = findViewById(R.id.etComentario);
        //  etNota = findViewById(R.id.etNota);
        etNota = findViewById(R.id.ratingBar2);
        etDataLan = findViewById(R.id.etDataLan);

        Intent intent = getIntent();

        if (intent != null) {
            Seriado seriadoSelecionado = (Seriado) intent.getSerializableExtra("seriadoSelecionado");
            if(seriadoSelecionado != null){
                pupularFormulario(seriadoSelecionado);

            }


        }

    }
    private void pupularFormulario(Seriado seriadoSelecionado){
        etGenero.setText(seriadoSelecionado.getGenero());
        etStatus.setText(seriadoSelecionado.getStatus());
        etNome.setText(seriadoSelecionado.getNome());
        etComentario.setText(seriadoSelecionado.getComentario());
       // etNota.setText(String.valueOf(seriadoSelecionado.getNota()));
        etNota.setNumStars(seriado.getNota());
        etDataLan.setText(format.format(seriadoSelecionado.getDataLancamento()));
        seriado.setId(seriadoSelecionado.getId());
    }
    private void pupularModelo(){
        seriado.setGenero(etGenero.getText().toString());
        seriado.setStatus(etStatus.getText().toString());
        seriado.setNome(etNome.getText().toString());
        seriado.setComentario(etComentario.getText().toString());
        //seriado.setNota(Integer.parseInt(etNota.get.toString()));
        seriado.setNota(etNota.getProgress());

        try {
            dataFormat = format.parse(etDataLan.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        seriado.setDataLancamento(dataFormat);


    }
    public void salvar(View view){

     /*   String paramGenero = etGenero.getText().toString();
        String paramStatus = etStatus.getText().toString();
        String paramNome = etNome.getText().toString();
        String paramComentario = etComentario.getText().toString();
        String  paramNota = etNota.toString();
        String paramDataLan = etDataLan.getText().toString();
*/
        try {
            dataFormat = format.parse(etDataLan.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
       /* if(paramGenero.equals("") && paramStatus.equals("") && paramNome.equals("")
                && paramComentario.equals("") && paramNota.equals("")&& dataFormat == null){

            Toast.makeText(getBaseContext(),
                    "Todos os campos estão vazios!", Toast.LENGTH_LONG).show();

        }


         if(paramGenero.equals("") || paramStatus.equals("") || paramNome.equals("")

                || paramComentario.equals("") || paramNota.equals("") || dataFormat == null){

            if(paramGenero.equals("")){

                Toast.makeText(getBaseContext(),
                        "O campo genero não pode estar vazio!", Toast.LENGTH_LONG).show();

            }else if(paramStatus.equals("")){

                Toast.makeText(getBaseContext(),
                        "O campo statu não pode estar vazio!", Toast.LENGTH_LONG).show();

            }else if(paramNome.equals("")){

                Toast.makeText(getBaseContext(),
                        "O campo nome não pode estar vazio!", Toast.LENGTH_LONG).show();

            }else if(paramComentario.equals("")){

                Toast.makeText(getBaseContext(),
                        "O campo comentario não pode estar vazio!", Toast.LENGTH_LONG).show();

            }else if(paramNota.equals("")){

                Toast.makeText(getBaseContext(),
                        "O campo nota não pode estar vazio!", Toast.LENGTH_LONG).show();

            }else if(dataFormat == null){
                Toast.makeText(getBaseContext(),
                        "O campo data não pode está vazio!", Toast.LENGTH_LONG).show();

            }

        }else if(dataFormat.after(atual)) {
            Toast.makeText(getBaseContext(),
                    "O campo data não pode ser maior que a data atual!", Toast.LENGTH_LONG).show();
        } else { */
     String mensagemValidacao = validarCampos();
           if(mensagemValidacao.isEmpty()) {
               pupularModelo();
               if (seriado.getId() == null) {

                   dao.incluir(seriado);

                   Toast.makeText(getBaseContext(),
                           "Inclusão realizada com sucesso!", Toast.LENGTH_LONG).show();
                   limparCampos();


               } else {
                   dao.alterar(seriado);
                   Toast.makeText(getBaseContext(),
                           "Alteração realizada com sucesso!", Toast.LENGTH_LONG).show();
                   limparCampos();
               }
           }else {
               Toast.makeText(getBaseContext(),  mensagemValidacao, Toast.LENGTH_LONG).show();
           }
       // }

    }
    private String validarCampos(){
        String resposta = "";

        if (etGenero.getText().toString().isEmpty()) {
            resposta = "O campo nome não foi preenchido!";
        }
        if (etStatus.getText().toString().isEmpty()) {
            resposta += "\nO campo genero não foi preenchido!";
        }
        if (etNome.getText().toString().isEmpty()) {
            resposta += "\nO campo status não foi preenchido!";
        }
        if (etComentario.getText().toString().isEmpty()) {
            resposta += "\nO campo comentário não foi preenchido!";
        }

        if (etNota.toString().isEmpty()) {
            resposta += "\nO campo nota não foi preechido!";
        }
        if (etDataLan.getText().toString().isEmpty()) {
            resposta += "\nO campo data não foi preenchido!";
        }
            if(dataFormat.after(atual)){
              resposta += "Data não pode ser maior que a data atual";

            
        }
        return resposta;
    }



    private void limparCampos(){
        etGenero.setText("");
        etStatus.setText("");
        etNome.setText("");
        etComentario.setText("");
       // etNota.setText("");
        etNota.setProgress(0);
        etDataLan.setText("");
        seriado = new Seriado();

    }
    public void limpar(View view){
        limparCampos();

    }

   public void listar(View view){
      /* Intent intent = new Intent(getBaseContext(), ListaActivity.class);
       startActivity(intent);

       */
      finish();

   }


}
