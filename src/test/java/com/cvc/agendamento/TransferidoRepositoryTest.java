package com.cvc.agendamento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cvc.agendamento.model.Agendamento;
import com.cvc.agendamento.model.Transferido;
import com.cvc.agendamento.service.AgendamentoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(
    basePackageClasses = {
    		AgendamentoService.class
            })
@EnableAutoConfiguration
public class TransferidoRepositoryTest {

	@Autowired
	private AgendamentoService service;
	
	@Test
	public void testAgendarTransferencia() {
		
		Agendamento agendamento = new Agendamento();
		agendamento.setContaOrigem("123456");
		agendamento.setContaDestino("987654");
		agendamento.setDataTransferencia(LocalDate.now());
		agendamento.setValorTransferencia(BigDecimal.valueOf(100.00d));
		
		Transferido transferido = service.agendar(agendamento);
		
		assertEquals(6.00d, transferido.getTaxa().doubleValue());
		
	}
	
	@Test
	public void testListarTransferencia() {
		
		Agendamento agendamento = new Agendamento();
		agendamento.setContaOrigem("123456");
		agendamento.setContaDestino("987654");
		agendamento.setDataTransferencia(LocalDate.now());
		agendamento.setValorTransferencia(BigDecimal.valueOf(100.00d));
		
		service.agendar(agendamento);
		
		agendamento = new Agendamento();
		agendamento.setContaOrigem("123456");
		agendamento.setContaDestino("987654");
		agendamento.setDataTransferencia(LocalDate.now());
		agendamento.setValorTransferencia(BigDecimal.valueOf(50.00d));
		
		service.agendar(agendamento);
		
		List<Transferido> transferidos = service.pesquisarTodos();
		
		assertEquals(2, transferidos.size());
		
	}
	
	
}