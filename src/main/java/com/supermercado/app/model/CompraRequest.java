package com.supermercado.app.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompraRequest {
    @NotNull(message = "Debe indicar el id del producto")
    private Long productoId;

    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private int cantidad;
}
