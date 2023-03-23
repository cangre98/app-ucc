package co.edu.ucc.app.service;

import co.edu.ucc.app.modeloCanonico.dto.GastoDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;

import java.math.BigDecimal;

public interface IGastoService {

    GenericResponseDTO crearGasto(GastoDTO gastoDTO) throws Exception;

    GenericResponseDTO consultarPorId(BigDecimal id) throws Exception;

    GenericResponseDTO eliminarPorId(BigDecimal id) throws Exception;

}
