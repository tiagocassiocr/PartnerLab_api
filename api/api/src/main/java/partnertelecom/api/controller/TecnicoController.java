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
import partnertelecom.api.domain.tecnico.*;

@RestController
@RequestMapping("/tecnico")
@SecurityRequirement(name = "bearer-key")
@Transactional
@Valid
public class TecnicoController {

    @Autowired
    private TecnicoRepository repository;


    @PostMapping
    public ResponseEntity cadastrar(@RequestBody DadosCadastroTecnico dados, UriComponentsBuilder uriBuilder) {

        var tecnico = new Tecnico(dados);
        repository.save(tecnico);
        var uri = uriBuilder.path("/tecnico/{id}").buildAndExpand(tecnico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTecnico(tecnico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTecnico>> listar(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemTecnico::new);

        return ResponseEntity.ok(page);
    }


    @PutMapping("{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTecnico dados ){


        var tecnico = repository.getReferenceById(dados.id());
        tecnico.AtualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTecnico(tecnico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {

        var tecnico = repository.getReferenceById(id);
        tecnico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var tecnico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTecnico(tecnico));
    }




}
