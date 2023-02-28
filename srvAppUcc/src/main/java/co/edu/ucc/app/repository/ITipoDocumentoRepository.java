package co.edu.ucc.app.repository;

import co.edu.ucc.app.modeloCanonico.entities.TipodocumentoDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ITipoDocumentoRepository extends JpaRepository<TipodocumentoDAO, BigDecimal> {



}
