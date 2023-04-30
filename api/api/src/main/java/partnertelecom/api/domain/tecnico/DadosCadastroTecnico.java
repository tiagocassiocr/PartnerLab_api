package partnertelecom.api.domain.tecnico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import partnertelecom.api.domain.endereco.DadosCadastroEndereco;

public record DadosCadastroTecnico(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotNull
        Setor setor,
        @Pattern(regexp="/^(([0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2})|([0-9]{11}))$/")
        String cpf,
        @NotNull
        @Valid
        DadosCadastroEndereco endereco) {
}
