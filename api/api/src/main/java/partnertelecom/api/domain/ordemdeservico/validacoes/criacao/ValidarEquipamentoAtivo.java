package partnertelecom.api.domain.ordemdeservico.validacoes.criacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import partnertelecom.api.infra.exception.ValidacaoException;
import partnertelecom.api.domain.equipamento.EquipamentoRepository;
import partnertelecom.api.domain.ordemdeservico.DadosCriarOrdemDeServico;
@Component
public class ValidarEquipamentoAtivo implements ValidarCriacaoOrdemDeServico{

    @Autowired
    private EquipamentoRepository repository;

    public void validar (DadosCriarOrdemDeServico dados) {

        if (dados.idEquipamento() == null){

            return;
        }

        var equipamentoEstaAtivo = repository.findAtivoById(dados.idEquipamento());


        if (!equipamentoEstaAtivo) {
            throw new ValidacaoException("Ordem de Servico nao pode ser feita com equipamento excluído!");
        }
    }
}
