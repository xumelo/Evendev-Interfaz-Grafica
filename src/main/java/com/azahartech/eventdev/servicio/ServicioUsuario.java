package com.azahartech.eventdev.servicio;

import com.azahartech.eventdev.datos.RepositorioGenerico;
import com.azahartech.eventdev.modelo.Usuario;
import com.azahartech.eventdev.util.GestorPersistencia;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ServicioUsuario {
    private RepositorioGenerico<Usuario> repo = new RepositorioGenerico<>();
    private Map<String, Usuario> mapa;
    private static final String CARPETA_RUTA = "datos";
    private static final String FICHERO_DATOS = "datos/usuarios.dat";

    public ServicioUsuario() {
        GestorPersistencia gestor= new GestorPersistencia();
        this.repo.cargar(gestor.cargarDatos(FICHERO_DATOS));
    }
    /**
     * Función para guardar el usuario dentro de el repo.
     * @param nuevoUsuario usuario nuevo para guardar en el RepositorioGenerico.
     */
    public void registrarUsuario(Usuario nuevoUsuario) {
        repo.guardar(nuevoUsuario);
    }

    /**
     * Funcion que devuelve el usuario que se desea buscar por el parametro nombre.
     * @param nombre el nombre del usuario a buscar.
     * @return El usuario que coincide con el nombre del usuario a buscar.
     */
    public Usuario buscarPorNombre (String nombre) {
        return repo.listar().stream()
                .filter(usuario -> usuario.getNombre().equals(nombre))
                .findFirst().orElse(null);
    }

    /**
     * Funcion que devuelve el usuario que se desea buscar por el parametro email.
     * @param email el email del usuario a buscar.
     * @return EL usuario que coincide con el email del usuario a buscar.
     */
    public Usuario buscarPorEmail(String email) {
        return  repo.listar().stream()
                .filter(usuario -> usuario.getEmail().equals(email))
                .findFirst().orElse(null);
    }

    /**
     * Función que devuelve la lista de usuarios guardados
     * @return lista de usuarios guardados.
     */
    public List<Usuario> listarTodosLosUsuario() {
        return repo.listar();
    }

    public void guardar(){
        if (!new File(CARPETA_RUTA).exists() || !new File(CARPETA_RUTA).isDirectory()){
            new File(CARPETA_RUTA).mkdir();
        }
        GestorPersistencia gestor=new GestorPersistencia();
        gestor.guardarDatos(repo.listar(),FICHERO_DATOS);
    }
}
