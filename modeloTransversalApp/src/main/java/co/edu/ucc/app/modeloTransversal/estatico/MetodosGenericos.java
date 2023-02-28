package co.edu.ucc.app.modeloTransversal.estatico;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.util.CellReference;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MetodosGenericos {

    public static String getIpPublicaCliente(HttpServletRequest request) {
        if (request.getHeader("X-Forwarded-For") != null) {
            return request.getHeader("X-Forwarded-For");
        } else {
            return request.getRemoteAddr();
        }
    }

    public static HashMap getInformacionNavegador(HttpServletRequest request) {
        HashMap<String, String> variablesIngreso = new HashMap();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        OperatingSystem agent = userAgent.getOperatingSystem();
        Browser browser = userAgent.getBrowser();
        Version version = userAgent.getBrowserVersion();

        if(agent != null) {
            variablesIngreso.put("SO", agent.getName());
            variablesIngreso.put("DISPOSITIVO", agent.getDeviceType().getName());
        }
        if(browser != null) {
            variablesIngreso.put("NAVEGADOR", browser.getName());
        }
        if(version != null) {
            variablesIngreso.put("VERSION", version.getVersion());
        }
        return variablesIngreso;
    }

    public static Map generarMensajeErrorRequest(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage()));
        return errores;
    }

    public static boolean validarExpresionRegular(String regex, String valor){
       return Pattern.matches(regex, valor);
    }


    public static BigDecimal diaSemanaNumero(String dia){
        BigDecimal diaReal = null;
        switch (dia){
            case "LUNES":
                diaReal = new BigDecimal("1");
                break;
            case "MARTES":
                diaReal = new BigDecimal("2");
                break;
            case "MIERCOLES":
                diaReal = new BigDecimal("3");
                break;
            case "JUEVES":
                diaReal = new BigDecimal("4");
                break;
            case "VIERNES":
                diaReal = new BigDecimal("5");
                break;
            case "SABADO":
                diaReal = new BigDecimal("6");
                break;
            case "DOMINGO":
                diaReal = new BigDecimal("7");
                break;
            default:
                diaReal = null;
                break;
        }
        return diaReal;
    }

    public static boolean validarBigDecimalEntero(BigDecimal numero){
        System.out.println("numero "+numero);
        System.out.println("numero "+new BigDecimal(numero.intValue()));
        System.out.println("numero "+numero.subtract(new BigDecimal(numero.intValue())));
        //return numero.subtract(new BigDecimal(numero.intValue())) == BigDecimal.ZERO;
        return numero.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0;
    }


    public static boolean noEsNulo(Object objeto) {
        return objeto != null;
    }

    public static boolean noEsNuloVacio(String objeto) {
        return objeto != null && objeto.trim().length() > 0;
    }

    public static boolean fechaMenorIgual(Date fechaMenor, Date fechaMayor) {
        if (noEsNulo(fechaMenor) && noEsNulo(fechaMayor)) {
            return fechaMenor.getTime() <= fechaMayor.getTime();
        } else {
            return false;
        }
    }

    public static String cambiarFormatoFecha(Date fecha, String formato) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(formato);
        return dateFormatter.format(fecha);
    }

    public static Date eliminarHora(Date fecha) {
        if(fecha != null) {
            return DateUtils.truncate(fecha, Calendar.DATE);
        }else{
            return null;
        }
    }

    public static boolean entreFechas(Date fechaMenor, Date fechaMayor, Date fechaComparar) {
        if (noEsNulo(fechaMenor) && noEsNulo(fechaMayor) && noEsNulo(fechaComparar)) {
            return fechaMenor.getTime() <= fechaComparar.getTime() && fechaMayor.getTime() >= fechaComparar.getTime();
        } else {
            return false;
        }
    }

    public static boolean entreHoras (String horaMenor, String horaMayor, String horaComparar){
        try {
            if (noEsNuloVacio(horaMenor) && noEsNuloVacio(horaMayor) && noEsNuloVacio(horaComparar)) {
                DateFormat formatoHora = new SimpleDateFormat("HH:mm");
                Date horaInicial = formatoHora.parse(horaMenor);
                Date horaFinal = formatoHora.parse(horaMayor);
                Date horaPrueba = formatoHora.parse(horaComparar);
                return horaInicial.getTime() <= horaPrueba.getTime() && horaFinal.getTime() >= horaPrueba.getTime();
            } else {
                return true;
            }
        }catch (Exception e){
            return true;
        }
    }

    public static Date generarHora(String hora){
        Date fecha = null;
        try {
            if (noEsNuloVacio(hora)) {
                DateFormat formatoHora = new SimpleDateFormat("HH:mm");
                fecha = formatoHora.parse(hora);
            }
        }catch (Exception e){

        }
        return fecha;
    }

    public static boolean fechaMenor(Date fechaMenor, Date fechaMayor) {
        if (noEsNulo(fechaMenor) && noEsNulo(fechaMayor)) {
            return fechaMenor.getTime() < fechaMayor.getTime();
        } else {
            return false;
        }
    }

    public static String generarNumeroFilaExcel(int numeroFila){
        return String.valueOf((numeroFila+1));
    }

    public static String generarNombreColumnaExcel(int numeroColumna){
        return CellReference.convertNumToColString(numeroColumna);
    }

    public static boolean numeroMinimoMaximo(int numero, int minimo, int maximo) {
        return numero >= minimo && numero <= maximo;
    }

    public static boolean esNumerico(String valor) {
        String pattern = "^[0-9]*$";
        return valor.matches(pattern);

    }



    public static boolean validarFechaObjet(Object fecha){
        System.out.println("fecha "+fecha);
        Date fechaTranformacion = (Date) fecha;
        return true;
    }
}
