package med.voll.api.domain.paciente.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DTO.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull Long id,
        String nome,
        String telefone,
        @Valid DadosEndereco endereco
) {
}
