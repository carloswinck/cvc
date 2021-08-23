package com.cvc.agendamento.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Validated
public class Agendamento {
	
	@Size(min = 6, max = 6, message = "{conta.size}")
    @NotBlank(message = "{conta.origem.notempty}")
	@JsonProperty("conta_origem")
	private String contaOrigem;
	
	@Size(min = 6, max = 6, message = "{conta.size}")
    @NotBlank(message = "{conta.destino.notempty}")
	@JsonProperty("conta_destino")
	private String contaDestino;
	
	@JsonProperty("valor")
    @DecimalMin(value = "0.00", inclusive = false, message = "{decimal.size}")
	private BigDecimal valorTransferencia;
	
	@JsonProperty("data_transferencia")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private LocalDate dataTransferencia;

}
