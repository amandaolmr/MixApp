package br.com.lanchonete.amanda.mixapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.lanchonete.amanda.mixapp.modelo.Cliente;

public class ClienteDAO extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "mixapp";
    private static  final int VERSAO = 1;

    private static final String CLIENTE_TABELA = "cliente";
    private static final String CLIENTE_ID = "id";
    private static final String CLIENTE_NOME = "nome";
    private static final String CLIENTE_TELEFONE = "telefone";
    private static final String CLIENTE_RUA = "rua";
    private static final String CLIENTE_BAIRRO = "bairro";


    public ClienteDAO( Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sqlCliente = new StringBuilder();
        sqlCliente.append("CREATE TABLE "+ CLIENTE_TABELA+ " (" );
        sqlCliente.append( CLIENTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlCliente.append(CLIENTE_NOME + " TEXT, ");
        sqlCliente.append(CLIENTE_TELEFONE + " TEXT, ");
        sqlCliente.append(CLIENTE_RUA + " TEXT, ");
        sqlCliente.append(CLIENTE_BAIRRO + " TEXT ");
        sqlCliente.append(" ); ");

        db.execSQL(sqlCliente.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder sqlCliente = new StringBuilder();
        sqlCliente.append("DROP TABLE IF EXISTS " + CLIENTE_TABELA);
        db.execSQL(sqlCliente.toString());
        onCreate(db);

    }

     public long salvarCliente(Cliente cli){
        ContentValues values = new ContentValues();
        long retornoDB;// retorno do banco

         values.put(CLIENTE_NOME, cli.getNome());
         values.put(CLIENTE_TELEFONE, cli.getTelefone());
         values.put(CLIENTE_RUA, cli.getRua());
         values.put(CLIENTE_BAIRRO, cli.getBairro());

         retornoDB = getWritableDatabase().insert(CLIENTE_TABELA, null, values);


        return retornoDB;
     }

    public long alterarCliente(Cliente cli){
        ContentValues values = new ContentValues();
        long retornoDB;// retorno do banco

        values.put(CLIENTE_NOME, cli.getNome());
        values.put(CLIENTE_TELEFONE, cli.getTelefone());
        values.put(CLIENTE_RUA, cli.getRua());
        values.put(CLIENTE_BAIRRO, cli.getBairro());

        String [] args = {String.valueOf(cli.getId())};

        retornoDB = getWritableDatabase().update(CLIENTE_TABELA, values, " id = ?", args);


        return retornoDB;
    }

    public long excluirCliente(Cliente cli){
        long retornoDB;

        String [] args = {String.valueOf(cli.getId())};

        retornoDB = getWritableDatabase().delete(CLIENTE_TABELA,CLIENTE_ID +"=?", args);

        return  retornoDB;
    }

     public  ArrayList<Cliente> listarCliente(){
        String[] colunas = {CLIENTE_ID, CLIENTE_NOME, CLIENTE_TELEFONE, CLIENTE_RUA, CLIENTE_BAIRRO};
        Cursor cursor = getWritableDatabase().query(CLIENTE_TABELA, colunas, null, null, null, null, "upper (nome)", null);

        ArrayList<Cliente>listaClientes = new ArrayList<>();
        while(cursor.moveToNext()){
            Cliente cliente = new Cliente();

            cliente.setId(cursor.getInt(0));
            cliente.setNome(cursor.getString(1));
            cliente.setTelefone(cursor.getString(2));
            cliente.setRua(cursor.getString(3));
            cliente.setBairro(cursor.getString(4));

            listaClientes.add(cliente);
        }

        return listaClientes;
     }




     /*ublic List<Cliente>listarClienteLike(String nome){
         String[] colunas = {CLIENTE_NOME};
         Cursor cursor = getWritableDatabase().query(CLIENTE_TABELA, colunas, null, null, null, null, "upper (nome)", null);

         ArrayList<Cliente>listaClientes = new ArrayList<>();
         while(cursor.moveToNext()){
             Cliente cliente = new Cliente();

             cliente.setId(cursor.getInt(0));
             cliente.setNome(cursor.getString(1));


             listaClientes.add(cliente);
         }
    return listaClientes;
     }
     */
}
