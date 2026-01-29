package com.estudo.agenda.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendaDto {


    private Long id;

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @NotBlank(message = "Email não pode ser vazio")
    private String email;

    @NotNull(message = "Telefone não pode ser vazio")
    @Positive(message = "Precisa ser maior que zero")
    private Long telefone;

    @NotBlank(message = "Endereço não pode ser vazio")
    private String endereco;

    @NotBlank(message = "Cpf não pode ser vazio")
    private String cpf;
   
}
