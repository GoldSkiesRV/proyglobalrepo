package com.globalhits.services;
import com.globalhits.model.Productos;

import java.sql.Date;
import java.util.List;

public interface IProductosService extends ICRUD<Productos,Integer> {
    List<Object[]> insertarProductoYListar(Long idProducto, String nombre, Date fec_registro);
}
