package co.edu.ucc.app.modeloTransversal.utils;

public final class Regex {

    public static final String REGEX_ACTIVO = "^[01]*$";
    public static final String REGEX_REQUIERE_FECHA_POSESION = "^(REQUERIDO|NO REQUERIDO|OPCIONAL)$";
    public static final String REGEX_REQUIERE_CATEGORIA = "^(SELECCION MANUAL|SELECCION AUTOMATICA|OPCIONAL|NO REQUERIDO)$";
    public static final String REGEX_ESTADO_VINCULACION_LABORAL = "^(ACTIVO|RETIRO TEMPORAL|RETIRO DEFINITIVO|ANULADO)$";

    public static final String DIA_SEMANA_LUNES_DOMINGO = "^(LUNES|MARTES|MIERCOLES|JUEVES|VIERNES|SABADO|DOMINGO)$";
}
