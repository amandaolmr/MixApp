package br.com.lanchonete.amanda.mixapp.dao;

import java.util.List;

import br.com.lanchonete.amanda.mixapp.modelo.Cliente;

public interface IClienteDAO {

    boolean salvarCliente(Cliente cliente);
    boolean atualizaCliente(Cliente cliente);
    boolean deletarCliente(Cliente cliente);
    List<Cliente>listarClientes();
}
