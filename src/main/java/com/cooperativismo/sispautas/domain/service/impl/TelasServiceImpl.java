package com.cooperativismo.sispautas.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.cooperativismo.sispautas.domain.dto.tela.BodyDTO;
import com.cooperativismo.sispautas.domain.dto.tela.BotaoDTO;
import com.cooperativismo.sispautas.domain.dto.tela.ItensTelaFormularioDTO;
import com.cooperativismo.sispautas.domain.dto.tela.TelaFormularioDTO;
import com.cooperativismo.sispautas.domain.dto.tela.TelaSelecaoDTO;
import com.cooperativismo.sispautas.domain.entity.Pauta;
import com.cooperativismo.sispautas.domain.enums.TipoInput;
import com.cooperativismo.sispautas.domain.enums.TipoTela;
import com.cooperativismo.sispautas.domain.service.TelasService;
import com.cooperativismo.sispautas.domain.service.impl.component.TelasComponent;

@Service
public class TelasServiceImpl implements TelasService{
	
	private final Environment env;
	private final TelasComponent telasComponent;

	@Autowired
	public TelasServiceImpl(Environment env, TelasComponent telasComponent) {
		this.env = env;
		this.telasComponent = telasComponent;
	}
	

	@Override
	public TelaFormularioDTO getTelaIncluirAssociado() {
		TelaFormularioDTO tela = new TelaFormularioDTO();
		List<ItensTelaFormularioDTO> itens = new ArrayList<>();
		ItensTelaFormularioDTO item = new ItensTelaFormularioDTO();
		BotaoDTO botaoOk = new BotaoDTO();
		BotaoDTO botaoCancelar = new BotaoDTO();
		
		//Seta formulário e o título
		tela.setTipo(TipoTela.FORMUALRIO);
		tela.setTitulo("Incluir Associado");
		
		//Seta a primeira parte da tela
		item.setTipo(TipoInput.TEXTO);
		item.setTexto("Preencha os dados para incluir um novo asociado.");
		
		//Seta os campos de preenchimento
		itens.add(item);
		itens.add(buildItem(TipoInput.INPUT_TEXTO,"cpf", "CPF:", ""));
		itens.add(buildItem(TipoInput.INPUT_TEXTO,"nome", "Nome Completo:", ""));
		tela.setItens(itens);
		
		//Seta opções do botao OK
		botaoOk.setTexto("Incluir");
		botaoOk.setUrl(env.getProperty("sispautas.host") + "/associado");
		botaoOk.setBody(new BodyDTO("valor1","123"));
		tela.setBotaoOK(botaoOk);
		
		//Seta opções do botao Cancelar
		botaoCancelar.setTexto("Cancelar");
		botaoCancelar.setUrl(env.getProperty("sispautas.host"));
		tela.setBotaoCancelar(botaoCancelar);
		
		return tela;
	}
	


	@Override
	public TelaFormularioDTO getTelaIncluirPauta() {
		TelaFormularioDTO tela = new TelaFormularioDTO();
		List<ItensTelaFormularioDTO> itens = new ArrayList<>();
		ItensTelaFormularioDTO item = new ItensTelaFormularioDTO();
		BotaoDTO botaoOk = new BotaoDTO();
		BotaoDTO botaoCancelar = new BotaoDTO();
		
		//Seta formulário e o título
		tela.setTipo(TipoTela.FORMUALRIO);
		tela.setTitulo("Incluir Nova Pauta");
		
		//Seta a primeira parte da tela
		item.setTipo(TipoInput.TEXTO);
		item.setTexto("Preencha os dados para incluir uma nova pauta para votação");
		
		//Seta os campos de preenchimento
		itens.add(item);
		itens.add(buildItem(TipoInput.INPUT_TEXTO,"cpfAutor", "CPF:", ""));
		itens.add(buildItem(TipoInput.INPUT_TEXTO,"titulo", "Titulo da Pauta:", ""));
		itens.add(buildItem(TipoInput.INPUT_TEXTO,"detalhes", "Detalhes:", "Insira os detalhes sobre a pauta"));
		tela.setItens(itens);
		
		//Seta opções do botao OK
		botaoOk.setTexto("Incluir");
		botaoOk.setUrl(env.getProperty("sispautas.host") + "/pauta");
		botaoOk.setBody(new BodyDTO("valor1","123"));
		tela.setBotaoOK(botaoOk);
				
		//Seta opções do botao Cancelar
		botaoCancelar.setTexto("Cancelar");
		botaoCancelar.setUrl(env.getProperty("sispautas.host"));
		tela.setBotaoCancelar(botaoCancelar);
		
		return tela;
	}
	
	@Override
	public TelaSelecaoDTO getTelaSelecionarPauta() {
		TelaSelecaoDTO tela = new TelaSelecaoDTO();
		
		//Seta formulário e o título
		tela.setTipo(TipoTela.SELECAO);
		tela.setTitulo("Selecione qual pauta deseja abrir votação");
		
		//Seta os campos de preenchimento
		tela.setItens(telasComponent.buildOptionsToSessao());
		
		return tela;
	}
	
	
	@Override
	public TelaFormularioDTO getTelaAbrirSessao(Long idPauta) {
		TelaFormularioDTO tela = new TelaFormularioDTO();
		Pauta pauta = telasComponent.findPautaNaoAbertaById(idPauta);
		List<ItensTelaFormularioDTO> itens = new ArrayList<>();
		ItensTelaFormularioDTO item = new ItensTelaFormularioDTO();
		BotaoDTO botaoOk = new BotaoDTO();
		BotaoDTO botaoCancelar = new BotaoDTO();
		
		//Seta formulário e o título
		tela.setTipo(TipoTela.FORMUALRIO);
		tela.setTitulo("Abrir sessão para votos");
		
		//Seta a primeira parte da tela
		item.setTipo(TipoInput.TEXTO);
		item.setTexto("Caso não for preenchido o campo de datalimite, o padrão será 1 minuto");
		
		//Seta os campos de preenchimento
		itens.add(item);
		itens.add(buildItem(TipoInput.INPUT_TEXTO,"cpfAutor", "CPF:", pauta.getAutor().getCpf()));
		itens.add(buildItem(TipoInput.INPUT_TEXTO,"titulo", "Titulo da Pauta:", pauta.getTitulo()));
		itens.add(buildItem(TipoInput.INPUT_TEXTO,"detalhes", "Detalhes:", pauta.getDetalhes()));
		itens.add(buildItem(TipoInput.INPUT_DATA,"dataLimite", "Data e hora Limite:", ""));
		tela.setItens(itens);
		
		//Seta opções do botao OK
		botaoOk.setTexto("Incluir");
		botaoOk.setUrl(env.getProperty("sispautas.host") + "/pauta/sessao");
		botaoOk.setBody(new BodyDTO("idPauta", pauta.getId().toString()));
		tela.setBotaoOK(botaoOk);
				
		//Seta opções do botao Cancelar
		botaoCancelar.setTexto("Cancelar");
		botaoCancelar.setUrl(env.getProperty("sispautas.host"));
		tela.setBotaoCancelar(botaoCancelar);
		
		return tela;
	}
	
	@Override
	public TelaFormularioDTO getTelaVotarPauta(Long idPauta) {
		TelaFormularioDTO tela = new TelaFormularioDTO();
		Pauta pauta = telasComponent.findPautaNaoFechadaById(idPauta);
		List<ItensTelaFormularioDTO> itens = new ArrayList<>();
		ItensTelaFormularioDTO item = new ItensTelaFormularioDTO();
		BotaoDTO botaoOk = new BotaoDTO();
		BotaoDTO botaoCancelar = new BotaoDTO();
		
		//Seta formulário e o título
		tela.setTipo(TipoTela.FORMUALRIO);
		tela.setTitulo("Votar: "+ pauta.getTitulo());
		
		//Seta a primeira parte da tela
		item.setTipo(TipoInput.TEXTO);
		item.setTexto(pauta.getDetalhes());
		
		//Seta os campos de preenchimento
		itens.add(item);
		itens.add(buildItem(TipoInput.INPUT_TEXTO,"cpfAutor", "CPF:", pauta.getAutor().getCpf()));
		itens.add(buildItem(TipoInput.INPUT_TEXTO,"decisao", "Voto:", ""));
		tela.setItens(itens);
		
		//Seta opções do botao OK
		botaoOk.setTexto("Votar");
		botaoOk.setUrl(env.getProperty("sispautas.host") + "/votar");
		botaoOk.setBody(new BodyDTO("idPauta", pauta.getId().toString()));
		tela.setBotaoOK(botaoOk);
				
		//Seta opções do botao Cancelar
		botaoCancelar.setTexto("Cancelar");
		botaoCancelar.setUrl(env.getProperty("sispautas.host"));
		tela.setBotaoCancelar(botaoCancelar);
		
		return tela;
	}
	
	
	@Override
	public TelaFormularioDTO getTelaApresentarResultadoPauta(Long idPauta) {
		TelaFormularioDTO tela = new TelaFormularioDTO();
		Pauta pauta = telasComponent.findPautaFechadaById(idPauta);
		List<ItensTelaFormularioDTO> itens = new ArrayList<>();
		ItensTelaFormularioDTO item = new ItensTelaFormularioDTO();
		BotaoDTO botaoOk = new BotaoDTO();
		
		//Seta formulário e o título
		tela.setTipo(TipoTela.FORMUALRIO);
		tela.setTitulo("Votação finalizada: "+ pauta.getTitulo());
		
		//Seta a primeira parte da tela
		item.setTipo(TipoInput.TEXTO);
		item.setTexto(pauta.getDetalhes());
		
		//Seta os campos de preenchimento
		itens.add(item);
		itens.add(buildItem(TipoInput.INPUT_TEXTO,"cpfAutor", "CPF:", pauta.getAutor().getCpf()));
		itens.add(buildItem(TipoInput.INPUT_DATA,"dataLimite", "Data e hora Limite:", pauta.getDataLimite().toString()));
		itens.add(buildItem(TipoInput.INPUT_DATA,"decisao", "Decisão Final: ", pauta.getDecisaoFinal().name()));
		tela.setItens(itens);
		
		//Seta opções do botao OK
		botaoOk.setTexto("Votar");
		botaoOk.setUrl(env.getProperty("sispautas.host"));
		tela.setBotaoOK(botaoOk);
		
		return tela;
	}
	private ItensTelaFormularioDTO buildItem(TipoInput tipo, String id, String titulo, String valor) {
		ItensTelaFormularioDTO item = new ItensTelaFormularioDTO();
		item.setTipo(tipo);
		item.setId(id);
		item.setTitulo(titulo);
		item.setValor(valor);
		return item;
	}







	



}
