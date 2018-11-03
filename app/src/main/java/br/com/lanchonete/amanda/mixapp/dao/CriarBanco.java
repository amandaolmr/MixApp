package br.com.lanchonete.amanda.mixapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class CriarBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO =   "mixapp";
    private static  final int VERSAO = 1;

    public static final String CLIENTE_TABELA = "cliente";
    public static final String CLIENTE_ID = "id";
    public static final String CLIENTE_NOME =  "nome";
    public static final String CLIENTE_TELEFONE =  "telefone";
    public static final String CLIENTE_RUA =  "rua";
    public static final String CLIENTE_BAIRRO =  "bairro";


    public CriarBanco( Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder sqlCliente = new StringBuilder();
        sqlCliente.append("CREATE TABLE " + CLIENTE_TABELA + " (");
        sqlCliente.append(CLIENTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlCliente.append(CLIENTE_NOME + " VARCHAR(100) NOT NULL,");
        sqlCliente.append(CLIENTE_TELEFONE + " VARCHAR(100) NOT NULL,");
        sqlCliente.append(CLIENTE_RUA + " VARCHAR(100) NOT NULL,");
        sqlCliente.append(CLIENTE_BAIRRO+ " VARCHAR(100) NOT NULL,");

        db.execSQL(sqlCliente.toString());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder sqlClienteDROP = new StringBuilder();
        sqlClienteDROP.append("DROP TABLE IF EXITS "+ CLIENTE_TABELA + " ;");
        db.execSQL(sqlClienteDROP.toString());// destroi a tabela
        onCreate(db);// cria denovo a tabela do banco
    }
}
