package partnertelecom.api.domain.cliente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import partnertelecom.api.domain.endereco.DadosCadastroEndereco;

public record DadosCadastroCliente(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        String cnpj,
        @NotNull
        @Valid
        DadosCadastroEndereco endereco) {
}
