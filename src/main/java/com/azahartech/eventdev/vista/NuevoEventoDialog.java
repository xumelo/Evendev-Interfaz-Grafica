package com.azahartech.eventdev.vista;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class NuevoEventoDialog extends JDialog {
    private JTextField nombreField;
    private JTextField fechaField;
    private JTextField precioField;

    private JButton guardarButton;
    private JButton cancelarButton;

    public NuevoEventoDialog(JFrame padre) {
        super(padre, "Nuevo evento", true);
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
