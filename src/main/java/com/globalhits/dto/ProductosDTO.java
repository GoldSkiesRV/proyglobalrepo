package com.globalhits.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductosDTO {
    @EqualsAndHashCode.Include
    @NotNull
    private Long idProducto;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 70, message = "el nombre debe tener un tamaño de 5 caracteres")
    private String nombreProducto;

    @NotNull
    private Date fec_registroProducto;
}
