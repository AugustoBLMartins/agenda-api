package com.estudo.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estudo.agenda.model.entity.Agenda;


public interface AgendaRepository extends JpaRepository<Agenda, Long>{


    boolean existsByCpf(String cpf);
}
