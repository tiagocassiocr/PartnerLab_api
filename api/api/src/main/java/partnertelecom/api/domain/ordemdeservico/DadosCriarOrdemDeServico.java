package partnertelecom.api.domain.ordemdeservico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import partnertelecom.api.domain.tecnico.Setor;

import java.time.LocalDateTime;

public record DadosCriarOrdemDeServico(

        Long idTecnico,
        @NotNull
        Long idCliente,
        @NotNull
        Long idEquipamento,
        @NotNull
        @Future
        LocalDateTime data,

        Setor setor

) {
}
