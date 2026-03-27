package com.azahartech.eventdev.servicio;

import com.azahartech.eventdev.modelo.*;
import com.azahartech.eventdev.datos.RepositorioGenerico;
import com.azahartech.eventdev.util.GestorPersistencia;
import com.azahartech.eventdev.util.UtilidadLog;

import java.time.LocalDate;
import java.io.*;
import java.util.*;

/**
 * Clase ServicioEvento
 */
public class ServicioEvento {
    private RepositorioGenerico<Evento> repo = new RepositorioGenerico<>();
    private HashMap<String, Evento> mapaEventos = new HashMap<>();
    private static final String CARPETA_RUTA = "datos";
    private static final String FICHERO_DATOS = "datos/eventos.dat";

    /**
     * Añadir un evento
     * @param nuevoEvento
     */
    public void registrarEvento(Evento nuevoEvento) {
        repo.guardar(nuevoEvento);
        mapaEventos.put(nuevoEvento.getId(), nuevoEvento);
    }

    /**
     * Buscar un evento por id
     * @param idABuscar
     * @return
     */
    public Evento buscarEventoPorId(String idABuscar) {
        return mapaEventos.get(idABuscar);
    }

    /**
     * Buscar un evento por precio mas alto
     * @return
     */
    public Evento buscarEventoMasCaro() {
        List<Evento> eventos = repo.listar();
        if (eventos.isEmpty()) return null;

        Evento masCaro = eventos.get(0);

        for (int i = 1; i < eventos.size(); i++) {
            if (eventos.get(i).getPrecio() > masCaro.getPrecio()) {
                masCaro = eventos.get(i);
            }
        }
        return masCaro;
    }

    /**
     * Mostrar catalogo
     */
    public void mostrarTodoElCatalogo() {
        mapaEventos.values().forEach(Evento::mostrarInformacion);
    }

    /**
     * Eliminar eventos pasados
     */
    public void eliminarEventosPasados() {
        Iterator<Evento> iterador = repo.listar().iterator();
        while (iterador.hasNext()) {
            Evento e = iterador.next();
            if (e.getFecha().isBefore(LocalDate.now())) {
                mapaEventos.remove(e.getId());
                System.out.println("Evento caducado eliminado: "+ e.getNombre());
                iterador.remove();
            }
        }
    }
    /**
     * Contar eventos gratuitos
     * @return
     */
    public long contarEventosGratuitos() {
        return mapaEventos.values().stream()
                .filter(e -> e.getPrecio() == 0)
                .count();
    }

    /**
     * Contar eventos por aforo
     * @param aforoMinimo
     * @return
     */
    public long contarEventosPorAforo(int aforoMinimo) {
        return mapaEventos.values().stream()
                .filter(e -> e.getRecinto().getAforoMaximo() >= aforoMinimo)
                .count();
    }

    /**
     * Cierre de eventos
     * @param sc
     */
    public void procesarCierreEventos(Scanner sc) {
        for (Evento e : mapaEventos.values()) {
            if (e.getEstado() == EstadoEvento.ACTIVO) {
                System.out.println("Cerrando: " + e.getNombre());

                if (e instanceof Partido p) {
                    System.out.print("Introduce resultado (ej. 2-1): ");
                    p.setResultadoMarcador(sc.nextLine());
                } else if (e instanceof Concierto c) {
                    System.out.print("Introduce lista de canciones: ");
                }
                e.finalizarEvento();
            }
        }
    }

    /**
     * Lista todos los eventos guardado en la Lista
     * @return
     */
    public List<Evento> listarTodosLosEventos(){
        return repo.listar();
    }
    public void importarEventosDesdeCSV(String rutaArchivo) {
        UtilidadLog LogUtil = null;
        File archivo = new File("datos/eventos_importar.csv");
        if (!archivo.exists()) {
            System.out.println("No hay datos para importar.");
            return;
        }
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            System.out.println(lector.readLine());

            while ((linea = lector.readLine()) != null) {
                String[] datos=new String[4];
                datos=linea.split(";");
                System.out.println("Leído: " + linea);
                LocalDate fecha;
                int aforo;
                double precio;
                String fechaStr;
                String aforoStr;
                String precioStr;


                try {
                    fechaStr =datos[2];
                    fecha=LocalDate.parse(fechaStr);
                } catch (Exception e) {
                    System.out.println("Error en la fecha");
                    LogUtil.registrar(NivelError.WARM,"ERROR: Fallo al importar línea del CSV: "+ e.getMessage());
                    fecha=null;
                }
                try {
                    aforoStr=datos[3];
                    aforo=Integer.parseInt(aforoStr);
                } catch (Exception e) {
                    System.out.println("Error en el aforo");
                    LogUtil.registrar(NivelError.WARM,"ERROR: Fallo al importar línea del CSV: "+ e.getMessage());
                    aforo=0;
                }
                try {
                    precioStr=datos[4];
                    precio=Double.parseDouble(precioStr);
                } catch (Exception e) {
                    System.out.println("Error en el precio");
                    LogUtil.registrar(NivelError.WARM,"ERROR: Fallo al importar línea del CSV: "+ e.getMessage());
                    precio=0;
                }
                Evento nuevoEvento=new Partido(datos[0],fecha,new Recinto(null,datos[1], aforo),precio,null,null,0 );
                this.repo.listar().add(nuevoEvento);
                System.out.println();
                LogUtil.registrar(NivelError.INFO,"Importado: Evento importado: "+datos[0]);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
            LogUtil.registrar(NivelError.ERROR,"ERROR: Fallo al importar el evento: "+ e.getMessage());

        }

    }
    public void guardar(){
        if (!new File(CARPETA_RUTA).exists() || !new File(CARPETA_RUTA).isDirectory()){
            new File(CARPETA_RUTA).mkdir();
        }
        GestorPersistencia gestor=new GestorPersistencia();
        gestor.guardarDatos(repo.listar(),FICHERO_DATOS);
    }

    /**
     * Genera informe financiero
     */
    public void generarInformeFinanciero() {
        Collection<Evento> eventos = mapaEventos.values();
        for (Evento e : mapaEventos.values()) {
            System.out.println("ID: " + e.getId());
            System.out.println("Evento: " + e.getNombre());
            System.out.printf(" - Coste Operativo: %.2f€%n", e.calcularCosteOperativo());
            System.out.printf(" - Precio Sugerido: %.2f€%n", e.calcularPrecioVentaRecomendado());
            System.out.println("-----------------------------------");
        }

    }
}