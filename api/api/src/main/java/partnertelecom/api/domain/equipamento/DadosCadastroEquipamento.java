package partnertelecom.api.domain.equipamento;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEquipamento(
        @NotBlank
        String modelo,
        @NotBlank
        String fabricante,
        @NotBlank
        String numerodeserie
)
{
}
