package br.com.rafaelgodoy.contacorrentebackend.restservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaelgodoy.contacorrentebackend.domain.Lancamento;
import br.com.rafaelgodoy.contacorrentebackend.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoServiceApi {
	
	@Autowired
	private LancamentoService service;
	
	@GetMapping
	public ResponseEntity<List<Lancamento>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(service.listarLancamentos());
	}
}
