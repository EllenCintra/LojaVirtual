package br.com.alura.loja.modelo;

import javax.persistence.Embeddable;

@Embeddable//Embutível, vai fazer parte das entidades que usarem os dados pessoais, no caso, a entidade Cliente
public class DadosPessoais {

	private String nome;
	private String cpf;
	
	public DadosPessoais() {};//O construtor padrão é necessário para o Hibernate inicializar a entidade
	
	public DadosPessoais(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
