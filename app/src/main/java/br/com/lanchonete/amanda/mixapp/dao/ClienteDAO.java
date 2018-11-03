package br.com.lanchonete.amanda.mixapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.lanchonete.amanda.mixapp.modelo.Cliente;

public class ClienteDAO implements IClienteDAO {

    private SQLiteDatabase db;
    private CriarBanco criarBanco;

    public ClienteDAO (Context context){
        criarBanco = new CriarBanco(context);
    }

    @Override
    public boolean salvarCliente(Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put(criarBanco.CLIENTE_NOME, cliente.getNome());
        values.put(criarBanco.CLIENTE_TELEFONE, cliente.getTelefone());
        values.put(criarBanco.CLIENTE_RUA, cliente.getRua());
        values.put(criarBanco.CLIENTE_BAIRRO, cliente.getBairro());

        try {

            db = criarBanco.getWritableDatabase();
            db.insert(criarBanco.CLIENTE_TABELA, null, values);

        }catch (SQLException e){
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizaCliente(Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put(criarBanco.CLIENTE_NOME, cliente.getNome());
        values.put(criarBanco.CLIENTE_TELEFONE, cliente.getTelefone());
        values.put(criarBanco.CLIENTE_RUA, cliente.getRua());
        values.put(criarBanco.CLIENTE_BAIRRO, cliente.getBairro());

        String where = criarBanco.CLIENTE_ID + " = ?";
        String[] args = {cliente.getId().toString()};

        try {

            db = criarBanco.getWritableDatabase();
            db.update(criarBanco.CLIENTE_TABELA, values, where, args);

        }catch (SQLException e){
            return  false;
        }
        return true;
    }

    @Override
    public boolean deletarCliente(Cliente cliente) {
        String where = criarBanco.CLIENTE_ID + " = ?";
        String[] args = {cliente.getId().toString()};

        try {
            db = criarBanco.getWritableDatabase();
            db.delete(criarBanco.CLIENTE_TABELA, where, args);
        }catch (SQLException e){
            return false;
        }

        return true;
    }

    @Override
    public List<Cliente> listarClientes() {

        List<Cliente>clientes = new ArrayList<>();
        String sqlConsulta = "SELECT *FROM "+ criarBanco.CLIENTE_TABELA + " ;";
        db = criarBanco.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlConsulta, null);

        while (cursor.moveToNext()){
            Integer id = cursor.getInt(cursor.getColumnIndex(criarBanco.CLIENTE_ID));
            String nome = cursor.getString(cursor.getColumnIndex(criarBanco.CLIENTE_NOME));
            String telefone = cursor.getString(cursor.getColumnIndex(criarBanco.CLIENTE_TELEFONE));
            String rua = cursor.getString(cursor.getColumnIndex(criarBanco.CLIENTE_RUA));
            String bairro = cursor.getString(cursor.getColumnIndex(criarBanco.CLIENTE_BAIRRO));

            Cliente cliente = new Cliente(id,nome, telefone, rua, bairro);
            clientes.add(cliente);
        }
        return clientes;
    }
}
