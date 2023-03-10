package co.edu.ucc.app.service;

import co.edu.ucc.app.modeloCanonico.dto.EgresoDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;

import java.math.BigDecimal;

public interface IEgresoService {

    GenericResponseDTO crearEgreso(EgresoDTO egresoDTO) throws Exception;

    GenericResponseDTO consultarPorId(BigDecimal id) throws Exception;

    GenericResponseDTO eliminarPorId(BigDecimal id) throws Exception;


}
