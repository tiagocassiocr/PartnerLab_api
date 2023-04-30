package partnertelecom.api.domain.cliente;

import partnertelecom.api.domain.endereco.DadosCadastroEndereco;

public record DadosAtualizacaoCliente(
        Long id,
        String nome,
        String email,
        String telefone,
        DadosCadastroEndereco endereco
) {
}
