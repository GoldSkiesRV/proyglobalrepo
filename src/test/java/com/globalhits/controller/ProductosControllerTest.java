package com.globalhits.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globalhits.dto.ProductosDTO;
import com.globalhits.services.IProductosService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductosController.class)
public class ProductosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductosService service;

    @Test
    void testInsertarListar() throws Exception {
        List<Object[]> productosRegistrados = new ArrayList<>();
        //Lista
        productosRegistrados.add(new Object[]{1L, "Prueba", new Date(System.currentTimeMillis())});
        when(service.insertarProductoYListar(
                any(Long.class),
                any(String.class),
                any(Date.class)))
                .thenReturn(productosRegistrados);
        //Inserccion
        ProductosDTO request = new ProductosDTO(2L, "PruebaIS", new Date(System.currentTimeMillis()));
        mockMvc.perform(MockMvcRequestBuilders.post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk());
    }
    @Test
    void testInsertarSinProducto() throws Exception {
        when(service.insertarProductoYListar(
                any(Long.class),
                any(String.class),
                any(Date.class)))
                .thenReturn(Collections.emptyList());

        ProductosDTO request = new ProductosDTO(2L, "PruebaIS", new Date(System.currentTimeMillis()));

        mockMvc.perform(MockMvcRequestBuilders.post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isNotFound());
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
