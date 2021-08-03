package br.com.alura.loja.teste;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeCategorias {

	public static void main(String[] args) {
		
			Categoria categoria1 = new Categoria("Celulares");
			Categoria categoria2 = new Categoria("Inform�tica");
			Categoria categoria3 = new Categoria("Livros");
			
			EntityManager em = JPAUtil.getEntityManager();
			CategoriaDao dao = new CategoriaDao(em);
			
			em.getTransaction().begin();
			em.persist(categoria1);//Se tivesse um frame, essa era a �nica linha de c�digo que ficaria
			em.persist(categoria2);
			em.persist(categoria3);
			em.getTransaction().commit();
			em.close();
		}

}