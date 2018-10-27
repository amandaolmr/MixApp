package br.com.lanchonete.amanda.mixapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {

    Button btn_principal_clientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btn_principal_clientes = (Button)findViewById(R.id.btn_principal_clientes);
        btn_principal_clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, lista_clientes.class);
                startActivity(intent);
            }
        });
    }
}
