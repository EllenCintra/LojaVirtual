package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {

	private EntityManager em;
	
	public ProdutoDao (EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar (Produto produto) {
		this.em.persist(produto);
	}
	
	public void atualizar (Produto produto) {
		this.em.merge(produto);
	}
	
	public void remover (Produto produto) {
		produto = em.merge(produto);//Pra garantir que o produto esteja managed
		this.em.remove(produto);
	}
	
	public Produto bucarProdutoPorId (Long id) {
		return em.find(Produto.class, id);
	}
	
	public List<Produto> bucarProdutos(){
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();		
	}
	
	public List<Produto> bucarProdutoPorNome(String nome){
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();		
	}
	
	public List<Produto> buscarProdutoPorCategoria(String categoria){
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.categoria = :categoria";
		return em.createQuery(jpql, Produto.class).setParameter("categoria", categoria).getResultList();
	}
	
	public BigDecimal buscarPrecoDeProduto(String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult();
	}
	
	public List<Produto> buscarProdutoPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro){
		String jpql = "SELECT p FROM Produto p WHERE 1=1 ";
		if (nome != null && !nome.trim().isEmpty()) {	
			jpql = "AND p.nome = :nome";
		}
		if (preco != null) {
			jpql = "AND p.preco = :preco";
		}
		if (dataCadastro != null) {
			jpql = "AND p.dataCadastro = :dataCadastro";
		}
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		if (nome != null && !nome.trim().isEmpty()) {	
			query.setParameter("nome", nome);
		}
		if (preco != null) {
			query.setParameter("preco", preco);
		}
		if (dataCadastro != null) {
			query.setParameter("dataCadastro", dataCadastro);
		}
	}
}
























