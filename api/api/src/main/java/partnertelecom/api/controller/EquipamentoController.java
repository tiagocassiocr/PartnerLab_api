package partnertelecom.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import partnertelecom.api.domain.equipamento.*;


@RestController
@RequestMapping("/equipamento")
@SecurityRequirement(name = "bearer-key")
@Transactional
@Valid
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository repository;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody DadosCadastroEquipamento dados ,UriComponentsBuilder uriBuilder) {



        var equipamento = new Equipamento(dados);
        repository.save(equipamento);
        var uri = uriBuilder.path("/equipamento/{id}").buildAndExpand(equipamento.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoEquipamento(equipamento));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemEquipamento>> listar(@PageableDefault(size = 10, sort = {"modelo"})Pageable paginacao) {

       var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemEquipamento::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoEquipamento dados ) {
        var equipamento = repository.getReferenceById(dados.id());
        equipamento.AtualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoEquipamento(equipamento));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {

        var equipamento = repository.getReferenceById(id);
        equipamento.excluir();

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var equipamento = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoEquipamento(equipamento));
    }



}
