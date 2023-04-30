package partnertelecom.api.domain.tecnico;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import partnertelecom.api.domain.endereco.Endereco;


@Table(name="tecnico")
@Entity(name="Tecnico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Tecnico {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    @Enumerated (EnumType.STRING)
    private Setor setor;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;


    public Tecnico(DadosCadastroTecnico dados) {

        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.setor = dados.setor();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void AtualizarInformacoes(DadosAtualizacaoTecnico dados) {

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.email()!= null) {
            this.email = dados.email();
        }
        if (dados.endereco() != null){
            this.endereco.AtualizaInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
