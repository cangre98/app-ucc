package co.edu.ucc.app.service;

import co.edu.ucc.app.modeloCanonico.dto.AhorroDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;

import java.math.BigDecimal;

public interface IAhorroService {

    GenericResponseDTO crearEgreso(AhorroDTO ahorroDTO) throws Exception;

    GenericResponseDTO consultarPorId(BigDecimal id) throws Exception;

    GenericResponseDTO eliminarPorId(BigDecimal id) throws Exception;


}
