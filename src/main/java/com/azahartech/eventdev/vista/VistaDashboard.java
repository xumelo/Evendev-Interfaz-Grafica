package com.azahartech.eventdev.vista;

import javax.swing.*;
import java.awt.*;

public class VistaDashboard extends JFrame {
    private Container lienzo = this.getContentPane();
    private String nombreUsuario;
    public VistaDashboard(String nombreUsuario) {
        this.setTitle("Panel");
        this.nombreUsuario=nombreUsuario;
        this.setSize(800, 600);
        lienzo.setLayout(new BorderLayout(10, 10));
        this.setLocationRelativeTo(null);
        initUI();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void initUI(){
        //Panel Barra Lateral
        JPanel pnlBarraLateral =new JPanel();
        pnlBarraLateral.setBackground(Color.red);

        JButton btnCatalogo=new JButton("Catalogo");
        JButton btnEntradas =new JButton("Mis Entradas");
        JButton btnPerfil=new JButton("Perfil");
        JButton btnSalir=new JButton("Salir");

        pnlBarraLateral.add(btnCatalogo);
        pnlBarraLateral.add(btnEntradas);
        pnlBarraLateral.add(btnPerfil);
        pnlBarraLateral.add(btnSalir);
        GridLayout gridLayoutBarraLateral = new GridLayout(10,1);
        gridLayoutBarraLateral.setVgap(10);
        pnlBarraLateral.setLayout(gridLayoutBarraLateral);
        lienzo.add(pnlBarraLateral,BorderLayout.WEST);

        //Panel Barra Estado
        JPanel pnlBarraEstado=new JPanel();
        JLabel lblUsuarioInvitado = new JLabel("Usuario: "+nombreUsuario);
        pnlBarraEstado.add(lblUsuarioInvitado);
        pnlBarraEstado.setLayout(new FlowLayout(FlowLayout.LEFT));
        lienzo.add(pnlBarraEstado,BorderLayout.SOUTH);

        //Panel Central
        JPanel pnlCentral=new JPanel();
        pnlCentral.setBackground(Color.white);
        lienzo.add(pnlCentral,BorderLayout.CENTER);

        //Scroll
        JPanel listaPanel=new JPanel();
        GridLayout gridLayout = new GridLayout(0,1);
        gridLayout.setVgap(10);
        listaPanel.setLayout(gridLayout);
        listaPanel.setBorder(BorderFactory.createCompoundBorder(listaPanel.getBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        for (int i=0;i<10;i++){
            TarjetaEvento tarjeta1=new TarjetaEvento("Prueba1","2026-05-10","2.00");
            tarjeta1.setBorder(BorderFactory.createCompoundBorder(tarjeta1.getBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            listaPanel.add(tarjeta1);
        }
        JScrollPane scroll = new JScrollPane(listaPanel);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        lienzo.add(scroll,BorderLayout.CENTER);

        btnSalir.addActionListener(e -> {
            int confirmar=JOptionPane.showConfirmDialog(this,
                    "¿Seguro que quieres salir?",
                    "Salir",
                    JOptionPane.YES_NO_OPTION);
            if (confirmar==JOptionPane.YES_NO_OPTION){
                this.dispose();
                new VistaLogin().setVisible(true);
            }
        });
    }
}
