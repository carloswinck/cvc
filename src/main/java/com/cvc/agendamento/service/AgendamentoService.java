package com.cvc.agendamento.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cvc.agendamento.constants.Constants;
import com.cvc.agendamento.exception.CustomException;
import com.cvc.agendamento.model.Agendamento;
import com.cvc.agendamento.model.Transferido;
import com.cvc.agendamento.repository.TransferidoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private TransferidoRepository transferidoRepository;

	public List<Transferido> pesquisarTodos() {
		return transferidoRepository.findAll();
	}

	@Transactional
	public Transferido agendar(Agendamento agendamento) {
		Transferido transferido = this.calculo(agendamento);
		transferido = transferidoRepository.save(transferido);
		return transferido;
	}

	LocalDate dataAgendamento = LocalDate.now();

	private Transferido calculo(Agendamento agendamento) {

		Transferido transferido = new Transferido();
		transferido.setDataAgendamento(dataAgendamento);

		try {
			BeanUtils.copyProperties(transferido, agendamento);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Long diff = ChronoUnit.DAYS.between(dataAgendamento, agendamento.getDataTransferencia());

		if (diff == 0) {
			transferido.setTaxa((agendamento.getValorTransferencia().multiply(new BigDecimal(Constants.PCT3)).add(Constants.TRES)));
		} else if (diff >= 0 && diff <= 10) {
			transferido.setTaxa(new BigDecimal(diff).multiply(Constants.DOZE));
		} else if (diff >= 11 && diff <= 20) {
			transferido.setTaxa(agendamento.getValorTransferencia().multiply(new BigDecimal(Constants.PCT8)));
		} else if (diff >= 21 && diff <= 30) {
			transferido.setTaxa(agendamento.getValorTransferencia().multiply(new BigDecimal(Constants.PCT6)));
		} else if (diff >= 31 && diff <= 40) {
			transferido.setTaxa(agendamento.getValorTransferencia().multiply(new BigDecimal(Constants.PCT4)));
		} else if (diff >= 41 && agendamento.getValorTransferencia().doubleValue() > 100000) {
			transferido.setTaxa(agendamento.getValorTransferencia().multiply(new BigDecimal(Constants.PCT2)));
		} else {
			throw new CustomException("Taxa não pode ser calculada", HttpStatus.BAD_REQUEST);
		}

		return transferido;

	}

}