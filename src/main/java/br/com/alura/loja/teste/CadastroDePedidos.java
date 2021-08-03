package br.com.alura.loja.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedidos {

	public static void main(String[] args) {
		
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		Produto produtoCelular = produtoDao.bucarProdutoPorId(1l);
		Produto produtoVideogame = produtoDao.bucarProdutoPorId(2l);
		Produto produtoMacbook = produtoDao.bucarProdutoPorId(3l);
		Cliente cliente = clienteDao.buscarClientePorId(1l);
		
		em.getTransaction().begin();
		
		Pedido pedido1 = new Pedido (cliente);
		pedido1.adicionarItem(new ItemPedido(pedido1, produtoCelular, 3));
		pedido1.adicionarItem(new ItemPedido(pedido1, produtoVideogame, 1));
		pedido1.adicionarItem(new ItemPedido(pedido1, produtoMacbook, 2));
		
		Pedido pedido2 = new Pedido (cliente);
		pedido2.adicionarItem(new ItemPedido(pedido1, produtoCelular, 1));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido1);
		pedidoDao.cadastrar(pedido2);
		
		em.getTransaction().commit();
		
		
		System.out.println("VALOR TOTAL PEDIDO 1: " + pedido1.getValorTotal());
		System.out.println("VALOR TOTAL PEDIDO 2: " + pedido2.getValorTotal());
		System.out.println("VALOR TOTAL VENDIDO: " + pedidoDao.valorTotalVendido());
		
		List <RelatorioDeVendasVo> relatorioVendas = pedidoDao.relatorioDeVendas();
		relatorioVendas.forEach(System.out::println);
		em.close();
		
		System.out.println(pedido1.getCliente().getNome());
	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("8000"), videogames);
		Produto macbook = new Produto("Macbook", "Macboo pro retina", new BigDecimal("14000"), informatica);
		
		Cliente cliente = new Cliente("Rodrigo", "123456");
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);
		
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(macbook);
		
		clienteDao.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}
}
