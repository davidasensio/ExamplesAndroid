package com.curso.androidt.basededatos;

import java.util.Date;
import java.util.List;

/**
 * Created by androidt on 20/05/2015.
 */
public interface Dao<K,E> {

    void insertar(E entidad);
    void borrar(E entidad);
    void update(E entidad);
    E consultar(K id);
    List<E> consultar();
    List<E> consultar(Float magnitud, Date fecha);
}
