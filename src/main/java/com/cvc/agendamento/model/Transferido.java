package com.cvc.agendamento.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Transferido {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonIgnore
    private Long id;
	
	@JsonProperty("conta_origem")
	private String contaOrigem;
	
	@JsonProperty("conta_destino")
	private String contaDestino;
	
	@JsonProperty("valor")
	private BigDecimal valorTransferencia;
	
	@JsonProperty("taxa")
	private BigDecimal taxa;
	
	@JsonProperty("data_transferencia")
	private LocalDate dataTransferencia;
	
	@JsonProperty("data_agendamento")
	private LocalDate dataAgendamento;

}
