package partnertelecom.api.domain.equipamento;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoEquipamento(
        @NotNull
        Long id,
        String modelo,
        String fabricante,
        String numerodeserie
) {
}
