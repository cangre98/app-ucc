package co.edu.ucc.app.service.impl;

import co.edu.ucc.app.commons.converter.ConverterApp;
import co.edu.ucc.app.modeloCanonico.dto.GastoDTO;
import co.edu.ucc.app.modeloCanonico.dto.InversionDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;
import co.edu.ucc.app.modeloCanonico.entities.GastoDAO;
import co.edu.ucc.app.modeloCanonico.entities.InversionDAO;
import co.edu.ucc.app.repository.IGastoRepository;
import co.edu.ucc.app.repository.IInversionRepository;
import co.edu.ucc.app.service.IGastoService;
import co.edu.ucc.app.service.IInversionService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class InversionService implements IInversionService {

    private static final Logger logger = LoggerFactory.getLogger(InversionService.class);

    private final IInversionRepository iInversionService;

    private final ModelMapper modelMapper;

    private final ConverterApp converterApp;

    @Autowired
    public InversionService(IInversionRepository iInversionService, ModelMapper modelMapper, ConverterApp converterApp) {
        this.iInversionService = iInversionService;
        this.modelMapper = modelMapper;
        this.converterApp = converterApp;
    }


    @Override
    public GenericResponseDTO crear(InversionDTO inversionDTO) throws Exception {
        try {

            InversionDAO inversionDAO = converterApp.inversionDTOtoDAO(inversionDTO, modelMapper);


            iInversionService.save(inversionDAO);

            InversionDTO inversionDTOSalida = converterApp.inversionDAOtoDTO(inversionDAO, modelMapper);

            return GenericResponseDTO.builder().message("Se ha creado la inversion")
                    .objectResponse(inversionDTOSalida).statusCode(HttpStatus.OK.value()).build();


        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al registrar la inversion").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public GenericResponseDTO consultarPorId(BigDecimal id) throws Exception {
        try {

            Optional<InversionDAO> buscar = iInversionService.findById(id);

            if (buscar.isEmpty()) {
                return GenericResponseDTO.builder().message("El id N°" + id + " de la inversion que ha ingresado  no existe").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            } else {
                return GenericResponseDTO.builder().message("Consulta gasto por id: " + id + " realizada con exito").objectResponse(buscar).statusCode(HttpStatus.OK.value()).build();
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error consultando el egreso").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }


    @Override
    public GenericResponseDTO consultarTodos() throws Exception {
        try {

            List<InversionDAO> lista = iInversionService.findAll();


            return GenericResponseDTO.builder().message("Consulta inversion realizada con exito").objectResponse(lista).statusCode(HttpStatus.OK.value()).build();


        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error consultando el egreso").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public GenericResponseDTO eliminarPorId(BigDecimal id) throws Exception {
        try {

            Optional<InversionDAO> buscar = iInversionService.findById(id);

            if (buscar.isEmpty()) {
                return GenericResponseDTO.builder().message("El id N°" + id + " de la inversion que ha ingresado no existe").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            } else {
                iInversionService.deleteById(id);
                return GenericResponseDTO.builder().message("Eliminaado exitoso").objectResponse(id).statusCode(HttpStatus.OK.value()).build();
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al consultar persona").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }

    }
}

