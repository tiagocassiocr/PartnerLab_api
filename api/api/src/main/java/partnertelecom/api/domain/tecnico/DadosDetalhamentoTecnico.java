package partnertelecom.api.domain.tecnico;

import partnertelecom.api.domain.endereco.Endereco;

public record DadosDetalhamentoTecnico(Long id, String nome, String email, String telefone, Setor setor, String cpf, Endereco endereco) {

    public DadosDetalhamentoTecnico (Tecnico tecnico) {
        this(tecnico.getId(), tecnico.getNome(), tecnico.getEmail(), tecnico.getTelefone(), tecnico.getSetor(),tecnico.getCpf(), tecnico.getEndereco());

    }

}
