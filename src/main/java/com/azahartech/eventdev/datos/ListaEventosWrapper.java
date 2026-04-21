package com.azahartech.eventdev.datos;

import com.azahartech.eventdev.modelo.Evento;
import jakarta.xml.bind.annotation.*;

import java.util.*;


@XmlRootElement(name = "catalogo_eventos")
@XmlAccessorType(XmlAccessType.FIELD)

public class ListaEventosWrapper {
    @XmlElement(name = "evento")
    private List<Evento> lista;

    public ListaEventosWrapper(){}

    public List<Evento> getLista() {
        return lista;
    }

    public void setLista(List<Evento> lista) {
        this.lista = lista;
    }
}
