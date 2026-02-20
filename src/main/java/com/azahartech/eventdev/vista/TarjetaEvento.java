package com.azahartech.eventdev.vista;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
        this.setBorder(BorderFactory.createLineBorder(Color.yellow));
//        this.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 1), new EmptyBorder(10,10,10,10)));
        JLabel lblPanel=new JLabel();
        JTextArea txtFecha=new JTextArea(fecha);
        lblPanel.add(txtFecha);
        this.add(txtFecha,BorderLayout.CENTER);


        JButton btnComprar=new JButton("Comprar "+precio);
        this.add(btnComprar,BorderLayout.SOUTH);

        JLabel lblTitulo=new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        //Alineación horizontal al centro
        lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(lblTitulo,BorderLayout.NORTH);




    }
}
