package br.com.alura.loja.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProdutos {

	public static void main(String[] args) {
	
		EntityManager em = JPAUtil.getEntityManager();
		
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		Categoria categoria1 = new Categoria("Celulares");
		Categoria categoria2 = new Categoria("Informática");
		Categoria categoria3 = new Categoria("Livros");
			
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		//Simulando um usuário
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("1100"), categoria1);
		Produto celular2 = new Produto("Xiaomi Redmi 2", "Muito legal", new BigDecimal("1400"), categoria1);		
		
		Produto mouse = new Produto ("Mouse", "Mouse sem fio", new BigDecimal("100"), categoria2);
		Produto notebook = new Produto ("Notebook", "Notebook DELL I7", new BigDecimal("3300"), categoria2);
		
		Produto livro = new Produto ("A culpa é das estrelas", "Romance", new BigDecimal("75"), categoria3);
		
		ProdutoDao dao = new ProdutoDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(categoria1);
		categoriaDao.cadastrar(categoria2);
		categoriaDao.cadastrar(categoria3);
		
		produtoDao.cadastrar(celular2);
		produtoDao.cadastrar(livro);
		produtoDao.cadastrar(mouse);
		produtoDao.cadastrar(notebook);
		
		em.getTransaction().commit();
		
		//Buscar produtos
		Produto produtoBusca1 = produtoDao.bucarProdutoPorId(1l);
		System.out.println(produtoBusca1.getNome());
				
		List<Produto> produtosBusca2 = produtoDao.bucarProdutoPorNome("Mouse");
		produtosBusca2.forEach(produto -> System.out.println(produto.getNome() + ", " + produto.getDescricao()));
		
		List<Produto> produtosBusca3 = produtoDao.buscarProdutoPorCategoria("Informática");
		produtosBusca3.forEach(produto -> System.out.println(produto.getNome()));
		
		System.out.println(produtoDao.buscarPrecoDeProduto("Mouse"));
				
		em.close();
		
	}
}
