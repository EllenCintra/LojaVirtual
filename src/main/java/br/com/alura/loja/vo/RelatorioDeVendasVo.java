package br.com.alura.loja.vo;

import java.time.LocalDate;

//Classe de valor, não é uma entidade. Só tem atributos e os getters.
public class RelatorioDeVendasVo {

	private String nomeProduto;
	private Long quantidadeVendida;//sum devolve um long
	private LocalDate dataUltimaVenda;
	
	public RelatorioDeVendasVo(String nomeProduto, Long quantidadeVendida, LocalDate dataUltimaVenda) {
		this.nomeProduto = nomeProduto;
		this.quantidadeVendida = quantidadeVendida;
		this.dataUltimaVenda = dataUltimaVenda;
	}
	
	@Override
	public String toString() {
		return "RelatorioDeVendasVo [Nome do produto: " + nomeProduto + ", Quantidade Vendida: " + quantidadeVendida
				+ ", Data da última venda: " + dataUltimaVenda + "]";
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public Long getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public LocalDate getDataUltimaVenda() {
		return dataUltimaVenda;
	}
	
}
