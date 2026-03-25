package com.azahartech.eventdev.util;

import com.azahartech.eventdev.modelo.NivelError;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UtilidadLog {
    private static final String CARPETA_RUTA = "log";
    private static final String RUTA_LOG = "log/auditoria.log";

    public static void registrar(NivelError nivel, String mensaje) {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDate fecha=LocalDate.now();
        if (!new File(CARPETA_RUTA).exists() || !new File(CARPETA_RUTA).isDirectory()){
            new File(CARPETA_RUTA).mkdir();
        }
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(RUTA_LOG, true))) {
            String linea="["+fecha+" "+ahora.getHour()+":"+ahora.getMinute()+":"+ahora.getSecond()+"] ["+nivel+"] "+mensaje;
            escritor.write(linea);
            escritor.newLine();
            System.out.println("Log guardado.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }
}
