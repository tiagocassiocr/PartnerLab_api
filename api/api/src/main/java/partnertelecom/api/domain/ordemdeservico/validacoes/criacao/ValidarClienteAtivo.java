package partnertelecom.api.domain.ordemdeservico.validacoes.criacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import partnertelecom.api.infra.exception.ValidacaoException;
import partnertelecom.api.domain.cliente.ClienteRepository;
import partnertelecom.api.domain.ordemdeservico.DadosCriarOrdemDeServico;
@Component
public class ValidarClienteAtivo implements ValidarCriacaoOrdemDeServico {

    @Autowired
    private ClienteRepository repository;

    public void validar (DadosCriarOrdemDeServico dados) {

        if (dados.idCliente() == null){

            return;
        }

        var clienteEstaAtivo = repository.findAtivoById(dados.idCliente());


        if (!clienteEstaAtivo) {
            throw new ValidacaoException("Ordem de Servico nao pode ser feita com cliente excluído!");
        }
    }
}
