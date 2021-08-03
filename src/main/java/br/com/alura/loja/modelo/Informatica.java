package br.com.alura.loja.modelo;

import javax.persistence.Entity;

public class Informatica extends Produto{

	private String Marca;
	private String Modelo;
	
	public String getMarca() {
		return Marca;
	}
	public void setMarca(String marca) {
		Marca = marca;
	}
	public String getModelo() {
		return Modelo;
	}
	public void setModelo(String modelo) {
		Modelo = modelo;
	}
	
}
