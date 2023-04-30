package partnertelecom.api.domain.tecnico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
    Page<Tecnico> findAllByAtivoTrue(Pageable paginacao);


    @Query("""
            select t from Tecnico t
            where
            t.ativo = 1
            and
            t.setor = :setor
            and
            t.id not in(
                select o.tecnico.id from OrdemDeServico o
                where
                o.data = :data
            )
            order by rand()
            limit 1            
            """)
    Tecnico escolherTecnicoAleatorioLivreNaData(Setor setor, LocalDateTime data);


    @Query("""
            select t.ativo
            from Tecnico t
            where
            t.id = :id          
              
            """)
    Boolean findAtivoById(Long id);
}
