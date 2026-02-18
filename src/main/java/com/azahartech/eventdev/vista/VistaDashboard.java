package com.azahartech.eventdev.vista;

import javax.swing.*;
import java.awt.*;

public class VistaDashboard extends JFrame {
    private Container lienzo = this.getContentPane();
    public VistaDashboard() {
        this.setTitle("Panel");
        this.setSize(800, 600);
        lienzo.setLayout(new BorderLayout(10, 10));
        ((JPanel)this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLocationRelativeTo(null);
        initUI();
    }
    private void initUI(){
        //Panel principal
        JPanel pnlPrincipal =new JPanel();
        pnlPrincipal.setLayout(new BorderLayout());

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
        pnlBarraLateral.setLayout(new GridLayout(10,1));
        pnlPrincipal.add(pnlBarraLateral,BorderLayout.WEST);

        //Panel Barra Estado
        JPanel pnlBarraEstado=new JPanel();
        JLabel lblUsuarioInvitado = new JLabel("Usuario Invitado");
        pnlBarraEstado.add(lblUsuarioInvitado);
        pnlBarraEstado.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlPrincipal.add(pnlBarraEstado,BorderLayout.SOUTH);

        //Panel Central
        JPanel pnlCentral=new JPanel();
        pnlCentral.setBackground(Color.white);
        pnlPrincipal.add(pnlCentral,BorderLayout.CENTER);


        lienzo.add(pnlPrincipal);

    }
}
