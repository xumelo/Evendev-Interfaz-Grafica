package com.azahartech.eventdev.vista;

import javax.swing.*;
import java.awt.*;

public class TarjetaEvento extends JPanel {
    private String titulo;
    private String fecha;
    private String precio;
    public TarjetaEvento(String titulo,String fecha,String precio){
        this.titulo=titulo;
        this.fecha=fecha;
        this.precio=precio;

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.cyan));
        JPanel pnlPanel=new JPanel();
        JTextArea txtTitulo=new JTextArea(titulo);
        JTextArea txtFecha=new JTextArea(fecha);
        pnlPanel.add(txtTitulo);
        pnlPanel.add(txtFecha);
        this.add(pnlPanel,BorderLayout.CENTER);

        JPanel pnlBoton=new JPanel();
        JButton btnComprar=new JButton("Comprar"+precio);
        pnlBoton.add(btnComprar);
        this.add(pnlBoton,BorderLayout.SOUTH);

        JPanel pnlTitulo=new JPanel();
        JLabel lblTitulo=new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        //Alineación horizontal al centro
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

    }
}
