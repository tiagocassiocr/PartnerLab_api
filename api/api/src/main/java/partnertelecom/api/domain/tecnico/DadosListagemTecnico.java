package partnertelecom.api.domain.tecnico;

public record DadosListagemTecnico(Long id, String nome, String email, String telefone, Setor setor) {

    public DadosListagemTecnico(Tecnico tecnico){

        this(
                tecnico.getId(),
                tecnico.getNome(),
                tecnico.getEmail(),
                tecnico.getTelefone(),
                tecnico.getSetor()
        );




    }
}
