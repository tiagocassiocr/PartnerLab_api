package partnertelecom.api.domain.equipamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    Page<Equipamento> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select e.ativo
            from Equipamento e
            where
            e.id = :id          
              
            """)
    Boolean findAtivoById(Long id);
}
