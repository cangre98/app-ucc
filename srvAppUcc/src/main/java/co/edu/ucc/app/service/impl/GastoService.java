package co.edu.ucc.app.service.impl;

import co.edu.ucc.app.commons.converter.ConverterApp;
import co.edu.ucc.app.modeloCanonico.dto.GastoDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;
import co.edu.ucc.app.modeloCanonico.entities.GastoDAO;
import co.edu.ucc.app.repository.IGastoRepository;
import co.edu.ucc.app.service.IGastoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class GastoService implements IGastoService {

    private static final Logger logger = LoggerFactory.getLogger(GastoService.class);

    private final IGastoRepository iGastoRepository;

    private final ModelMapper modelMapper;

    private final ConverterApp converterApp;

    @Autowired
    public GastoService(IGastoRepository iGastoRepository, ModelMapper modelMapper, ConverterApp converterApp) {
        this.iGastoRepository = iGastoRepository;
        this.modelMapper = modelMapper;
        this.converterApp = converterApp;
    }

    @Override
    public GenericResponseDTO crearGasto(GastoDTO gastoDTO) throws Exception {
        try {

            GastoDAO gastoDAO = converterApp.gastoDTOtoDAO(gastoDTO, modelMapper);


            iGastoRepository.save(gastoDAO);

            GastoDTO egresoDTOSalida = converterApp.gastoDAOtoDTO(gastoDAO, modelMapper);

            return GenericResponseDTO.builder().message("Se ha guardado el egreso exitosamente")
                    .objectResponse(egresoDTOSalida).statusCode(HttpStatus.OK.value()).build();


        }catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al registrar egreso").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public GenericResponseDTO consultarPorId(BigDecimal id) throws Exception {
        try {

            Optional<GastoDAO> buscar = iGastoRepository.findById(id);

            if (buscar.isEmpty()) {
                return GenericResponseDTO.builder().message("El id N°"+ id +" del gasto que ha ingresado  no existe").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            } else{
                return GenericResponseDTO.builder().message("Consulta gasto por id: "+ id +" realizada con exito").objectResponse(buscar).statusCode(HttpStatus.OK.value()).build();
            }

        }catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error consultando el egreso").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public GenericResponseDTO eliminarPorId(BigDecimal id) throws Exception {
        try {

            Optional<GastoDAO> buscar = iGastoRepository.findById(id);

            if (buscar.isEmpty()) {
                return GenericResponseDTO.builder().message("El id N°"+ id +" del gasto que ha ingresado no existe").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            } else{
                iGastoRepository.deleteById(id);
                return GenericResponseDTO.builder().message("Eliminaado exitoso").objectResponse(id).statusCode(HttpStatus.OK.value()).build();
            }

        }catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al consultar persona").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }

    }
    }

