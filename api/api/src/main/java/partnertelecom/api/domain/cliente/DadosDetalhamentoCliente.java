package partnertelecom.api.domain.cliente;

import partnertelecom.api.domain.endereco.Endereco;

public record DadosDetalhamentoCliente(Long id, String nome, String email, String telefone, String cnpj, Endereco endereco) {

    public DadosDetalhamentoCliente (Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone(), cliente.getCnpj(), cliente.getEndereco());

    }

}



