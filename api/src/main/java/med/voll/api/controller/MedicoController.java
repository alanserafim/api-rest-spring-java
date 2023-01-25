package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.DTO.DadosAtualizacaoMedico;
import med.voll.api.domain.medico.DTO.DadosCadastroMedico;
import med.voll.api.domain.medico.DTO.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.DTO.DadosListagemMedico;
import med.voll.api.domain.medico.entity.Medico;
import med.voll.api.domain.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadatrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico)); // código 201 (created) - Requisiçãp processada e novo recurso criado
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page); // Código 200
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
            var medico = repository.getReferenceById(dados.id());
            medico.atualizarInformacoes(dados);
            return ResponseEntity.ok(new DadosDetalhamentoMedico(medico)); // código 200
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build(); //204 - No content. Processada com sucesso, mas sem conteúdo.
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

}