package br.com.rafaelgodoy.contacorrentebackend.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class Lancamento implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dataLancamento;
	private String descricao;
	private Integer numero;
	private String situacao;
	private String dataConfirmacao;
	private DadosBancario dadosBancario;
	private BigDecimal valor;
}
