package partnertelecom.api.domain.ordemdeservico.validacoes.fechamento;

import partnertelecom.api.domain.ordemdeservico.DadosFechamentoOrdemDeServico;

public interface ValidadorFechamentoOrdemDeServico {

    void validar(DadosFechamentoOrdemDeServico dados);
}
