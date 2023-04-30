package partnertelecom.api.domain.ordemdeservico.validacoes.fechamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import partnertelecom.api.infra.exception.ValidacaoException;
import partnertelecom.api.domain.ordemdeservico.DadosFechamentoOrdemDeServico;
import partnertelecom.api.domain.ordemdeservico.OrdemDeServicoRepository;
import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaFechamento")
public class ValidadorHorarioAntecedencia implements ValidadorFechamentoOrdemDeServico {

    @Autowired
    private OrdemDeServicoRepository repository;

    @Override
    public void validar(DadosFechamentoOrdemDeServico dados) {
        var consulta = repository.getReferenceById(dados.idOrdemDeServico());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Ordem de Servico só pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}
