package com.cvc.agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cvc.agendamento.model.Transferido;

@Repository
public interface TransferidoRepository extends JpaRepository<Transferido, Long> {


}