package partnertelecom.api.domain.ordemdeservico.validacoes.criacao;

import org.springframework.stereotype.Component;
import partnertelecom.api.infra.exception.ValidacaoException;
import partnertelecom.api.domain.ordemdeservico.DadosCriarOrdemDeServico;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarHorarioAntecedencia implements ValidarCriacaoOrdemDeServico {

    public void validar (DadosCriarOrdemDeServico dados) {

        var dataOrdemDeServico = dados.data();
        var now = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(now, dataOrdemDeServico).toMinutes();

        if (diferencaEmMinutos < 60) {
            throw new ValidacaoException("Ordem de Servico deve ser criada com antecencia minima de 60 minutos");
        }
    }
}
