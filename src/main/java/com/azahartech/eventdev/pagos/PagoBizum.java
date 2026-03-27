package com.azahartech.eventdev.pagos;

import java.io.Serializable;

/**
 * Implementación del procesador de pagos mediante Bizum.
 * Valida el formato del número de teléfono y simula posibles errores de conexión.
 */
public class PagoBizum implements ProcesadorPago, Serializable {
    /** Número de teléfono asociado al usuario de Bizum. */
    String telefono;
    /** PIN de seguridad del usuario. */
    int pin;

    /**
     * Constructor para procesar pagos vía Bizum.
     * @param telefono Número de teléfono (debe tener 9 dígitos).
     * @param pin Código PIN de seguridad.
     */
    public PagoBizum(String telefono, int pin) {
        this.telefono = telefono;
        this.pin = pin;
    }

    /**
     * Ejecuta el pago verificando el formato del teléfono y una estabilidad de red simulada.
     * @param cantidad La cantidad total a cobrar.
     * @return {@code true} si el teléfono es válido y no hay error de red;
     * {@code false} si el formato es incorrecto o falla la conexión con un 10% de probabilidad.
     */
    @Override
    public boolean procesarPago(double cantidad) {
        System.out.println("Procesando pago de " + cantidad + " con teléfono " + telefono);

        if (Math.random() < 0.1) {
            System.out.println("Error técnico: La conexión con Bizum ha fallado.");
            return false;
        }

        if (telefono.matches("\\d{9}")) {
            return true;
        } else {
            System.out.println("Error: Número de teléfono inválido.");
            return false;
        }
    }
}