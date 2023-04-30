package partnertelecom.api.domain.equipamento;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name="equipamento")
@Entity(name="Equipamento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Equipamento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private String fabricante;
    private String numerodeserie;
    private Boolean ativo;


    public Equipamento(DadosCadastroEquipamento dados) {

        this.modelo = dados.modelo();
        this.fabricante = dados.fabricante();
        this.numerodeserie = dados.numerodeserie();
        this.ativo = true;

    }

    public void AtualizarInformacoes(DadosAtualizacaoEquipamento dados) {

        if (dados.modelo() != null) {
            this.modelo = dados.modelo();
        }

        if (dados.fabricante() != null) {
            this.fabricante = dados.fabricante();
        }
        if (dados.numerodeserie() != null) {
            this.numerodeserie = dados.numerodeserie();
        }
    }

    public void excluir() {

        this.ativo = false;
    }
}
