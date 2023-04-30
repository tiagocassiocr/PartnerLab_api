package partnertelecom.api.domain.ordemdeservico.validacoes.criacao;

import org.springframework.stereotype.Component;
import partnertelecom.api.infra.exception.ValidacaoException;
import partnertelecom.api.domain.ordemdeservico.DadosCriarOrdemDeServico;

import java.time.DayOfWeek;
@Component
public class ValidadorHorarioFuncionamentoEmpresa implements ValidarCriacaoOrdemDeServico {

    public void validar (DadosCriarOrdemDeServico dados) {
        var dataOrdemDeServico = dados.data();
        var sabado = dataOrdemDeServico.getDayOfWeek().equals(DayOfWeek.SATURDAY);
        var domingo = dataOrdemDeServico.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaEmpresa = dataOrdemDeServico.getHour() < 8;
        var depoisDoFechamentoDaEmpresa = dataOrdemDeServico.getHour() > 18;

        if (sabado || domingo || antesDaAberturaDaEmpresa || depoisDoFechamentoDaEmpresa) {

            throw new ValidacaoException("Ordem de Servico fora do horario util da Empresa");
        }


    }
}
