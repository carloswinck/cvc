package com.cvc.agendamento.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cvc.agendamento.model.Agendamento;
import com.cvc.agendamento.model.Transferido;
import com.cvc.agendamento.service.AgendamentoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/agendamento")
@Api(tags = "Agendamentos")
@Validated
public class AgendamentoController {

	@Autowired
	private AgendamentoService agendamentoService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Pesquisar agendamentos")
	public ResponseEntity<List<Transferido>> pesquisarTodos() {
		List<Transferido> list = agendamentoService.pesquisarTodos();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Agendar uma transferência")
    public ResponseEntity<Transferido> agendar(@Valid @RequestBody Agendamento agendamento) {
		Transferido transferido = agendamentoService.agendar(agendamento);
		return ResponseEntity.ok().body(transferido);
    }
	
}
