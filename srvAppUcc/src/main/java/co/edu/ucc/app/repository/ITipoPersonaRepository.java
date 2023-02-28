package co.edu.ucc.app.repository;

import co.edu.ucc.app.modeloCanonico.entities.TipoPersonaDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ITipoPersonaRepository extends JpaRepository<TipoPersonaDAO, BigDecimal> {



}
