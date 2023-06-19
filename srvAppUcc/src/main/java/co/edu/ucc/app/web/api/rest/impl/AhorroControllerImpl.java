package co.edu.ucc.app.web.api.rest.impl;

import co.edu.ucc.app.modeloCanonico.dto.AhorroDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;
import co.edu.ucc.app.modeloTransversal.utils.MensajeGenerico;
import co.edu.ucc.app.service.IAhorroService;
import co.edu.ucc.app.web.api.rest.IAhorroController;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/ahorro")
@CrossOrigin
public class AhorroControllerImpl implements IAhorroController {

    private static final Logger logger = LoggerFactory.getLogger(AhorroControllerImpl.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private IAhorroService ahorroService;


    @Override
    @PostMapping(path = "/crear", produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "crear", notes = "notas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login Ok", response = GenericResponseDTO.class)
    })
    public ResponseEntity<GenericResponseDTO> crear(@Valid @RequestBody AhorroDTO ahorroDTO, HttpServletRequest request) throws Exception {

        try {

        GenericResponseDTO salida = ahorroService.crearEgreso(ahorroDTO);

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
            GenericResponseDTO salida = ahorroService.consultarPorId(id);
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


    @Override
    @DeleteMapping(path = "/eliminarPorId/{id}", produces = "application/json")
    @ApiOperation(value = "Consultar la descripcion proceso por id", notes = "notas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La consulta de descripcion proceso fue exitosa", response = GenericResponseDTO.class)
    })
    public ResponseEntity<GenericResponseDTO> eliminarPorId(@PathVariable(value = "id", required = true) BigDecimal id,  HttpServletRequest request) throws Exception {
        try {
            MensajeGenerico.generarMensajeEntradaLog(logger, request, "eliminarPorId", null);
            GenericResponseDTO salida = ahorroService.eliminarPorId(id);
            MensajeGenerico.generarMensajeSalidaLog(logger, request, "eliminarPorId", mapper.writeValueAsString(salida));

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

    public static void main(String[] args) {
        System.out.println(new BigDecimal("4545").setScale(2, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(new BigDecimal("0"));


    }
}
