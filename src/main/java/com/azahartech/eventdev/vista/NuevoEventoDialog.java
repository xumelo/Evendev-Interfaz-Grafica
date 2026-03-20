package com.azahartech.eventdev.vista;

import com.azahartech.eventdev.modelo.Partido;
import com.azahartech.eventdev.modelo.Recinto;
import com.azahartech.eventdev.presentacion.AppGUI;
import com.azahartech.eventdev.servicio.ServicioEvento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

public class NuevoEventoDialog extends JDialog {
    private JTextField nombreField;
    private JTextField fechaField;
    private JTextField precioField;

    private JButton guardarButton;
    private JButton cancelarButton;
    private ServicioEvento servicioEvento;

    public NuevoEventoDialog(JFrame padre) {
        super(padre, "Nuevo evento", true);
        servicioEvento= AppGUI.servicioPrincipal;

        initUI();
    }
    private void initUI(){
        this.setSize(300,200);
        this.setLocationRelativeTo(null);
        JPanel principalPanel =new JPanel(new BorderLayout());
        JPanel formularioPanel =new JPanel();
        formularioPanel.setLayout(new GridLayout(3,2,5,5));

        JLabel nombreLabel = new JLabel("Nombre:");
        this.nombreField = new JTextField();

        JLabel fechaLabel = new JLabel("Fecha:");
        this.fechaField = new JTextField();
        fechaField.setToolTipText("Formato: AAAA-MM-DD");

        JLabel precioLabel = new JLabel("Precio:");
        this.precioField = new JTextField();

        formularioPanel.add(nombreLabel);
        formularioPanel.add(nombreField);

        formularioPanel.add(fechaLabel);
        formularioPanel.add(fechaField);

        formularioPanel.add(precioLabel);
        formularioPanel.add(precioField);

        principalPanel.add(formularioPanel, BorderLayout.CENTER);

        JPanel pnlBotones = new JPanel();
        //Alineacion al centro
        pnlBotones.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Crear Botones
        guardarButton = new JButton("Guardar");
        cancelarButton = new JButton("Cancelar");
        pnlBotones.add(guardarButton);
        pnlBotones.add(cancelarButton);
        principalPanel.add(pnlBotones, BorderLayout.SOUTH);
        this.setContentPane(principalPanel);

        precioField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String precio=precioField.getText();
                try{
                    Double.parseDouble(precio.replace(',','.'));
                    precioField.setBackground(Color.WHITE);
                }catch(NumberFormatException ex){
                    precioField.setBackground(Color.PINK);
                }
            }
        });

        initListeners();
    }
    private void initListeners() {
        cancelarButton.addActionListener(e -> intentarCancelar());
        guardarButton.addActionListener(e->intentarGuardar());
    }
    private void intentarCancelar(){
        this.dispose();
    }
    private void intentarGuardar() {
        try{
            String nombre = nombreField.getText().trim();
            LocalDate fecha =LocalDate.parse(fechaField.getText().trim());
            double precio = Double.parseDouble(precioField.getText().trim());
            servicioEvento.registrarEvento(new Partido(nombre,fecha,new Recinto("Caminas","Calle Gloria",1200),precio, "Barcelona","Madrid",1000.00));
            this.dispose();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
