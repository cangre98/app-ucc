package co.edu.ucc.app.modeloTransversal.utils;

import co.edu.ucc.app.modeloTransversal.estatico.MetodosGenericos;
import org.slf4j.Logger;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public final class MensajeGenerico {

    public static final String generarMensajeLog(String nombreMetodo, String objetoEntrada, HttpServletRequest request){
        return "\n[\n\tMetodo ["+nombreMetodo+"]\n\tObjeto ["+objetoEntrada+"]\n]\n";
    }

    public static final void generarMensajeEntradaLog(Logger logger,HttpServletRequest request, String nombreMetodo, String objetoEntrada){
        String ip = MetodosGenericos.getIpPublicaCliente(request);
        HashMap<String, String> variablesIngreso = MetodosGenericos.getInformacionNavegador(request);
        String mensajeIngreso = "\n[\n\tTipo        [Entrada]\n\tIp usuario  ["+ip+"]\n\tSO          ["+variablesIngreso.get("SO")+"]\n\tDispositivo ["+variablesIngreso.get("DISPOSITIVO")+"]\n\tNavegador   ["+variablesIngreso.get("NAVEGADOR")+"]\n\tVersion Nav ["+ (variablesIngreso.get("VERSION") !=null ? variablesIngreso.get("VERSION") :"No encontrado") +"]\n][\n\tMetodo ["+nombreMetodo+"]\n\tObjeto ["+objetoEntrada+"]\n]\n";
        logger.info(mensajeIngreso);
    }

    public static final void generarMensajeSalidaLog(Logger logger,HttpServletRequest request, String nombreMetodo, String objetoEntrada){
        String ip = MetodosGenericos.getIpPublicaCliente(request);
        HashMap<String, String> variablesIngreso = MetodosGenericos.getInformacionNavegador(request);
        String mensajeSalida = "\n[\n\tTipo        [Salida]\n\tIp usuario  ["+ip+"]\n\tSO          ["+variablesIngreso.get("SO")+"]\n\tDispositivo ["+variablesIngreso.get("DISPOSITIVO")+"]\n\tNavegador   ["+variablesIngreso.get("NAVEGADOR")+"]\n\tVersion Nav ["+ (variablesIngreso.get("VERSION") !=null ? variablesIngreso.get("VERSION") :"No encontrado") +"]\n][\n\tMetodo ["+nombreMetodo+"]\n\tObjeto ["+objetoEntrada+"]\n]\n";
        logger.info(mensajeSalida);
    }

    public static final Map generarMensajeErrorRequest(Logger logger, MethodArgumentNotValidException ex){
        Map<String, String> errores = MetodosGenericos.generarMensajeErrorRequest(ex);
        logger.error("Error de ingreso por Request \n[\n\t"+ "Mensaje: "+ex.getMessage()+"\n\tErrores:"+errores.toString()+"\n]");
        return errores;
    }

    public static String posicionXlsx (int fila, int columna, String hojaCalculo){
        return "Fila [".concat(String.valueOf(MetodosGenericos.generarNumeroFilaExcel(fila))).concat("]".concat(" Columna [".concat(MetodosGenericos.generarNombreColumnaExcel(columna)).concat("] ".concat(" Hoja [".concat(hojaCalculo).concat("]")))));
    }

    public static String posicionXlsx (String fila, String columna, String hojaCalculo){
        return "Fila [".concat(fila).concat("]".concat(" Columna [".concat(columna).concat("] ".concat(" Hoja [".concat(hojaCalculo).concat("]")))));
    }


}
