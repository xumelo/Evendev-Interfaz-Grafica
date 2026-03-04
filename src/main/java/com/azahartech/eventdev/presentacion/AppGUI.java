package com.azahartech.eventdev.presentacion;

import com.azahartech.eventdev.vista.VistaDashboard;
import com.azahartech.eventdev.vista.VistaLogin;
import com.azahartech.eventdev.vista.VistaRegistro;

import javax.swing.*;

public class AppGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VistaLogin().setVisible(true);
        });
//        SwingUtilities.invokeLater(() -> {
//            new VistaRegistro().setVisible(true);
//        });
//        SwingUtilities.invokeLater(() -> {
//            new VistaDashboard("admin@eventdev.com").setVisible(true);
//        });
    }
}
