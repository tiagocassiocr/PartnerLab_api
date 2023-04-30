package partnertelecom.api.domain.ordemdeservico;

import java.time.LocalDateTime;

public record DadosDetalhamentoOrdemdeServico(Long id, String nomeTecnico, String nomeCliente, String nomeEquipamento, LocalDateTime data) {
    public DadosDetalhamentoOrdemdeServico(OrdemDeServico ordemdeservico) {

        this(
                ordemdeservico.getId(),
                ordemdeservico.getTecnico().getNome(),
                ordemdeservico.getCliente().getNome(),
                ordemdeservico.getEquipamento().getNumerodeserie(),
                ordemdeservico.getData()
        );
    }
}
