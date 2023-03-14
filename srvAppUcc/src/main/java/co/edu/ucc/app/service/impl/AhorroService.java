package co.edu.ucc.app.service.impl;

import co.edu.ucc.app.commons.converter.ConverterApp;
import co.edu.ucc.app.modeloCanonico.dto.AhorroDTO;
import co.edu.ucc.app.modeloCanonico.dto.EgresoDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;
import co.edu.ucc.app.modeloCanonico.entities.AhorroDAO;
import co.edu.ucc.app.modeloCanonico.entities.EgresoDAO;
import co.edu.ucc.app.repository.IAhorroRepository;
import co.edu.ucc.app.service.IAhorroService;
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
public class AhorroService implements IAhorroService {


    private static final Logger logger = LoggerFactory.getLogger(AhorroService.class);

    private final IAhorroRepository iAhorroRepository;

    private final ModelMapper modelMapper;

    private final ConverterApp converterApp;

    @Autowired
    public AhorroService(IAhorroRepository iAhorroRepository, ModelMapper modelMapper, ConverterApp converterApp) {
        this.iAhorroRepository = iAhorroRepository;
        this.modelMapper = modelMapper;
        this.converterApp = converterApp;
    }




    @Override
    @Transactional
    public GenericResponseDTO crearEgreso(AhorroDTO ahorroDTO) throws Exception {

        try {

            AhorroDAO ahorroDAO = converterApp.ahorroDTOtoDAO(ahorroDTO, modelMapper);


            iAhorroRepository.save(ahorroDAO);

            AhorroDTO ahorroDTOSalida = converterApp.ahorroDAOtoDTO(ahorroDAO, modelMapper);

            return GenericResponseDTO.builder().message("Se ha guardado el egreso exitosamente")
                    .objectResponse(ahorroDTOSalida).statusCode(HttpStatus.OK.value()).build();


        }catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al registrar egreso").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }

    }


    @Override
    public GenericResponseDTO consultarPorId(BigDecimal id) throws Exception {

        try {

            Optional<AhorroDAO> buscar = iAhorroRepository.findById(id);

            if (buscar.isEmpty()) {
                return GenericResponseDTO.builder().message("El id N°"+ id +" del ahorro que ha ingresado  no existe").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            } else{
                return GenericResponseDTO.builder().message("Consulta igreso por id: "+ id +" realizada con exito").objectResponse(buscar).statusCode(HttpStatus.OK.value()).build();
        }

        }catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error consultando el ahorro").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public GenericResponseDTO eliminarPorId(BigDecimal id) throws Exception {

        try {

            Optional<AhorroDAO> buscar = iAhorroRepository.findById(id);

            if (buscar.isEmpty()) {
                return GenericResponseDTO.builder().message("El id N°"+ id +" del ahorro que ha ingresado no existe").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            } else{
                iAhorroRepository.deleteById(id);
                return GenericResponseDTO.builder().message("Eliminaado exitoso").objectResponse(id).statusCode(HttpStatus.OK.value()).build();
            }

        }catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al consultar ahorro").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }

    }
}
