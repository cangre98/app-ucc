package co.edu.ucc.app.service;

import co.edu.ucc.app.modeloCanonico.dto.CuentaDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;
import co.edu.ucc.app.modeloCanonico.entities.CuentaDAO;

import java.math.BigDecimal;

public interface ICuentaService  {

    GenericResponseDTO crear(CuentaDTO cuentaDTO) throws Exception;

    GenericResponseDTO consultarPorId(BigDecimal id) throws Exception;

    GenericResponseDTO eliminarPorId(BigDecimal id) throws Exception;
    void actualizarSaldo(CuentaDAO cuentaDAO) throws Exception;
}
