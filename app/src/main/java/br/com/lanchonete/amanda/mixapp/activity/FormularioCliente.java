package br.com.lanchonete.amanda.mixapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.lanchonete.amanda.mixapp.R;
import br.com.lanchonete.amanda.mixapp.dao.ClienteDAO;
import br.com.lanchonete.amanda.mixapp.modelo.Cliente;

public class FormularioCliente extends AppCompatActivity {
    EditText editNome, editTelefone, editRua, editBairro;
    Button btnVariavelCliente;
    Cliente cliente, altCliente;
    ClienteDAO clienteDAO;
    long retornoDB;// quando da certo ou noa a conexao com o banco e salvar o regiatro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cliente);

        Intent intent = getIntent();
        altCliente = (Cliente) intent.getSerializableExtra("cliente-enviado");
        cliente = new Cliente();
        clienteDAO = new ClienteDAO(FormularioCliente.this);

        editNome = (EditText)findViewById(R.id.editNome);
        editTelefone = (EditText)findViewById(R.id.editTelefone);
        editRua = (EditText)findViewById(R.id.editRua);
        editBairro = (EditText)findViewById(R.id.editBairro);
        btnVariavelCliente = (Button)findViewById(R.id.btnVariavelCliente);


        if(altCliente != null){
            btnVariavelCliente.setText("Alterar");
            editNome.setText(altCliente.getNome());
            editTelefone.setText(altCliente.getTelefone());
            editRua.setText(altCliente.getRua());
            editBairro.setText(altCliente.getBairro());

            cliente.setId(altCliente.getId());

        }else{
            btnVariavelCliente.setText("Salvar");
        }

        btnVariavelCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cliente.setNome(editNome.getText().toString());
                cliente.setTelefone(editTelefone.getText().toString());
                cliente.setRua(editRua.getText().toString());
                cliente.setBairro (editBairro.getText().toString());
                clienteDAO.close();

                if(btnVariavelCliente.getText().toString().equals("Salvar")){
                    retornoDB = clienteDAO.salvarCliente(cliente);
                   if(retornoDB == -1){
                       alerta("Erro ao Cadastrar Cliente");
                   }else{
                       alerta("Cadastrado com Sucesso!");
                   }

                }else{
                    //alterar
                    retornoDB = clienteDAO.alterarCliente(cliente);
                    clienteDAO.close();

                    if(retornoDB == -1){
                        alerta("Erro ao alterar Cliente");
                    }else{
                        alerta("Alterado com Sucesso!");
                    }

                }
                finish();
            }
        });
    }

    private  void alerta(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
