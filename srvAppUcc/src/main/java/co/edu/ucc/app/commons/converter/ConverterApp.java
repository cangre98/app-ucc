package co.edu.ucc.app.commons.converter;


import co.edu.ucc.app.modeloCanonico.dto.*;
import co.edu.ucc.app.modeloCanonico.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;


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


    public EgresoDAO egresoDTOtoDAO(EgresoDTO egresoDTO){
        EgresoDAO egresoDAO = new EgresoDAO();

        CuentaDAO cuentaDAO = new CuentaDAO();
        cuentaDAO.setId(egresoDTO.getCuenta().getId());

        GastoDAO gastoDAO = new GastoDAO();
        gastoDAO.setId(egresoDTO.getGasto().getId());

        egresoDAO.setDetalle(egresoDTO.getDetalle());
        egresoDAO.setValor(egresoDTO.getValor());
        egresoDAO.setFecha(new Date());
        egresoDAO.setIdgasto(gastoDAO);
        egresoDAO.setIdCuenta(cuentaDAO);
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

    public AhorroDTO ahorroDAOtoDTO(AhorroDAO ahorroDAO, ModelMapper modelMapper){
        AhorroDTO ahorroDTO = new AhorroDTO();
        modelMapper.map(ahorroDAO, ahorroDTO);
        return ahorroDTO;
    }

    public AhorroDAO ahorroDTOtoDAO(AhorroDTO ahorroDTO, ModelMapper modelMapper){
        AhorroDAO ahorroDAO = new AhorroDAO();
        modelMapper.map(ahorroDTO, ahorroDAO);
        return ahorroDAO;
    }

    public InversionDTO inversionDAOtoDTO(InversionDAO inversionDAO, ModelMapper modelMapper){
        InversionDTO inversionDTO = new InversionDTO();
        modelMapper.map(inversionDAO, inversionDTO);
        return inversionDTO;
    }

    public InversionDAO inversionDTOtoDAO(InversionDTO inversionDTO, ModelMapper modelMapper){
        InversionDAO inversionDAO = new InversionDAO();
        modelMapper.map(inversionDTO, inversionDAO);
        return inversionDAO;
    }

    public IngresoDTO ingresoDAOtoDTO(IngresoDAO ingresoDAO, ModelMapper modelMapper){
        IngresoDTO ingresoDTO = new IngresoDTO();
        modelMapper.map(ingresoDAO, ingresoDTO);
        return ingresoDTO;
    }

    public IngresoDAO ingresoDTOtoDAO(IngresoDTO ingresoDTO, ModelMapper modelMapper){
        IngresoDAO ingresoDAO = new IngresoDAO();
        modelMapper.map(ingresoDTO, ingresoDAO);
        return ingresoDAO;
    }

}
