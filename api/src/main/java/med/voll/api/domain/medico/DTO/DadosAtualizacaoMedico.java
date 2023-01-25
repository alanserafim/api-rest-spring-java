package med.voll.api.domain.medico.DTO;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DTO.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {

}
