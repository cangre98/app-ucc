package co.edu.ucc.app.service;


import co.edu.ucc.app.modeloCanonico.dto.InversionDTO;
import co.edu.ucc.app.modeloCanonico.dto.PersonaDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;

import java.math.BigDecimal;


public interface IInversionService {

    GenericResponseDTO crear(InversionDTO ingresoDTO) throws Exception;

    GenericResponseDTO consultarPorId(BigDecimal entrada) throws Exception;
    GenericResponseDTO consultarTodos() throws Exception;

    GenericResponseDTO eliminarPorId(BigDecimal id) throws Exception;




}
