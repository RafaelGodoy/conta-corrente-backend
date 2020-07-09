package br.com.rafaelgodoy.contacorrentebackend.repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import br.com.rafaelgodoy.contacorrentebackend.domain.DadosBancario;
import br.com.rafaelgodoy.contacorrentebackend.domain.Lancamento;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class LancamentoRepository {

	
	public List<Lancamento> listarLancamentos() {
        try {
        	JSONParser jsonParser = new JSONParser();
    		
        	FileReader reader = new FileReader(
        			getClass().getClassLoader().getResource("lancamento-conta-legado.json").getFile()
        			);
            
        	//Read JSON file
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
            JSONArray listaControleLancamentos = (JSONArray) obj.get("listaControleLancamento");
            return (List<Lancamento>) listaControleLancamentos.stream().map(c -> parser((JSONObject) c)).collect(Collectors.toList());
            
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(),e);
        } catch (IOException e) {
        	log.error(e.getMessage(),e);
        } catch (org.json.simple.parser.ParseException e) {
        	log.error(e.getMessage(),e);
        }
		return Collections.EMPTY_LIST;
	}
	
	
	private Lancamento parser(JSONObject c) {
		
		Lancamento lancamento = new Lancamento();
		lancamento.setDadosBancario(new DadosBancario());
		JSONObject lancamentoCCOCliente = (JSONObject) c.get("lancamentoContaCorrenteCliente");
		JSONObject dadosBancarioObj = (JSONObject) lancamentoCCOCliente.get("dadosDomicilioBancario");
		
		lancamento.getDadosBancario().setBanco(Integer.valueOf(dadosBancarioObj.get("codigoBanco").toString()));
		lancamento.getDadosBancario().setAgencia(Integer.valueOf(dadosBancarioObj.get("numeroAgencia").toString()));
		lancamento.getDadosBancario().setContaCorrente(Long.valueOf(dadosBancarioObj.get("numeroContaCorrente").toString()));
		lancamento.getDadosBancario().setNomeBanco(c.get("nomeBanco").toString().trim());
		
		lancamento.setDataConfirmacao(c.get("dataEfetivaLancamento").toString());
		lancamento.setDataLancamento(c.get("dataLancamentoContaCorrenteCliente").toString());
		lancamento.setDescricao(lancamentoCCOCliente.get("nomeTipoOperacao").toString());
		lancamento.setNumero(Integer.valueOf(c.get("numeroEvento").toString()));
		lancamento.setSituacao(lancamentoCCOCliente.get("nomeSituacaoRemessa").toString());
		lancamento.setValor(new BigDecimal(c.get("valorLancamentoRemessa").toString()).setScale(2));
		
		return lancamento;
	}
}
