package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Cliente;

public class ClienteDao {
	
	private EntityManager em;
	
	public ClienteDao (EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar (Cliente cliente) {
		this.em.persist(cliente);
	}
	
	public void atualizar (Cliente cliente) {
		this.em.merge(cliente);
	}
	
	public Cliente buscarClientePorId (Long id) {
		return this.em.find(Cliente.class, id);
	}

	public List<Cliente> buscarClientePorNome (String nome) {
		String jpql = "SELECT c FROM Cliente c WHERE c.nome = :nome";
		return this.em.createQuery(jpql, Cliente.class).setParameter("nome", nome).getResultList();
	}
	
	public Cliente buscarClientePorCpf (String cpf) {
		String jpql = "SELECT c FROM Cliente c WHERE c.cpf = :cpf";
		return this.em.createQuery(jpql, Cliente.class).setParameter("cpf", cpf).getSingleResult();
	}
}
