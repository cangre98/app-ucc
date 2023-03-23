package co.edu.ucc.app.web.api.rest.impl;


import co.edu.ucc.app.modeloCanonico.dto.UsuarioDTO;
import co.edu.ucc.app.modeloCanonico.entities.UsuarioDAO;
import co.edu.ucc.app.modeloTransversal.utils.MensajeGenerico;
import co.edu.ucc.app.service.IUsuarioService;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;
import co.edu.ucc.app.web.api.rest.IUsuarioController;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/v1/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController implements IUsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private IUsuarioService estadoService;


    @Override
    @PostMapping(path = "/crear", produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "crear", notes = "notas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login Ok", response = GenericResponseDTO.class)
    })
    public ResponseEntity<GenericResponseDTO> crear(@Valid @RequestBody UsuarioDTO usuarioDTO, HttpServletRequest request) throws Exception {
        try {

            GenericResponseDTO salida = estadoService.crear(usuarioDTO);

            MensajeGenerico.generarMensajeSalidaLog(logger, request, "login", mapper.writeValueAsString(salida));
            return new ResponseEntity(
                    salida, HttpStatus.valueOf(200)
            );

        } catch (ResponseStatusException | HttpClientErrorException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se encuentra informacion");

        } catch (Exception e) {
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Ocurrio un error inesperado");
        }
    }

    @Override
    @PostMapping(path = "/login", produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "Login", notes = "notas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login Ok", response = GenericResponseDTO.class)
    })
    public ResponseEntity<GenericResponseDTO> login(@Valid @RequestBody UsuarioDTO usuarioDTO, HttpServletRequest request) throws Exception {
        try {

            GenericResponseDTO salida = estadoService.login(usuarioDTO);

            MensajeGenerico.generarMensajeSalidaLog(logger, request, "login", mapper.writeValueAsString(salida));
            return new ResponseEntity(
                    salida, HttpStatus.valueOf(200)
            );

        } catch (ResponseStatusException | HttpClientErrorException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se encuentra informacion");

        } catch (Exception e) {
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Ocurrio un error inesperado");
        }
    }

    @Override
    @GetMapping(path = "/consultarId/{id}", produces = "application/json")
    @ApiOperation(value = "Consultar la descripcion proceso por id", notes = "notas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La consulta de descripcion proceso fue exitosa", response = GenericResponseDTO.class)
    })
    public ResponseEntity<GenericResponseDTO> consultarId(@PathVariable(value = "id", required = true) BigDecimal id,  HttpServletRequest request) throws Exception {
        try {
            MensajeGenerico.generarMensajeEntradaLog(logger, request, "consultarId", null);
            GenericResponseDTO salida = estadoService.consultarPorId(id);
            MensajeGenerico.generarMensajeSalidaLog(logger, request, "consultarId", mapper.writeValueAsString(salida));

            return new ResponseEntity(
                    salida, HttpStatus.valueOf(200)
            );

        } catch (ResponseStatusException | HttpClientErrorException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se encuentra informacion");
        } catch (Exception e) {
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Ocurrio un error inesperado");
        }
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponseDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = MensajeGenerico.generarMensajeErrorRequest(logger, ex);
        return new ResponseEntity(
                GenericResponseDTO.builder().message("Error en los datos ingresados").objectResponse(errors).statusCode(HttpStatus.NOT_FOUND.value()).build(), HttpStatus.NOT_FOUND
        );
    }
}
