package com.globalhits.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class ProductosRepositoryCustom {

    private final EntityManager entityManager;

    public ProductosRepositoryCustom(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Object[]> insertarProductoYListar(Long idProducto, String nombre, Date fec_registro) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_insertAndListProducts");
        query.registerStoredProcedureParameter("idProducto", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("fec_registro", Date.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("cursorProductos", void.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter("codigoRespuesta", Integer.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("mensajeRespuesta", String.class, ParameterMode.OUT);

        query.setParameter("idProducto", idProducto);
        query.setParameter("nombre", nombre);
        query.setParameter("fec_registro",  fec_registro);

        query.execute();

        List<Object[]> resultados = query.getResultList();

        Integer codigoRespuesta = (Integer) query.getOutputParameterValue("codigoRespuesta");
        String mensajeRespuesta = (String) query.getOutputParameterValue("mensajeRespuesta");

        System.out.println("Código de respuesta: " + codigoRespuesta);
        System.out.println("Mensaje de respuesta: " + mensajeRespuesta);

        return resultados;
    }
}