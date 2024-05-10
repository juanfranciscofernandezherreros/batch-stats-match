package com.fernandez.statsmatch.utils;

import java.io.File;

public class FileUtils {

    public static String obtenerParteNombre(String filePath) {
        // Obtener solo el nombre del archivo sin la ruta
        String nombreArchivo = new File(filePath).getName();

        // Quitar la extensión del archivo
        int lastIndex = nombreArchivo.lastIndexOf('.');
        if (lastIndex > 0) {
            nombreArchivo = nombreArchivo.substring(0, lastIndex);
        }

        // Extraer la parte específica que deseas (g_3_IHW1gRfp)
        int startIndex = nombreArchivo.indexOf("STATS_MATCH_") + "STATS_MATCH_".length();
        int endIndex = nombreArchivo.indexOf("_0");
        if (endIndex == -1) { // Si "_0" no está presente, tomar hasta el final
            endIndex = nombreArchivo.length();
        }
        return nombreArchivo.substring(startIndex, endIndex);
    }

    public static String obtenerUltimoCaracter(String filePath) {
        // Obtener solo el nombre del archivo sin la ruta
        String nombreArchivo = new File(filePath).getName();

        // Quitar la extensión del archivo
        int lastIndex = nombreArchivo.lastIndexOf('.');
        if (lastIndex > 0) {
            nombreArchivo = nombreArchivo.substring(0, lastIndex);
        }

        // Obtener el último carácter del nombre del archivo
        if (!nombreArchivo.isEmpty()) {
            return String.valueOf(nombreArchivo.charAt(nombreArchivo.length() - 1));
        } else {
            return null; // Si el nombre del archivo está vacío, retornar null o un valor indicando que no se encontró ningún carácter
        }
    }

}
