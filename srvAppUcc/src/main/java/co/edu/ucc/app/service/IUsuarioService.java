package co.edu.ucc.app.service;


import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;
import co.edu.ucc.app.modeloCanonico.dto.UsuarioDTO;
import co.edu.ucc.app.modeloCanonico.entities.UsuarioDAO;

import java.math.BigDecimal;

public interface IUsuarioService {

    GenericResponseDTO crear(UsuarioDTO usuarioDTO) throws Exception;

    GenericResponseDTO login(UsuarioDTO usuarioDTO) throws Exception;
    GenericResponseDTO consultarPorId(BigDecimal entrada) throws Exception;



}
