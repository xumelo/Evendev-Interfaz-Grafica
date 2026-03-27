package com.azahartech.eventdev.pagos;

import java.io.Serializable;

/**
 * Implementación del procesador de pagos mediante Tarjeta de credito/debito.
 * Realiza una validación básica del formato de la tarjeta y simula una tasa
 * de error aleatoria para representar fallos en la pasarela de pagos.
 */
public class PagoTarjeta implements ProcesadorPago, Serializable {
    /** Número de la tarjeta (debe contener 16 dígitos). */
    String numeroTarjeta;
    /** Fecha de caducidad de la tarjeta. */
    String fechaCaducidad;

    /**
     * Constructor para procesar pagos con tarjeta.
     * @param numeroTarjeta El número completo de la tarjeta (16 dígitos).
     * @param fechaCaducidad La fecha de expiración de la tarjeta.
     */
    public PagoTarjeta(String numeroTarjeta, String fechaCaducidad) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaCaducidad = fechaCaducidad;
    }

    /**
     * Ejecuta el proceso de pago con tarjeta.
     * El metodo incluye una probabilidad del 10% de fallo.
     * Verifica que el número de tarjeta cumpla con el estándar de 16 dígitos.
     * * @param cantidad La cantidad total de la transacción.
     * @return {@code true} si la tarjeta es válida y no hubo fallo técnico;
     * {@code false} en caso de error aleatorio o formato de tarjeta incorrecto.
     */
    @Override
    public boolean procesarPago(double cantidad) {
        double numFallo = Math.random();

        if (numFallo < 0.1) {
            System.err.println("Error, pago fallido");
            return false;
        } else {
            System.out.println("Pago aceptado");
        }

        System.out.println("Procesando pago de " + cantidad + " con Tarjeta " + numeroTarjeta);

       if (numeroTarjeta.matches("\\d{16}")) {
            return true;
        } else {
            System.err.println("Error: Formato de tarjeta inválido.");
            return false;
        }
    }
}