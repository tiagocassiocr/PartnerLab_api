package partnertelecom.api.domain.ordemdeservico.validacoes.criacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import partnertelecom.api.infra.exception.ValidacaoException;
import partnertelecom.api.domain.ordemdeservico.DadosCriarOrdemDeServico;
import partnertelecom.api.domain.tecnico.TecnicoRepository;

@Component
public class ValidarTecnicoAtivo implements ValidarCriacaoOrdemDeServico {

    @Autowired
    private TecnicoRepository repository;

    public void validar (DadosCriarOrdemDeServico dados) {

        if (dados.idTecnico() == null){

            return;
        }

        var tecnicoEstaAtivo = repository.findAtivoById(dados.idTecnico());


        if (!tecnicoEstaAtivo) {
            throw new ValidacaoException("Ordem de Servico nao pode ser feita com tecnico excluído!");
        }
    }
}
