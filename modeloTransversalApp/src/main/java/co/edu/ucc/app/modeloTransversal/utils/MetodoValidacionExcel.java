package co.edu.ucc.app.modeloTransversal.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

public class MetodoValidacionExcel {

    public static boolean validarTipoDatoCelda(Cell cell, ConstantEnums.TipoDataExcel tipo){
        boolean resultado = false;
        switch (tipo){
            case TEXT:
                resultado = cell.getCellType().equals(CellType.STRING);
                break;
            case NUMERICO:
                resultado = cell.getCellType().equals(CellType.NUMERIC) && !DateUtil.isCellDateFormatted(cell);
                break;
            case FECHA:
                resultado = cell.getCellType().equals(CellType.NUMERIC) && DateUtil.isCellDateFormatted(cell);
                break;
            case TEXTO_NUMERO:
                resultado = cell.getCellType().equals(CellType.STRING) || (cell.getCellType().equals(CellType.NUMERIC) && !DateUtil.isCellDateFormatted(cell));
                break;
        }
        return resultado;
    }

    /*public static boolean validarCeldaVacia(Cell cell){
        CellType cellType = cell.getCellType().equals(CellType.FORMULA)
                ? cell.getCachedFormulaResultType() : cell.getCellType();
        Object valor = null;

        if (cellType.equals(CellType.STRING)) {
            //System.out.print(cell.getStringCellValue() + " | ");
            valor = cell.getStringCellValue();
        }
        if (cellType.equals(CellType.NUMERIC)) {
            if (DateUtil.isCellDateFormatted(cell)) {
                //System.out.print(cell.getDateCellValue() + " | ");
                valor = cell.getDateCellValue();
            } else {
                //System.out.print(cell.getNumericCellValue() + " | ");
                valor = cell.getNumericCellValue();
            }
        }
        if (cellType.equals(CellType.BOOLEAN)) {
            //System.out.print(cell.getBooleanCellValue() + " | ");
            valor = cell.getBooleanCellValue();
        }
        if (cellType.equals(CellType.BLANK)) {
            //System.out.print("vacio" + " | ");
            valor = null;
        }
        return valor == null || valor.toString().trim().length()==0;
    }*/

    public static Object getValorCelda(Cell cell, ConstantEnums.TipoDataExcel tipo){
        Object resultado = null;
        switch (tipo){
            case TEXT:
                resultado = cell.getStringCellValue();
                break;
            case NUMERICO:
                resultado = cell.getNumericCellValue();
                break;
            case FECHA:
                resultado = cell.getDateCellValue();
                break;
            case TEXTO_NUMERO:
                if(validarTipoDatoCelda(cell, ConstantEnums.TipoDataExcel.TEXT)){
                    resultado = cell.getStringCellValue();
                }else{
                    resultado = cell.getNumericCellValue();
                }
                break;
        }
        return resultado;

    }

    /*public static ValidacionDTO crearObjetoValidacionExcelDTO (String tipo, String descripcion, String contenidoDetalle, int fila, int columna, String hoja){
        return ValidacionDTO.builder().tipoValidacion(tipo).descripcion(descripcion).contenido(contenidoDetalle).ubicacion(MensajeGenerico.posicionXlsx(fila, columna, hoja)).build();
    }

    public static ValidacionDTO validarCeldaTextoEstatico(Cell cell, String texto, String hoja){
        ValidacionDTO validacionDTO= null;
        if(!validarTipoDatoCelda(cell,ConstantEnums.TipoDataExcel.TEXT)){
            validacionDTO = crearObjetoValidacionExcelDTO("ERROR","El campo no puede ser vacio y debe ser [".concat(texto).concat("]"),null,cell.getRowIndex(), cell.getColumnIndex(), hoja);
        }else if(!getValorCelda(cell,ConstantEnums.TipoDataExcel.TEXT).equals(texto)){
            validacionDTO = crearObjetoValidacionExcelDTO("ERROR","El campo debe ser [".concat(texto).concat("]"),cell.getStringCellValue(),cell.getRowIndex(), cell.getColumnIndex(), hoja);
        }
        return validacionDTO;
    }*/

}
