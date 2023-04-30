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
import partnertelecom.api.domain.ordemdeservico.*;


@RestController
@RequestMapping("/ordemdeservico")
@SecurityRequirement(name = "bearer-key")
@Transactional
@Valid
public class OrdemDeServicoController {

    @Autowired
    private CriarOrdemDeServico agenda;

    @Autowired
    private OrdemDeServicoRepository repository;




    @PostMapping
    public ResponseEntity criar(@RequestBody @Valid DadosCriarOrdemDeServico dados) {

        var dto = agenda.criar(dados);

        return ResponseEntity.ok(dto);

    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoOrdemdeServico>> listar(@PageableDefault(size = 10) Pageable paginacao) {

        var page = repository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoOrdemdeServico::new);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosFechamentoOrdemDeServico dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
