package co.edu.ucc.app.repository;

import co.edu.ucc.app.modeloCanonico.entities.UsuarioDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioDAO, BigDecimal> {

    @Query("SELECT usuario FROM UsuarioDAO usuario WHERE usuario.correo =:correo AND usuario.clave =:clave")
    UsuarioDAO login(@Param("correo") String correo, @Param("clave") String clave);

}
