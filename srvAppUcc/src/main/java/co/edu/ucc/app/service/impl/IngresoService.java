package co.edu.ucc.app.service.impl;

import co.edu.ucc.app.commons.converter.ConverterApp;
import co.edu.ucc.app.modeloCanonico.dto.IngresoDTO;
import co.edu.ucc.app.modeloCanonico.dto.generic.GenericResponseDTO;
import co.edu.ucc.app.modeloCanonico.entities.CuentaDAO;
import co.edu.ucc.app.modeloCanonico.entities.IngresoDAO;
import co.edu.ucc.app.repository.IIngresoRepository;
import co.edu.ucc.app.service.IIngresoService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IngresoService implements IIngresoService {

    private static final Logger logger = LoggerFactory.getLogger(IngresoService.class);

    private final IIngresoRepository ingresoRepository;

    private final CuentaService cuentaService;

    private final ModelMapper modelMapper;

    private final ConverterApp converterApp;

    @Autowired
    public IngresoService(IIngresoRepository ingresoRepository, CuentaService cuentaService, ModelMapper modelMapper, ConverterApp converterApp) {
        this.ingresoRepository = ingresoRepository;
        this.cuentaService = cuentaService;
        this.modelMapper = modelMapper;
        this.converterApp = converterApp;
    }


    @Override
    public GenericResponseDTO crear(IngresoDTO ingresoDTO) throws Exception {
        try {
            LocalDate localDate = LocalDate.now();
            ingresoDTO.setFechaIngreso(Date.from(localDate.atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant()));;
            IngresoDAO ingresoDAO = converterApp.ingresoDTOtoDAO(ingresoDTO, modelMapper);

            cuentaService.actualizarSaldo(CuentaDAO.builder()
                    .id(ingresoDTO.getIdCuenta().getId())
                    .saldo(ingresoDTO.getValor())
                    .build(), true);

            ingresoRepository.save(ingresoDAO);

            IngresoDTO inversionDTOSalida = converterApp.ingresoDAOtoDTO(ingresoDAO, modelMapper);

            return GenericResponseDTO.builder().message("Se ha creado el ingreso")
                    .objectResponse(inversionDTOSalida).statusCode(HttpStatus.OK.value()).build();


        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al registrar la inversion").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public GenericResponseDTO consultarPorId(BigDecimal id) throws Exception {
        try {

            Optional<IngresoDAO> buscar = ingresoRepository.findById(id);

            if (buscar.isEmpty()) {
                return GenericResponseDTO.builder().message("El id N°" + id + " del ingreso que ha ingresado  no existe").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            } else {
                return GenericResponseDTO.builder().message("Consulta ingreso por id: " + id + " realizada con exito").objectResponse(buscar).statusCode(HttpStatus.OK.value()).build();
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error consultando el egreso").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public GenericResponseDTO consultarHistoricoFecha(String json) throws Exception {
        try {
            JSONParser parser = new JSONParser();
            JSONObject objRequest = (JSONObject) parser.parse(json);
            String anio = (String) objRequest.get("anio") != null ? (String) objRequest.get("anio"):"0" ;
            String mes = ((String) objRequest.get("mes") != null && (String) objRequest.get("mes")!="" ) ? (String) objRequest.get("mes"):"0" ;
           Integer years = Integer.valueOf(anio) ;
           Integer  moth = Integer.valueOf(mes) ;
            List<IngresoDAO> consultarHistoricoPorAnio = new ArrayList<>();
            if (years > 0 && moth == 0) {
                consultarHistoricoPorAnio = ingresoRepository.consultarFechaPorAnnio(years);
            } else if (moth > 0) {
                consultarHistoricoPorAnio = ingresoRepository.consultarFechaPorAnnioYMes(years, moth);
            }
            if (consultarHistoricoPorAnio != null) {
                return GenericResponseDTO.builder().message("Consulta ingreso por anio: " + anio+ " realizada con exito").objectResponse(consultarHistoricoPorAnio).statusCode(HttpStatus.OK.value()).build();
            }else {
                JSONObject respuesta = new JSONObject();
                respuesta.put("mensaje", "no existen ingresos  asociados al año".concat(anio.toString()));

                return GenericResponseDTO.builder().message("El anio" + anio + " del ingreso que ha ingresado  no existe").objectResponse(objRequest).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            }



        }catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error consultando el ingreso").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }

    }

    @Override
    public GenericResponseDTO sumaIngresosIdCuenta(BigDecimal id) throws Exception {
        try {

            BigDecimal sumaIngresosIdCuenta = ingresoRepository.sumaIngresosIdCuenta(id);


            return GenericResponseDTO.builder().message("Consulta ingreso por id: " + id + " realizada con exito").objectResponse(sumaIngresosIdCuenta).statusCode(HttpStatus.OK.value()).build();

        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error consultando el egreso").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public GenericResponseDTO eliminarPorId(BigDecimal id) throws Exception {
        try {

            Optional<IngresoDAO> buscar = ingresoRepository.findById(id);

            if (buscar.isEmpty()) {
                return GenericResponseDTO.builder().message("El id N°" + id + " del ingreso que ha ingresado no existe").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            } else {
                ingresoRepository.deleteById(id);
                return GenericResponseDTO.builder().message("Eliminaado exitoso").objectResponse(id).statusCode(HttpStatus.OK.value()).build();
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al consultar persona").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }

    }
}

