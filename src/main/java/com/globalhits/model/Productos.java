package com.globalhits.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NamedStoredProcedureQuery(
        name = "insertarProductoYListar",
        procedureName = "sp_insertAndListProducts",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "idProducto", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "nombre", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "fec_registro", type = Date.class),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "cursorProductos", type = void.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "codigoRespuesta", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "mensajeRespuesta", type = String.class)
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Productos {

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_seq")
    @Column(unique = true)
    private Long id;

    @Column(length = 20, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Date fec_registro;
}
