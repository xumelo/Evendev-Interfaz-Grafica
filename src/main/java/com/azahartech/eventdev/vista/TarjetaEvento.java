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
        this.setBorder(BorderFactory.createLineBorder(Color.blue));

        JLabel lblPanel=new JLabel();
        JTextArea txtFecha=new JTextArea(fecha);
        lblPanel.add(txtFecha);
        this.add(txtFecha,BorderLayout.CENTER);


        JButton comprarButton =new JButton("Comprar "+precio);
        this.add(comprarButton,BorderLayout.SOUTH);


        JLabel lblTitulo=new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        //Alineación horizontal al centro
        lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(lblTitulo,BorderLayout.NORTH);

        comprarButton.addActionListener(e -> {
            // Simular compra
            int opcion = JOptionPane.showConfirmDialog(this,
                    "¿Quieres comprar una entrada para " + this.titulo + "?",
                    "Confirmar Compra",
                    JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this,
                        "¡Entrada comprada! (simulación)",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                // Opcional: Deshabilitar el botón para no comprar dos veces
                comprarButton.setEnabled(false);
                comprarButton.setText("Comprado");
            }
        });
    }
}
