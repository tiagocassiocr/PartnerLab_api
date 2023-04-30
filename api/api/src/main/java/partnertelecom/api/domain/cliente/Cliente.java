package partnertelecom.api.domain.cliente;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import partnertelecom.api.domain.endereco.Endereco;


@Table(name="cliente")
@Entity(name="Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cnpj;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;
    public Cliente(DadosCadastroCliente dados) {


        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cnpj = dados.cnpj();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void AtualizarInformacoes(DadosAtualizacaoCliente dados) {

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.endereco() != null) {
            this.endereco.AtualizaInformacoes(dados.endereco());
        }
    }

    public void excluir() {

        this.ativo = false;
    }
}