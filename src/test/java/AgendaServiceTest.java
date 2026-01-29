import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.estudo.agenda.model.dto.AgendaDto;
import com.estudo.agenda.model.entity.Agenda;
import com.estudo.agenda.repository.AgendaRepository;
import com.estudo.agenda.service.AgendaService;

@ExtendWith(MockitoExtension.class) 
class AgendaServiceTest {

    @Mock
    private AgendaRepository agendaRepository; 

    @InjectMocks
    private AgendaService agendaService; 

    @Test
    @DisplayName("Deve buscar um contato por ID com sucesso")
    void buscarPorIdComSucesso() {
    
        Long idExistente = 1L;
        Agenda agendaFake = new Agenda();
        agendaFake.setId(idExistente);
        agendaFake.setNome("Augusto");

        Mockito.when(agendaRepository.findById(idExistente)).thenReturn(Optional.of(agendaFake));

        AgendaDto resultado = agendaService.buscarPorId(idExistente);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Augusto", resultado.getNome());
        
        Mockito.verify(agendaRepository, Mockito.times(1)).findById(idExistente);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o ID não for encontrado")
    void buscarPorIdFalha() {
        
        Long idInexistente = 99L;
        Mockito.when(agendaRepository.findById(idInexistente)).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            agendaService.buscarPorId(idInexistente);
        });

        Assertions.assertEquals("Id usuario não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve salvar um contato com sucesso")
    void salvarComSucesso() {
        
        AgendaDto dtoParaSalvar = new AgendaDto();
        dtoParaSalvar.setNome("Augusto");
        
        Agenda agendaFake = new Agenda();
        agendaFake.setId(1L);
        agendaFake.setNome("Augusto");

        Mockito.when(agendaRepository.save(Mockito.any(Agenda.class))).thenReturn(agendaFake);

        AgendaDto resultado = agendaService.salvarAgendamento(dtoParaSalvar);

        Assertions.assertNotNull(resultado.getId());
        Assertions.assertEquals("Augusto", resultado.getNome());
    }

    @Test
    @DisplayName("Deve deletar o registro com sucesso")
    void deletarComSucesso(){
    
        Long idParaDeletar = 10L;
        Agenda agendaFake = new Agenda();
        agendaFake.setId(idParaDeletar);
        agendaFake.setNome("Augusto");
        
        Mockito.when(agendaRepository.findById(idParaDeletar)).thenReturn(Optional.of(agendaFake));

        agendaService.deletarCadastroAgenda(idParaDeletar);
        
        Mockito.verify(agendaRepository, Mockito.times(1)).delete(agendaFake);

    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar cadastrar CPF já existente")
    void salvarComCpfDuplicadoFalha() {
    
    AgendaDto dto = new AgendaDto();
        dto.setCpf("12345678901");
        
        
        Mockito.when(agendaRepository.existsByCpf(dto.getCpf())).thenReturn(true);

        
        Assertions.assertThrows(RuntimeException.class, () -> {
            agendaService.salvarAgendamento(dto);
        });
    }

}