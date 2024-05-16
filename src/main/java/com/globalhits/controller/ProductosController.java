package com.globalhits.controller;

import com.globalhits.dto.ProductosDTO;
import com.globalhits.services.IProductosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductosController {
    private final IProductosService service;

    @PostMapping
    public ResponseEntity<List<ProductosDTO>> insertarProductoYListarHoy(@Valid @RequestBody ProductosDTO request) {
        List<Object[]> productosRegistradosHoy = service.insertarProductoYListar(
                request.getIdProducto(),
                request.getNombreProducto(),
                request.getFec_registroProducto());
        if (productosRegistradosHoy.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<ProductosDTO> productosDTO = productosRegistradosHoy.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productosDTO);
    }

    private ProductosDTO convertToDTO(Object[] obj) {
        Long id = (Long) obj[0];
        String nombre = (String) obj[1];
        Date fec_registro = (Date) obj[2];
        return new ProductosDTO(id, nombre, fec_registro);
    }
}
