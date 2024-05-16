package com.globalhits.services.impl;
import com.globalhits.model.Productos;
import com.globalhits.repository.IGenericRepository;
import com.globalhits.repository.IProductosRepository;
import com.globalhits.repository.ProductosRepositoryCustom;
import com.globalhits.services.IProductosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductosServiceImpl extends CRUDImpl<Productos,Integer> implements IProductosService {

    private final IProductosRepository repository;
    private final ProductosRepositoryCustom productosRepositoryCustom;


    @Override
    protected IGenericRepository<Productos,Integer> getRepo(){
        return repository;
    }
    @Override
    @Transactional
    public List<Object[]> insertarProductoYListar(Long idProducto, String nombre, Date fec_registro) {
        List<Object[]> resultados =  productosRepositoryCustom.insertarProductoYListar(idProducto, nombre, fec_registro);
        resultados.forEach(obj -> {
            Timestamp timestamp = (Timestamp) obj[2];
            Date date = new Date(timestamp.getTime());
            obj[2] = date;
        });

        return resultados;
    }
}
