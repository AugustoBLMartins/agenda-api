package com.estudo.agenda.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.estudo.agenda.model.dto.AgendaDto;
import com.estudo.agenda.model.entity.Agenda;
import com.estudo.agenda.repository.AgendaRepository;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;

    public AgendaService(AgendaRepository agendaRepository){
        this.agendaRepository = agendaRepository;
    }

    public AgendaDto buscarPorId(Long id){
        Agenda agenda = agendaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id usuario não encontrado"));

        return converterParaDto(agenda);
    }
    
    public AgendaDto salvarAgendamento(AgendaDto dto){
        
        if (agendaRepository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("CPF já cadastrado no sistema");
        }
        //transforma dto em entity
        Agenda entity = converterParaEntity(dto);
        //salvo entity e recebo ele atualizado com o id
        Agenda entitySalva = agendaRepository.save(entity);
        //transformo o entity em dto para responder
        return converterParaDto(entitySalva);
    }

    public AgendaDto atualizarAgenda(Long id, AgendaDto dto){
        Agenda agendaExistente = agendaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id nao encontrado"));
        
        agendaExistente.setNome(dto.getNome());
        agendaExistente.setTelefone(dto.getTelefone());
        agendaExistente.setEndereco(dto.getEndereco());
        agendaExistente.setEmail(dto.getEmail());

        Agenda agendaSalva = agendaRepository.save(agendaExistente);

        return converterParaDto(agendaSalva);
    }

    public void deletarCadastroAgenda(Long id){
        Agenda cadastroParaDeletar = agendaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id nao existe"));

        agendaRepository.delete(cadastroParaDeletar);

    }

    public List<AgendaDto> listarTodos(){
        List<Agenda> entidades = agendaRepository.findAll();

        return entidades.stream()
        .map(this::converterParaDto)
        .collect(Collectors.toList());
    }

    private AgendaDto converterParaDto(Agenda entity){
        return AgendaDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .telefone(entity.getTelefone())
                .endereco(entity.getEndereco())
                .email(entity.getEmail())
                .build();
    }

    private Agenda converterParaEntity(AgendaDto dto){
        return Agenda.builder()
                .nome(dto.getNome())
                .telefone(dto.getTelefone())
                .endereco(dto.getEndereco())
                .email(dto.getEmail())
                .build();
    }
}
