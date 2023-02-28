package co.edu.ucc.app.service.impl;


import co.edu.ucc.app.commons.converter.ConverterApp;
import co.edu.ucc.app.modeloCanonico.dto.PersonaDTO;
import co.edu.ucc.app.modeloCanonico.dto.TipodocumentoDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;
import co.edu.ucc.app.modeloCanonico.entities.PersonaDAO;
import co.edu.ucc.app.modeloCanonico.entities.TipoPersonaDAO;
import co.edu.ucc.app.modeloCanonico.entities.TipodocumentoDAO;
import co.edu.ucc.app.repository.IPersonaRepository;
import co.edu.ucc.app.repository.ITipoDocumentoRepository;
import co.edu.ucc.app.repository.ITipoPersonaRepository;
import co.edu.ucc.app.service.IPersonaService;
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
public class PersonaService implements IPersonaService {

    private static final Logger logger = LoggerFactory.getLogger(PersonaService.class);

    private final IPersonaRepository iPersonaRepository;

    private final ITipoDocumentoRepository iTipoDocumentoRepository;
    private final ITipoPersonaRepository iTipoPersonaRepository;

    private final ModelMapper modelMapper;
    private final ConverterApp converterApp;


    @Autowired
    public PersonaService(IPersonaRepository iPersonaRepository, ITipoDocumentoRepository iTipoDocumentoRepository, ITipoPersonaRepository iTipoPersonaRepository, ModelMapper modelMapper, ConverterApp converterApp) {

        this.iPersonaRepository = iPersonaRepository;
        this.iTipoDocumentoRepository = iTipoDocumentoRepository;
        this.iTipoPersonaRepository = iTipoPersonaRepository;
        this.modelMapper = modelMapper;
        this.converterApp = converterApp;
    }

    public GenericResponseDTO crear(PersonaDTO personaDTO) throws Exception {
        try {

            PersonaDAO personaDAO = converterApp.personaDTOtoDAO(personaDTO, modelMapper);

            iPersonaRepository.save(personaDAO);

            PersonaDTO personaDTOSalida = converterApp.personaDAOtoDTO(personaDAO, modelMapper);


            return GenericResponseDTO.builder().message("Persona registrado con exito").objectResponse(personaDTOSalida).statusCode(HttpStatus.OK.value()).build();

        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al registrar persona").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }


    @Override
    public GenericResponseDTO consultarPorId(BigDecimal id) throws Exception {
        try {

            Optional<PersonaDAO> personaDAO = iPersonaRepository.findById(id);

            if (personaDAO.isEmpty()) {
                return GenericResponseDTO.builder().message("El id de la persona ingresado no existe").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            }
            return GenericResponseDTO.builder().message("Consulta persona por id realizada con exito").objectResponse(personaDAO).statusCode(HttpStatus.OK.value()).build();

        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al consultar persona").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }

    }

    @Override
    public GenericResponseDTO listarTiposPersona() throws Exception {
        try {

            List< TipoPersonaDAO> tipoPersonaDAOS = iTipoPersonaRepository.findAll();

            return GenericResponseDTO.builder().message("Consulta tipos Personas con exito").objectResponse(tipoPersonaDAOS).statusCode(HttpStatus.OK.value()).build();

        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al consultar tipos Personas").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }

    }


    @Override
    public GenericResponseDTO listarTiposDocumento() throws Exception {
        try {

            List<TipodocumentoDAO> tipodocumentoDAOS = iTipoDocumentoRepository.findAll();

            return GenericResponseDTO.builder().message("Consulta persona por id realizada con exito").objectResponse(tipodocumentoDAOS).statusCode(HttpStatus.OK.value()).build();

        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al consultar persona").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }

    }

}
