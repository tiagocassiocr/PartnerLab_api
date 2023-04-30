package partnertelecom.api.domain.ordemdeservico.validacoes.criacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import partnertelecom.api.infra.exception.ValidacaoException;
import partnertelecom.api.domain.ordemdeservico.DadosCriarOrdemDeServico;
import partnertelecom.api.domain.ordemdeservico.OrdemDeServicoRepository;

@Component
public class ValidarTecnicoLivre implements ValidarCriacaoOrdemDeServico {

    @Autowired
    private OrdemDeServicoRepository repository;

    public void validar (DadosCriarOrdemDeServico dados) {
        var tecnicoNaoEstaLivre = repository.existsByTecnicoIdAndData(dados.idTecnico(), dados.data());

        if (tecnicoNaoEstaLivre) {

            throw new ValidacaoException("Tecnico nao esta livre no horario escolhido");


        }

    }
}
