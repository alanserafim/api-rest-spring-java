package med.voll.api.domain.medico.DTO;

import med.voll.api.domain.medico.entity.Medico;
import med.voll.api.domain.medico.entity.Especialidade;

public record DadosListagemMedico(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public DadosListagemMedico(Medico medico){
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getEspecialidade()
        );
    }

}
