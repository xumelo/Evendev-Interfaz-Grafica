package com.azahartech.eventdev.vista;

import javax.swing.*;
import java.awt.*;

public class VistaRegistro extends JFrame {
    private Container lienzo = this.getContentPane();
    private JButton guardarButton;
    private JButton cancelarButton;

    JTextField nombreField;
    JTextField emailField;
    JPasswordField passwordField;
    JPasswordField passwordConfirmarField;
    JTextField edadField;


    public VistaRegistro() {
        this.setTitle("Registro");
        this.setSize(400, 300);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        lienzo.setLayout(new BorderLayout(10, 10));
        ((JPanel) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initUI();
    }

    private void initUI() {
        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridLayout(5, 5, 5, 5));
        JLabel nombreLabel = new JLabel("Nombre Completo:");
        nombreField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordField = new JPasswordField();

        JLabel passwordConfirmarLabel = new JLabel("Confirmar Contraseña:");
        passwordConfirmarField = new JPasswordField();

        JLabel edadLabel = new JLabel("Edad:");
        edadField = new JTextField();

        formularioPanel.add(nombreLabel);
        formularioPanel.add(nombreField);
        formularioPanel.add(emailLabel);
        formularioPanel.add(emailField);
        formularioPanel.add(passwordLabel);
        formularioPanel.add(passwordField);
        formularioPanel.add(passwordConfirmarLabel);
        formularioPanel.add(passwordConfirmarField);
        formularioPanel.add(edadLabel);
        formularioPanel.add(edadField);
        lienzo.add(formularioPanel, BorderLayout.CENTER);

        //Botones
        JPanel pnlBotones = new JPanel();
        //Alineacion al centro
        pnlBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        //Crear Botones
        guardarButton = new JButton("Guardar");
        cancelarButton = new JButton("Cancelar");
        pnlBotones.add(guardarButton);
        pnlBotones.add(cancelarButton);
        lienzo.add(pnlBotones, BorderLayout.SOUTH);


        //Titulo
        JLabel tituloLabel = new JLabel("Registro a EventDEV");
        //Cambiar la fuente
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        //Alineación horizontal al centro
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //Añadir la etiqueta a la zona NORTH
        lienzo.add(tituloLabel, BorderLayout.NORTH);
        initListeners();

    }

    private void initListeners() {
        guardarButton.addActionListener(e -> intentarGuardar());
        cancelarButton.addActionListener(e ->intentarCancelar());
    }
    private void intentarCancelar(){
            this.dispose();
            new VistaLogin().setVisible(true);
    }

    private void intentarGuardar() {
        String nombre = nombreField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String passwordConfirmar = passwordConfirmarField.getText().trim();
        String edad = edadField.getText().trim();
        boolean validacion = true;
        if (nombre.isEmpty()) {
            System.out.println("El nombre esta vacio");
            validacion = false;
        }
        if (email.isEmpty()) {
            System.out.println("El email esta vacio");
            validacion = false;
        }
        if (password.isEmpty()) {
            System.out.println("La contraseña esta vacia");
            validacion = false;
        }
        if (!password.equals(passwordConfirmar)) {
            System.out.println("Las contraseñas no coinciden");
            validacion = false;
        }
        if (edad.isEmpty()) {
            System.out.println("Edad esta vacio");
            validacion = false;
        }
        if (validacion) {
            JOptionPane.showMessageDialog(this,
                    "¡Guardado Correctamente!",
                    "Guardado",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar :(",
                    "Guardado denegado",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
