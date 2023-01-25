package med.voll.api.medico.DTO;

import med.voll.api.endereco.entity.Endereco;
import med.voll.api.medico.entity.Especialidade;
import med.voll.api.medico.entity.Medico;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone,
                                      Especialidade especialidade, Endereco enderco) {
    public DadosDetalhamentoMedico(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getTelefone(),
                medico.getEspecialidade(),
                medico.getEndereco()
        );
    }
}
