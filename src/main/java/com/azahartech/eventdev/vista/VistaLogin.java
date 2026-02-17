package com.azahartech.eventdev.vista;

import javax.swing.*;
import java.awt.*;

public class VistaLogin extends JFrame {
    private Container lienzo = this.getContentPane();
    public VistaLogin() {
        this.setTitle("Acceso a EventDEV");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        lienzo.setLayout(new BorderLayout(10, 10));
        initUI();
    }
    private void initUI(){

        JPanel pnlFormulario=new JPanel();
        pnlFormulario.setLayout(new GridLayout(2,2,10,10));
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JLabel lblPassword = new JLabel("Contraseña:");
        JPasswordField txtPassword = new JPasswordField();
        pnlFormulario.add(lblEmail);
        pnlFormulario.add(txtEmail);
        pnlFormulario.add(lblPassword);
        pnlFormulario.add(txtPassword);
        lienzo.add(pnlFormulario, BorderLayout.CENTER);
    }
}
