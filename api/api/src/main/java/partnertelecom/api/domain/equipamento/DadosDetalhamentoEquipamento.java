package partnertelecom.api.domain.equipamento;

public record DadosDetalhamentoEquipamento(Long id, String modelo, String fabricante, String numerodeserie) {

    public DadosDetalhamentoEquipamento (Equipamento equipamento) {
        this(equipamento.getId(), equipamento.getModelo(), equipamento.getFabricante(), equipamento.getNumerodeserie());

    }
}
