package partnertelecom.api.domain.ordemdeservico;

import java.time.LocalDateTime;

public record DadosListagemOrdemDeServico(Long id, Long idTecnico, Long idCliente, Long idEquipamento, LocalDateTime data) {

    public DadosListagemOrdemDeServico(OrdemDeServico ordemDeServico){

        this(
                ordemDeServico.getId(),
                ordemDeServico.getTecnico().getId(),
                ordemDeServico.getCliente().getId(),
                ordemDeServico.getEquipamento().getId(),
                ordemDeServico.getData()
        );




    }
}
