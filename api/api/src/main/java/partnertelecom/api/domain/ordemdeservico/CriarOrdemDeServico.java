package partnertelecom.api.domain.ordemdeservico;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import partnertelecom.api.infra.exception.ValidacaoException;
import partnertelecom.api.domain.cliente.ClienteRepository;
import partnertelecom.api.domain.equipamento.EquipamentoRepository;
import partnertelecom.api.domain.ordemdeservico.validacoes.criacao.ValidarCriacaoOrdemDeServico;
import partnertelecom.api.domain.ordemdeservico.validacoes.fechamento.ValidadorFechamentoOrdemDeServico;
import partnertelecom.api.domain.tecnico.Tecnico;
import partnertelecom.api.domain.tecnico.TecnicoRepository;

import java.util.List;

@Service
public class CriarOrdemDeServico {

    @Autowired
    private OrdemDeServicoRepository ordemDeServicoRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private List<ValidarCriacaoOrdemDeServico> validadores;
    @Autowired
    private List<ValidadorFechamentoOrdemDeServico> validadoresFechamento;


    public DadosDetalhamentoOrdemdeServico criar (DadosCriarOrdemDeServico dados) {

        if (dados.idTecnico() != null && !tecnicoRepository.existsById(dados.idTecnico())) {
            throw new ValidacaoException("Tecnico informado não existe!");
        }

        if (!clienteRepository.existsById(dados.idCliente())) {
            throw new ValidacaoException("Cliente informado não existe!");
        }

        if (!equipamentoRepository.existsById(dados.idEquipamento())) {
            throw new ValidacaoException("Equipamento informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));


        var tecnico = escolherTecnico(dados);
        var cliente = clienteRepository.getReferenceById(dados.idCliente());
        var equipamento = equipamentoRepository.getReferenceById(dados.idEquipamento());


        if (tecnico == null) {
            throw new ValidacaoException("Nao existe tecnico disponivel nesse horario");
        }
        var ordemdeservico = new OrdemDeServico(null, tecnico, cliente, equipamento, dados.data(), true, null);

        ordemDeServicoRepository.save(ordemdeservico);

        return new DadosDetalhamentoOrdemdeServico(ordemdeservico);



    }

    public void cancelar(DadosFechamentoOrdemDeServico dados) {
        if (!ordemDeServicoRepository.existsById(dados.idOrdemDeServico())) {
            throw new ValidacaoException("Id da Ordem de Servico informado não existe!");
        }

        validadoresFechamento.forEach(v -> v.validar(dados));

        var desativar = ordemDeServicoRepository.getReferenceById(dados.idOrdemDeServico());
        desativar.desativar();


        var ordemdeservico = ordemDeServicoRepository.getReferenceById(dados.idOrdemDeServico());
        ordemdeservico.cancelar(dados.motivo());
    }


    private Tecnico escolherTecnico(DadosCriarOrdemDeServico dados) {

        if (dados.idTecnico() != null){

            return tecnicoRepository.getReferenceById(dados.idTecnico());

        }

        if (dados.setor() == null) {

            throw new ValidacaoException ("Quando o tecnico nao é escolhido o setor é obrigatório");
        }

        return tecnicoRepository.escolherTecnicoAleatorioLivreNaData(dados.setor(), dados.data());


    }
}
