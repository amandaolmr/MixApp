package br.com.lanchonete.amanda.mixapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Amanda Miranda de miranda
public class MainActivity extends AppCompatActivity {

    Button btn_main_entrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_main_entrar = (Button) findViewById(R.id.btn_main_entrar);
        btn_main_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Principal.class);
                startActivity(intent);
            }
        });

    }
}
