package com.azahartech.eventdev.vista;

import javax.swing.*;
import java.awt.*;

public class VistaLogin extends JFrame {
    private JTextField emailField;
    private JPasswordField contrasenyaField;
    private JButton loginButton;
    private JButton registroButton;
    private JButton salirButton;
    private Container lienzo = this.getContentPane();
    public VistaLogin() {
        this.setTitle("Acceso a EventDEV");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        lienzo.setLayout(new BorderLayout(10, 10));
        ((JPanel)this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initUI();
    }
    private void initUI(){

        JPanel formularioPanel =new JPanel();
        formularioPanel.setLayout(new GridLayout(2,2,5,5));
        JLabel emailLabel = new JLabel("Email:");
        this.emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Contraseña:");
        this.contrasenyaField = new JPasswordField();
        formularioPanel.add(emailLabel);
        formularioPanel.add(emailField);
        formularioPanel.add(passwordLabel);
        formularioPanel.add(contrasenyaField);
        lienzo.add(formularioPanel, BorderLayout.CENTER);

        //Titulo
        JLabel tituloLabel = new JLabel("Bienvenido a EventDEV");
        //Cambiar la fuente
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        //Alineación horizontal al centro
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //Añadir la etiqueta a la zona NORTH
        lienzo.add(tituloLabel, BorderLayout.NORTH);

        //Botones
        JPanel botonesPanel =new JPanel();
        //Alineacion al centro
        botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //Crear Botones
        this.loginButton=new JButton("Entrar");
        this.registroButton=new JButton("Registrarse");
        this.salirButton=new JButton("Salir");
        botonesPanel.add(loginButton);
        botonesPanel.add(registroButton);
        botonesPanel.add(salirButton);
        lienzo.add(botonesPanel,BorderLayout.SOUTH);
        initListeners();
    }
    private void initListeners(){
        salirButton.addActionListener(e -> intentarSalir());
        contrasenyaField.addActionListener(e -> intentarLogin());
        loginButton.addActionListener(e -> intentarLogin());
        registroButton.addActionListener(e -> intentarRegistro());
    }
    private void intentarLogin() {

        String email = emailField.getText();
        String contrasenya = new String(contrasenyaField.getPassword());
        if (email.equals("admin") && contrasenya.equals("1234")) {
            JOptionPane.showMessageDialog(this,
                    "¡Bienvenido al sistema, Admin!",
                    "Acceso concedido",
                    JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            VistaDashboard dashboard = new VistaDashboard(email);
            dashboard.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Usuario o contraseña incorrectos.",
                    "Acceso denegado",
                    JOptionPane.ERROR_MESSAGE);
            contrasenyaField.setText("");
            contrasenyaField.requestFocus();
        }
    }
    private void intentarSalir(){
        // Preguntar antes de salir
        int confirmar = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que quieres cerrar la aplicación?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            System.exit(0); // Cierra la JVM
        }
    }
    private void intentarRegistro(){
        this.dispose();
        VistaRegistro vistaRegistro = new VistaRegistro();
        vistaRegistro.setVisible(true);

    }
}
