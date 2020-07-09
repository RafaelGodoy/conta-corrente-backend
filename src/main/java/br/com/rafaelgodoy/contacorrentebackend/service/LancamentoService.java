package br.com.rafaelgodoy.contacorrentebackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rafaelgodoy.contacorrentebackend.domain.Lancamento;
import br.com.rafaelgodoy.contacorrentebackend.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository repository;
	
	public List<Lancamento> listarLancamentos(){
		return repository.listarLancamentos();
	}
}
