package com.supermercado.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompraResponse {
    private String mensaje;
    private Producto producto;
    private int cantidadComprada;
    private int totalPagado;
}
