package partnertelecom.api.domain.ordemdeservico;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;

public interface OrdemDeServicoRepository extends JpaRepository<OrdemDeServico, Long> {



    boolean existsByTecnicoIdAndData (Long idTecnico, LocalDateTime data);

    Page<OrdemDeServico> findAllByAtivoTrue(Pageable paginacao);



}
