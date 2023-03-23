package co.edu.ucc.app.service.impl;

import co.edu.ucc.app.commons.converter.ConverterApp;
import co.edu.ucc.app.modeloCanonico.dto.IngresoDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;
import co.edu.ucc.app.modeloCanonico.entities.CuentaDAO;
import co.edu.ucc.app.modeloCanonico.entities.IngresoDAO;
import co.edu.ucc.app.repository.IIngresoRepository;
import co.edu.ucc.app.service.IIngresoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class IngresoService implements IIngresoService {

    private static final Logger logger = LoggerFactory.getLogger(IngresoService.class);

    private final IIngresoRepository ingresoRepository;

    private final CuentaService cuentaService;

    private final ModelMapper modelMapper;

    private final ConverterApp converterApp;

    @Autowired
    public IngresoService(IIngresoRepository ingresoRepository, CuentaService cuentaService, ModelMapper modelMapper, ConverterApp converterApp) {
        this.ingresoRepository = ingresoRepository;
        this.cuentaService = cuentaService;
        this.modelMapper = modelMapper;
        this.converterApp = converterApp;
    }




    @Override
    public GenericResponseDTO crear(IngresoDTO ingresoDTO) throws Exception {
        try {

            IngresoDAO ingresoDAO = converterApp.ingresoDTOtoDAO(ingresoDTO, modelMapper);

            cuentaService.actualizarSaldo(CuentaDAO.builder()
                    .id(ingresoDTO.getIdCuenta().getId())
                            .saldo(ingresoDTO.getValor())
                    .build());

            ingresoRepository.save(ingresoDAO);

            IngresoDTO inversionDTOSalida = converterApp.ingresoDAOtoDTO(ingresoDAO, modelMapper);

            return GenericResponseDTO.builder().message("Se ha creado el ingreso")
                    .objectResponse(inversionDTOSalida).statusCode(HttpStatus.OK.value()).build();


        }catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al registrar la inversion").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public GenericResponseDTO consultarPorId(BigDecimal id) throws Exception {
        try {

            Optional<IngresoDAO> buscar = ingresoRepository.findById(id);

            if (buscar.isEmpty()) {
                return GenericResponseDTO.builder().message("El id N°"+ id +" del ingreso que ha ingresado  no existe").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            } else{
                return GenericResponseDTO.builder().message("Consulta ingreso por id: "+ id +" realizada con exito").objectResponse(buscar).statusCode(HttpStatus.OK.value()).build();
            }

        }catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error consultando el egreso").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public GenericResponseDTO eliminarPorId(BigDecimal id) throws Exception {
        try {

            Optional<IngresoDAO> buscar = ingresoRepository.findById(id);

            if (buscar.isEmpty()) {
                return GenericResponseDTO.builder().message("El id N°"+ id +" del ingreso que ha ingresado no existe").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            } else{
                ingresoRepository.deleteById(id);
                return GenericResponseDTO.builder().message("Eliminaado exitoso").objectResponse(id).statusCode(HttpStatus.OK.value()).build();
            }

        }catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al consultar persona").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }

    }
    }

