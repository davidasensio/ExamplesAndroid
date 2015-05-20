package com.curso.androidt.basededatos;

import java.util.Date;
import java.util.List;

/**
 * Created by androidt on 20/05/2015.
 */
public interface TerremotoDao extends Dao<String, Terremoto> {

    List<Terremoto> consultar(Float magnitud, Date fecha);
}
