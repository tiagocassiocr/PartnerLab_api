package partnertelecom.api.domain.ordemdeservico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import partnertelecom.api.domain.cliente.Cliente;
import partnertelecom.api.domain.equipamento.Equipamento;
import partnertelecom.api.domain.tecnico.Tecnico;

import java.time.LocalDateTime;

@Table(name="ordemdeservico")
@Entity(name="OrdemDeServico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class OrdemDeServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tecnico_id")
    private Tecnico tecnico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="equipamento_id")
    private Equipamento equipamento;

    private LocalDateTime data;

    private Boolean ativo;

    @Column(name = "motivofechamento")
    @Enumerated(EnumType.STRING)
    private MotivoFechamento motivoFechamento;

    public void cancelar(MotivoFechamento motivo) {
        this.motivoFechamento = motivo;
    }


    public void desativar() {

        this.ativo = false;
    }
}
