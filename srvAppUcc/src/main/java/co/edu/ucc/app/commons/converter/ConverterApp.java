package co.edu.ucc.app.commons.converter;


import co.edu.ucc.app.modeloCanonico.dto.*;
import co.edu.ucc.app.modeloCanonico.entities.*;
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

    public CuentaDTO cuentaDAOtoDTO(CuentaDAO cuentaDAO, ModelMapper modelMapper){
        CuentaDTO cuentaDTO = new CuentaDTO();
        modelMapper.map(cuentaDAO, cuentaDTO);
        return cuentaDTO;
    }

    public CuentaDAO cuentaDTOtoDAO(CuentaDTO cuentaDTO, ModelMapper modelMapper) {
        CuentaDAO cuentaDAO = new CuentaDAO();
        modelMapper.map(cuentaDTO, cuentaDAO);
        return cuentaDAO;
    }

    public EgresoDTO egresoDAOtoDTO(EgresoDAO egresoDAO, ModelMapper modelMapper){
        EgresoDTO egresoDTO = new EgresoDTO();
        modelMapper.map(egresoDAO, egresoDTO);
        return egresoDTO;
    }

    public EgresoDAO egresoDTOtoDAO(EgresoDTO egresoDTO, ModelMapper modelMapper){
        EgresoDAO egresoDAO = new EgresoDAO();
        modelMapper.map(egresoDTO, egresoDAO);
        return egresoDAO;
    }

    public GastoDTO gastoDAOtoDTO(GastoDAO gastoDAO, ModelMapper modelMapper){
        GastoDTO gastoDTO = new GastoDTO();
        modelMapper.map(gastoDAO, gastoDTO);
        return gastoDTO;
    }

    public GastoDAO gastoDTOtoDAO(GastoDTO gastoDTO, ModelMapper modelMapper){
        GastoDAO gastoDAO = new GastoDAO();
        modelMapper.map(gastoDTO, gastoDAO);
        return gastoDAO;
    }

}
