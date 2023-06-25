package co.edu.ucc.app.service;


import co.edu.ucc.app.modeloCanonico.dto.IngresoDTO;
import co.edu.ucc.app.modeloCanonico.dto.InversionDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;

import java.math.BigDecimal;


public interface IIngresoService {

    GenericResponseDTO crear(IngresoDTO ingresoDTO) throws Exception;

    GenericResponseDTO consultarPorId(BigDecimal entrada) throws Exception;
    GenericResponseDTO sumaIngresosIdCuenta(BigDecimal entrada) throws Exception;

    GenericResponseDTO consultarIngresoPorIdCuenta(BigDecimal idCuenta) throws Exception;


    GenericResponseDTO eliminarPorId(BigDecimal id) throws Exception;




}
