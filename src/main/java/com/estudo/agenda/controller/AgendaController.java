package com.estudo.agenda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.agenda.model.dto.AgendaDto;
import com.estudo.agenda.service.AgendaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaService agendaService;

    @GetMapping
    public ResponseEntity<List<AgendaDto>> listarTodosOsDados(){
        return ResponseEntity.ok(agendaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaDto> buscarPorId(@PathVariable @Min(1) Long id){
        AgendaDto dto = agendaService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AgendaDto> salvarPorId(@Valid @RequestBody AgendaDto dto){
        AgendaDto salvo = agendaService.salvarAgendamento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaDto> Atualizar(@PathVariable Long id,@Valid @RequestBody AgendaDto dto ){
        AgendaDto dadoAtualizado = agendaService.atualizarAgenda(id, dto);

        return ResponseEntity.ok(dadoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDadoDaAgenda(@PathVariable Long id){
        agendaService.deletarCadastroAgenda(id);
        return ResponseEntity.noContent().build();

    }

}
