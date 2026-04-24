package com.azahartech.eventdev.vista;

import com.azahartech.eventdev.modelo.Evento;
import com.azahartech.eventdev.modelo.Partido;
import com.azahartech.eventdev.modelo.Recinto;
import com.azahartech.eventdev.servicio.ServicioEvento;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;

import static com.azahartech.eventdev.presentacion.AppGUI.servicioPrincipal;

public class VistaDashboard extends JFrame {
    private Container lienzo = this.getContentPane();
    private String nombreUsuario;
    private JButton salirButton;
    private JMenuItem salirMenuItem;
    private JButton detallesButton;
    private JMenuItem cerrarSesionMenuItem;
    private JMenuItem nuevoEventoMenuItem;
    private String[] nombresColumnas;
    private DefaultTableModel eventosTableModel;
    private JTable eventosTable;
    private NuevoEventoDialog dialog;
    private ServicioEvento servicioEvento;
    private JMenuItem importarXmlMenuItem;
    private JMenuItem exportarXmlMenuItem;



    public VistaDashboard(String nombreUsuario) {
        this.setTitle("Panel");
        this.nombreUsuario=nombreUsuario;
        this.setSize(800, 600);
        lienzo.setLayout(new BorderLayout(10, 10));
        this.setLocationRelativeTo(null);
        salirButton=new JButton("Salir");
        detallesButton=new JButton("Ver Detalles");
        servicioEvento= servicioPrincipal;

        nombresColumnas= new String[]{"ID", "Nombre", "Fecha", "Precio"};
        eventosTableModel = new DefaultTableModel(nombresColumnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        initUI();
        refrescarTabla();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initUI(){
        //Panel Barra Lateral
        JPanel pnlBarraLateral =new JPanel();
        pnlBarraLateral.setBackground(Color.red);

        JButton catalogoButton =new JButton("Catalogo");
        JButton entradasButton =new JButton("Mis Entradas");
        JButton perfilButton =new JButton("Perfil");


        pnlBarraLateral.add(catalogoButton);
        pnlBarraLateral.add(entradasButton);
        pnlBarraLateral.add(perfilButton);
        pnlBarraLateral.add(salirButton);
        GridLayout gridLayoutBarraLateral = new GridLayout(10,1);
        gridLayoutBarraLateral.setVgap(10);
        pnlBarraLateral.setLayout(gridLayoutBarraLateral);
        lienzo.add(pnlBarraLateral,BorderLayout.WEST);

        //Panel Barra Estado
        JPanel barraEstadoPanel =new JPanel();
        JLabel usuarioInvitadoLabel = new JLabel("Usuario: "+nombreUsuario);
        barraEstadoPanel.add(usuarioInvitadoLabel);
        barraEstadoPanel.add(detallesButton);
        barraEstadoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        lienzo.add(barraEstadoPanel,BorderLayout.SOUTH);

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


//        for (int i=0;i<5;i++){
//            servicioPrincipal.registrarEvento(new Partido("Prueba"+i, LocalDate.now().plusDays(3+i),new Recinto("Caminas","Calle Gloria",1200),12.00+i, "Barcelona","Madrid",1000.00*i));
//        }
//        for (Evento listaEvento: servicioPrincipal.listarTodosLosEventos()){
//            Object[] datos={listaEvento.getId(),listaEvento.getNombre(),listaEvento.getFecha(),listaEvento.getPrecio()};
//            eventosTableModel.addRow(datos);
//        }

        eventosTable = new JTable(eventosTableModel);


        JScrollPane scroll = new JScrollPane(eventosTable);
        scroll.getVerticalScrollBar().setUnitIncrement(16);


        lienzo.add(scroll,BorderLayout.CENTER);

        initMenu();
        initListeners();
    }
    private void initMenu(){
        JMenuBar principalMenuBar=new JMenuBar();



        JMenu salirMenu=new JMenu("≡");
        salirMenuItem=new JMenuItem("Salir");
        cerrarSesionMenuItem=new JMenuItem("Cerrar Sesion");
        salirMenu.add(salirMenuItem);
        salirMenu.add(cerrarSesionMenuItem);

        JMenu accionesMenu=new JMenu("Acciones");
        nuevoEventoMenuItem=new JMenuItem("Nuevo Evento");
        accionesMenu.add(nuevoEventoMenuItem);

        JMenu archivoMenu=new JMenu("Archivo");
        importarXmlMenuItem=new JMenuItem("Importar XML");
        exportarXmlMenuItem=new JMenuItem("Exportar XML");
        archivoMenu.add(importarXmlMenuItem);
        archivoMenu.add(exportarXmlMenuItem);

        principalMenuBar.add(salirMenu);
        principalMenuBar.add(archivoMenu);
        principalMenuBar.add(accionesMenu);

        this.setJMenuBar(principalMenuBar);

    }
    //Liseners
    private void initListeners() {
        salirButton.addActionListener(e -> intentarSalir());
        salirMenuItem.addActionListener(e->intentarSalir());
        cerrarSesionMenuItem.addActionListener(e -> intentarCerrarSesion());
        detallesButton.addActionListener(e -> intentarVerDetalle());
        nuevoEventoMenuItem.addActionListener(e -> intentarNuevoEvento());
        importarXmlMenuItem.addActionListener(e-> intentarImportar());
        exportarXmlMenuItem.addActionListener(e->intetarExportar());
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
    private void intentarImportar(){
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo XML","xml"));
        fileChooser.showOpenDialog(this);
        File f = fileChooser.getSelectedFile();
        try {
            servicioPrincipal.importarCatalogoDesdeXML(f.getAbsolutePath());
            JOptionPane.showMessageDialog(this,"Archivo importado con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Error al importar");
        }

        refrescarTabla();
    }
    private void intetarExportar(){
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.showSaveDialog(this);
        File f=fileChooser.getSelectedFile();
        try {
            servicioPrincipal.exportarCatalogoAXML(f.getAbsolutePath()+".xml");
            JOptionPane.showMessageDialog(this,"Archivo exportado con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Error al exportar");
        }
    }
    private void intentarCerrarSesion(){
        this.dispose();
        new VistaLogin().setVisible(true);

    }
    private void intentarVerDetalle(){
        int filaSeleccionada = eventosTable.getSelectedRow();
        if (filaSeleccionada == -1){
            JOptionPane.showMessageDialog(this,
                    "Error selecciona una opcion valida.",
                    "Error de seleccion",
                    JOptionPane.ERROR_MESSAGE);
    }else {
            String nombreEvento = eventosTable.getValueAt(filaSeleccionada, 1).toString();
            String fechaEvento = eventosTable.getValueAt(filaSeleccionada, 2).toString();
            String precioEvento = eventosTable.getValueAt(filaSeleccionada, 3).toString();
            JOptionPane.showMessageDialog(this,
                    "Nombre: "+nombreEvento+"" +
                            "\nFecha: "+fechaEvento+"" +
                            "\nPrecio:"+precioEvento,"Detalles Evento",1);

        }

    }
    private void intentarNuevoEvento(){
       dialog = new NuevoEventoDialog(this);
        dialog.setVisible(true);
        this.refrescarTabla();
    }
    private void refrescarTabla(){
        eventosTableModel.setRowCount(0); // Borra las filas actuales
        for (Evento listaEvento: servicioPrincipal.listarTodosLosEventos()){
            Object[] datos={listaEvento.getId(),listaEvento.getNombre(),listaEvento.getFecha(),listaEvento.getPrecio()};
            eventosTableModel.addRow(datos); // Añade las nuevas filas
        }
    }
}
