package com.azahartech.eventdev.presentacion;

import com.azahartech.eventdev.vista.VistaLogin;

import javax.swing.*;

public class AppGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {new VistaLogin().setVisible(true);});

    }
}
