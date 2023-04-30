package partnertelecom.api.domain.tecnico;

import jakarta.validation.constraints.NotNull;
import partnertelecom.api.domain.endereco.DadosCadastroEndereco;

public record DadosAtualizacaoTecnico(

        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        DadosCadastroEndereco endereco
) {
}
