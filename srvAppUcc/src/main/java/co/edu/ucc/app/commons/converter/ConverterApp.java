package co.edu.ucc.app.commons.converter;


import co.edu.ucc.app.modeloCanonico.dto.PersonaDTO;
import co.edu.ucc.app.modeloCanonico.dto.UsuarioDTO;
import co.edu.ucc.app.modeloCanonico.entities.PersonaDAO;
import co.edu.ucc.app.modeloCanonico.entities.UsuarioDAO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class ConverterApp {


    public UsuarioDTO usuarioDAOtoDTO(UsuarioDAO usuarioDAO, ModelMapper modelMapper) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        modelMapper.map(usuarioDAO, usuarioDTO);
        return usuarioDTO;
    }

    public UsuarioDAO usuarioDTOtoDAO(UsuarioDTO usuarioDTO, ModelMapper modelMapper) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        modelMapper.map(usuarioDTO, usuarioDAO);
        return usuarioDAO;
    }

    public PersonaDTO personaDAOtoDTO(PersonaDAO personaDAO, ModelMapper modelMapper) {
        PersonaDTO personaDTO = new PersonaDTO();
        modelMapper.map(personaDAO, personaDTO);
        return personaDTO;
    }


    public PersonaDAO personaDTOtoDAO(PersonaDTO personaDTO, ModelMapper modelMapper) {
        PersonaDAO personaDAO = new PersonaDAO();
        modelMapper.map(personaDTO, personaDAO);
        return personaDAO;
    }

}
