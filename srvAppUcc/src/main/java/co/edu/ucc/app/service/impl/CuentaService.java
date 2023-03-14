package co.edu.ucc.app.service.impl;

import co.edu.ucc.app.commons.converter.ConverterApp;
import co.edu.ucc.app.modeloCanonico.dto.CuentaDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;
import co.edu.ucc.app.modeloCanonico.entities.CuentaDAO;
import co.edu.ucc.app.modeloCanonico.entities.EgresoDAO;
import co.edu.ucc.app.repository.ICuentaRepository;
import co.edu.ucc.app.repository.IPersonaRepository;
import co.edu.ucc.app.service.ICuentaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CuentaService implements ICuentaService {

    private static final Logger logger = LoggerFactory.getLogger(CuentaService.class);

    private final ICuentaRepository iCuentaRepository;
    private final ModelMapper modelMapper;
    private final ConverterApp converterApp;

    @Autowired
    public CuentaService(ICuentaRepository iCuentaRepository, ModelMapper modelMapper, ConverterApp converterApp) {
        this.iCuentaRepository = iCuentaRepository;
        this.modelMapper = modelMapper;
        this.converterApp = converterApp;
    }

    @Override
    public GenericResponseDTO crear(CuentaDTO cuentaDTO) throws Exception {

        CuentaDTO cuentaDTOsalida = null;

        CuentaDAO cuentaDAO = converterApp.cuentaDTOtoDAO(cuentaDTO, modelMapper);



        BigDecimal buscarPorIdPersona = iCuentaRepository.consultarCuentaPorIdPersona(cuentaDAO.getIdPersona().getId());

        if (buscarPorIdPersona == null) {

            iCuentaRepository.save(cuentaDAO);

             cuentaDTOsalida = converterApp.cuentaDAOtoDTO(cuentaDAO, modelMapper);

            return GenericResponseDTO.builder().message("Cuenta registrada con exito").objectResponse(cuentaDTOsalida).statusCode(HttpStatus.OK.value()).build();
        }

        iCuentaRepository.actualizarCuenta(cuentaDAO.getDescripcion(), cuentaDAO.getSaldo(),
                cuentaDAO.getDetalle(), buscarPorIdPersona);

        cuentaDTOsalida = converterApp.cuentaDAOtoDTO(cuentaDAO, modelMapper);

        return GenericResponseDTO.builder().message("Cuenta registrada con exito").objectResponse(cuentaDTOsalida).statusCode(HttpStatus.OK.value()).build();
    }

    @Override
    public GenericResponseDTO consultarPorId(BigDecimal id) throws Exception {
        try {

            Optional<CuentaDAO> buscar = iCuentaRepository.findById(id);

            if (buscar.isEmpty()) {
                    return GenericResponseDTO.builder().message("El id N°"+ id +" de la que ha ingresado  no existe").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            } else{
                return GenericResponseDTO.builder().message("Consulta cuenta por id: "+ id +" realizada con exito").objectResponse(buscar).statusCode(HttpStatus.OK.value()).build();
            }

        }catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error consultando el egreso").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public GenericResponseDTO eliminarPorId(BigDecimal id) throws Exception {
        try {

            Optional<CuentaDAO> buscar = iCuentaRepository.findById(id);

            if (buscar.isEmpty()) {
                return GenericResponseDTO.builder().message("El id N°"+ id +" del egreso que ha ingresado no existe").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            } else{
                iCuentaRepository.deleteById(id);
                return GenericResponseDTO.builder().message("Eliminaado exitoso").objectResponse(id).statusCode(HttpStatus.OK.value()).build();
            }

        }catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al consultar cuenta").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }
}
