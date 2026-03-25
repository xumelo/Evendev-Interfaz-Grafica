package com.azahartech.eventdev.presentacion;

import com.azahartech.eventdev.modelo.Evento;
import com.azahartech.eventdev.modelo.NivelError;
import com.azahartech.eventdev.servicio.ServicioEvento;
import com.azahartech.eventdev.util.UtilidadLog;
import com.azahartech.eventdev.vista.VistaLogin;

import javax.swing.*;

public class AppGUI {
    public final static ServicioEvento servicioPrincipal = new ServicioEvento();

    public static void main(String[] args) {
        servicioPrincipal.importarEventosDesdeCSV("datos/eventos_importar.csv");
        for (Evento evento:servicioPrincipal.listarTodosLosEventos()) {
            System.out.println(evento.getNombre()+" "+evento.getFecha()+" "+evento.getPrecio());
        }
        UtilidadLog LogUtil = null;
        LogUtil.registrar(NivelError.INFO,"Inicio de la aplicación");
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new VistaLogin().setVisible(true);
        });
    }
}
