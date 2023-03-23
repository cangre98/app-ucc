package co.edu.ucc.app.service.impl;


import co.edu.ucc.app.commons.converter.ConverterApp;
import co.edu.ucc.app.modeloCanonico.dto.PersonaDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;
import co.edu.ucc.app.modeloCanonico.dto.UsuarioDTO;
import co.edu.ucc.app.modeloCanonico.entities.UsuarioDAO;
import co.edu.ucc.app.repository.IUsuarioRepository;

import co.edu.ucc.app.service.IUsuarioService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.*;


@Service
public class UsuarioService implements IUsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    private final IUsuarioRepository iUsuarioRepository;

    private final PersonaService personaService;

    private final ModelMapper modelMapper;
    private final ConverterApp converterApp;


    @Autowired
    public UsuarioService(IUsuarioRepository iUsuarioRepository, PersonaService personaService, ModelMapper modelMapper, ConverterApp converterApp) {

        this.iUsuarioRepository = iUsuarioRepository;
        this.personaService = personaService;
        this.modelMapper = modelMapper;
        this.converterApp = converterApp;
    }

    public GenericResponseDTO crear(UsuarioDTO usuarioDTO) throws Exception {
        try {

            GenericResponseDTO persona = personaService.crear(usuarioDTO.getIdPersona());

            usuarioDTO.setClave(DigestUtils.md5Hex(usuarioDTO.getClave()));
            usuarioDTO.setFechaCreacion(new Date());
            usuarioDTO.setFechaCambioClave(new Date());
            usuarioDTO.setIdPersona((PersonaDTO) persona.getObjectResponse());

            UsuarioDAO usuarioDAO = converterApp.usuarioDTOtoDAO(usuarioDTO, modelMapper);

            iUsuarioRepository.save(usuarioDAO);

            UsuarioDTO response = converterApp.usuarioDAOtoDTO(usuarioDAO, modelMapper);


            return GenericResponseDTO.builder().message("Usuario registrado con exito").objectResponse(response).statusCode(HttpStatus.OK.value()).build();

        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al registrar usuario").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }


    public GenericResponseDTO login(UsuarioDTO usuarioDTO) throws Exception {
        try {

            UsuarioDAO usuarioDAO = iUsuarioRepository.login(usuarioDTO.getCorreo(), DigestUtils.md5Hex(usuarioDTO.getClave()));

            UsuarioDTO response = converterApp.usuarioDAOtoDTO(usuarioDAO, modelMapper);

            return GenericResponseDTO.builder().message("Login con exito").objectResponse(response).statusCode(HttpStatus.OK.value()).build();

        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error Login").objectResponse(null).statusCode(HttpStatus.UNAUTHORIZED.value()).build();
        }
    }

    @Override
    public GenericResponseDTO consultarPorId(BigDecimal id) throws Exception {
        try {

            Optional<UsuarioDAO> usuarioDAO = iUsuarioRepository.findById(id);

            if (usuarioDAO.isEmpty()) {
                return GenericResponseDTO.builder().message("El id de la persona ingresado no existe").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            }

            UsuarioDTO response = converterApp.usuarioDAOtoDTO(usuarioDAO.get(), modelMapper);

            return GenericResponseDTO.builder().message("Consulta el Usuario por id realizada con exito").objectResponse(response).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al consultar el Usuario por id registrada en el sistema").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }
}
