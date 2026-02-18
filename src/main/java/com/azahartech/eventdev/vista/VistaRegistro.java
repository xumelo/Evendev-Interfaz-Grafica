package com.azahartech.eventdev.vista;

import javax.swing.*;
import java.awt.*;

public class VistaRegistro extends JFrame {
    private Container lienzo = this.getContentPane();
    public VistaRegistro(){
        this.setTitle("Registro");
        this.setSize(400, 300);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        lienzo.setLayout(new BorderLayout(10, 10));
        ((JPanel)this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initUI();
    }
    private void initUI(){
        JPanel pnlFormulario=new JPanel();
        pnlFormulario.setLayout(new GridLayout(5,5,5,5));
        JLabel lblNombre = new JLabel("Nombre Completo:");
        JTextField txtNombre = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JLabel lblPassword = new JLabel("Contraseña:");
        JPasswordField txtPassword = new JPasswordField();
        JLabel lblPasswordConfirmar = new JLabel("Confirmar Contraseña:");
        JPasswordField txtPasswordConfirmar = new JPasswordField();
        JLabel lblEdad = new JLabel("Edad:");
        JTextField txtEdad = new JTextField();

        pnlFormulario.add(lblNombre);
        pnlFormulario.add(txtNombre);
        pnlFormulario.add(lblEmail);
        pnlFormulario.add(txtEmail);
        pnlFormulario.add(lblPassword);
        pnlFormulario.add(txtPassword);
        pnlFormulario.add(lblPasswordConfirmar);
        pnlFormulario.add(txtPasswordConfirmar);
        pnlFormulario.add(lblEdad);
        pnlFormulario.add(txtEdad);
        lienzo.add(pnlFormulario, BorderLayout.CENTER);

        //Botones
        JPanel pnlBotones =new JPanel();
        //Alineacion al centro
        pnlBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        //Crear Botones
        JButton btnGuardar=new JButton("Guardar");
        JButton btnCancelar=new JButton("Cancelar");
        pnlBotones.add(btnGuardar);
        pnlBotones.add(btnCancelar);
        lienzo.add(pnlBotones,BorderLayout.SOUTH);


        //Titulo
        JLabel lblTitulo = new JLabel("Registro a EventDEV");
        //Cambiar la fuente
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        //Alineación horizontal al centro
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        //Añadir la etiqueta a la zona NORTH
        lienzo.add(lblTitulo, BorderLayout.NORTH);
    }
}
