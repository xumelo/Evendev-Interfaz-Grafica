package com.azahartech.eventdev.pagos;

import java.io.Serializable;

/**
 * Implementación del procesador de pagos a través de PayPal.
 * Gestiona la transacción utilizando la cuenta de correo del usuario.
 */
public class PagoPaypal implements ProcesadorPago, Serializable {
    String emailUsuario;

    /**
     * Constructor de el pago de PayPal
     * @param emailUsuario El correo electrónico del usuario de PayPal.
     */
    public PagoPaypal(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    /**
     * Procesa el cobro simulando una redirección a la plataforma de PayPal.
     * @param cantidad La cantidad total de entradas a cobrar.
     * @return {@code true} siempre que se simule la redirección correctamente.
     */
    @Override
    public boolean procesarPago(double cantidad) {
        System.out.println("Redirigiendo a PayPal para el usuario " + emailUsuario);
        System.out.println("Cobro de " + cantidad + " realizado");
        return true;
    }
}