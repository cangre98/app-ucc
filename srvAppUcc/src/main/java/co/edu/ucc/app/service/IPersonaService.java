package co.edu.ucc.app.service;


import co.edu.ucc.app.modeloCanonico.dto.PersonaDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;

import java.math.BigDecimal;


public interface IPersonaService {

    GenericResponseDTO crear(PersonaDTO usuarioDTO) throws Exception;

    GenericResponseDTO consultarPorId(BigDecimal entrada) throws Exception;

    GenericResponseDTO listarTiposPersona() throws Exception;

    GenericResponseDTO listarTiposDocumento() throws Exception;




}
