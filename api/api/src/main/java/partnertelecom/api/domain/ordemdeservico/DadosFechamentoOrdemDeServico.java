package partnertelecom.api.domain.ordemdeservico;

import jakarta.validation.constraints.NotNull;

public record DadosFechamentoOrdemDeServico(
        @NotNull
        Long idOrdemDeServico,

        @NotNull
        MotivoFechamento motivo
) {
}
