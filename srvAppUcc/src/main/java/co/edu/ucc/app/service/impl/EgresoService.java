package co.edu.ucc.app.service.impl;

import co.edu.ucc.app.commons.converter.ConverterApp;
import co.edu.ucc.app.modeloCanonico.dto.EgresoDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;
import co.edu.ucc.app.modeloCanonico.entities.EgresoDAO;
import co.edu.ucc.app.modeloCanonico.entities.PersonaDAO;
import co.edu.ucc.app.repository.IEgresoRepository;
import co.edu.ucc.app.service.IEgresoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class EgresoService implements IEgresoService {


    private static final Logger logger = LoggerFactory.getLogger(EgresoService.class);

    private final IEgresoRepository iEgresoRepository;

    private final ModelMapper modelMapper;

    private final ConverterApp converterApp;

    @Autowired
    public EgresoService(IEgresoRepository iEgresoRepository, ModelMapper modelMapper, ConverterApp converterApp) {
        this.iEgresoRepository = iEgresoRepository;
        this.modelMapper = modelMapper;
        this.converterApp = converterApp;
    }

    @Override
    @Transactional
    public GenericResponseDTO crearEgreso(EgresoDTO egresoDTO) throws Exception {

        try {

        EgresoDAO egresoDAO = converterApp.egresoDTOtoDAO(egresoDTO, modelMapper);


            iEgresoRepository.save(egresoDAO);

            EgresoDTO egresoDTOSalida = converterApp.egresoDAOtoDTO(egresoDAO, modelMapper);

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

            Optional<EgresoDAO> buscar = iEgresoRepository.findById(id);

            if (buscar.isEmpty()) {
                return GenericResponseDTO.builder().message("El id N°"+ id +" del egreso que ha ingresado  no existe").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            } else{
                return GenericResponseDTO.builder().message("Consulta igreso por id: "+ id +" realizada con exito").objectResponse(buscar).statusCode(HttpStatus.OK.value()).build();
        }

        }catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error consultando el egreso").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public GenericResponseDTO eliminarPorId(BigDecimal id) throws Exception {

        try {

            Optional<EgresoDAO> buscar = iEgresoRepository.findById(id);

            if (buscar.isEmpty()) {
                return GenericResponseDTO.builder().message("El id N°"+ id +" del egreso que ha ingresado no existe").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            } else{
                iEgresoRepository.deleteById(id);
                return GenericResponseDTO.builder().message("Eliminaado exitoso").objectResponse(id).statusCode(HttpStatus.OK.value()).build();
            }

        }catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al consultar persona").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }

    }
}
