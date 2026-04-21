package com.azahartech.eventdev.datos;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;


public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    // De Java a XML (Escribir)
    @Override
    public String marshal(LocalDate fecha) {
        return fecha.toString(); // Convierte a formato "2025-12-31"
    }
    // De XML a Java (Leer)
    @Override
    public LocalDate unmarshal(String texto) {
        return LocalDate.parse(texto); // Convierte texto a objeto LocalDate
    }
}