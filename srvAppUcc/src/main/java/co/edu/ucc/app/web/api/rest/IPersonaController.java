package co.edu.ucc.app.web.api.rest;


import co.edu.ucc.app.modeloCanonico.dto.PersonaDTO;
import co.edu.ucc.app.modeloCanonico.dto.UsuarioDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;

public interface IPersonaController {

    ResponseEntity<GenericResponseDTO> crear(@Valid @RequestBody PersonaDTO personaDTO, HttpServletRequest request) throws Exception;

    ResponseEntity<GenericResponseDTO> consultarId(BigDecimal id, HttpServletRequest request) throws Exception;

    ResponseEntity<GenericResponseDTO> listarTiposPersona(HttpServletRequest request) throws Exception;

    ResponseEntity<GenericResponseDTO> listarTiposDocumento(HttpServletRequest request) throws Exception;





}
