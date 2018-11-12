package br.com.lanchonete.amanda.mixapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.lanchonete.amanda.mixapp.R;
import br.com.lanchonete.amanda.mixapp.dao.ClienteDAO;
import br.com.lanchonete.amanda.mixapp.modelo.Cliente;

public class lista_clientes extends AppCompatActivity {
   // Button btnNovoCadastro;
    ListView listaVisivelClientes;
    Cliente cliente;
    ClienteDAO clienteDAO;
    ArrayList<Cliente>arrayListCliente;
    ArrayAdapter<Cliente>arrayAdapterCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        listaVisivelClientes = (ListView)findViewById(R.id.listClientes);
        registerForContextMenu(listaVisivelClientes);

      //  btnNovoCadastro = (Button)findViewById(R.id.btnNovoCadastro);
      //  btnNovoCadastro.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View v) {
        //        Intent intent = new Intent(lista_clientes.this, FormularioCliente.class);
        //        startActivity(intent);
          //  }
        //});

        listaVisivelClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente clienteEnviado  = (Cliente) arrayAdapterCliente.getItem(position);
                Intent intent = new Intent(lista_clientes.this, FormularioCliente.class);
                intent.putExtra("cliente-enviado",clienteEnviado);
                startActivity(intent);
            }
        });

        listaVisivelClientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                cliente = arrayAdapterCliente.getItem(position);

                return false;
            }
        });
    }

    public  void populaLista(){
        clienteDAO = new ClienteDAO(lista_clientes.this);
        arrayListCliente = clienteDAO.listarCliente();
        clienteDAO.close();

        if(listaVisivelClientes != null){
            arrayAdapterCliente = new ArrayAdapter<>(lista_clientes.this, android.R.layout.simple_list_item_1,
                    arrayListCliente);

            listaVisivelClientes.setAdapter(arrayAdapterCliente);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_cliente, menu);
        MenuItem item = menu.findItem(R.id.acao_busca);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(buscaTexto());

        return super.onCreateOptionsMenu(menu);
    }

    private SearchView.OnQueryTextListener buscaTexto() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(lista_clientes.this, ""+newText, Toast.LENGTH_SHORT).show();
                //carregaDadosLike(newText);
                return false;
            }
        };
    }
private void carregaDadosLike(String nome){

        ClienteDAO clienteDAO = new ClienteDAO(this);
        //cliente = clienteDAO.listarClienteLike(nome);
        listaVisivelClientes.setAdapter(arrayAdapterCliente);


}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.acao_adiciona){
            Intent intent = new Intent(lista_clientes.this, FormularioCliente.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        populaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem mDelete = menu.add("Deletar Cliente");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                long retornoDB;
                clienteDAO = new ClienteDAO(lista_clientes.this);
                retornoDB = clienteDAO.excluirCliente(cliente);
                clienteDAO.close();
                if(retornoDB == -1){
                    alerta("Erro ao Excluir Cliente!");
                }else{
                    alerta("Alterado com Sucesso!");
                }
                populaLista();

                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    private void alerta(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
