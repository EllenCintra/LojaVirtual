package br.com.alura.loja.modelo;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded//se fosse o id, seria @EmbeddedId
	private DadosPessoais dadosPessoais;
	
	public Cliente () {};//O construtor padrão é necessário para o Hibernate inicializar a entidade
	
	public Cliente (String nome, String cpf) {
		this.dadosPessoais = new DadosPessoais(nome, cpf);
	}

	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}

	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return this.dadosPessoais.getNome();
	}
	
	public String getCpf() {
		return this.dadosPessoais.getCpf();
	}

}
