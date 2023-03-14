package co.edu.ucc.app.web.api.rest;

import co.edu.ucc.app.modeloCanonico.dto.AhorroDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;

public interface IAhorroController {

    ResponseEntity<GenericResponseDTO> crear(@Valid @RequestBody AhorroDTO ahorroDTO, HttpServletRequest request) throws Exception;

    ResponseEntity<GenericResponseDTO> consultarId(BigDecimal id, HttpServletRequest request) throws Exception;

    ResponseEntity<GenericResponseDTO> eliminarPorId(BigDecimal id, HttpServletRequest request) throws Exception;

}


