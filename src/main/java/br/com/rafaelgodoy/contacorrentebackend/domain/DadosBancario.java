package br.com.rafaelgodoy.contacorrentebackend.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class DadosBancario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeBanco;
	private Integer banco;
	private Integer agencia;
	private Long contaCorrente;
}
