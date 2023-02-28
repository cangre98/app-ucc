package co.edu.ucc.app.web.api.rest;



import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;
import co.edu.ucc.app.modeloCanonico.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;

public interface IUsuarioController {

    ResponseEntity<GenericResponseDTO> crear(@Valid @RequestBody UsuarioDTO usuarioDTO, HttpServletRequest request) throws Exception;

    ResponseEntity<GenericResponseDTO> login(@Valid @RequestBody UsuarioDTO usuarioDTO, HttpServletRequest request) throws Exception;

    ResponseEntity<GenericResponseDTO> consultarId(BigDecimal id, HttpServletRequest request) throws Exception;

}
