package partnertelecom.api.domain.equipamento;

public record DadosListagemEquipamento(String modelo, String fabricante, String numerodeserie) {

    public DadosListagemEquipamento(Equipamento equipamento){

        this(
                equipamento.getModelo(),
                equipamento.getFabricante(),
                equipamento.getNumerodeserie()
        );




    }
}
