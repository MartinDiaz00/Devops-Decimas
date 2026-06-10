package com.supermercado.app.service;

import com.supermercado.app.model.CompraRequest;
import com.supermercado.app.model.CompraResponse;
import com.supermercado.app.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupermercadoService {

    private final List<Producto> productos = new ArrayList<>();

    public SupermercadoService() {
        productos.add(new Producto(1L, "Pan familiar", "Panadería", 1800, 20));
        productos.add(new Producto(2L, "Leche 1L", "Lácteos", 1200, 35));
        productos.add(new Producto(3L, "Arroz 1kg", "Abarrotes", 1600, 25));
        productos.add(new Producto(4L, "Manzanas 1kg", "Frutas", 2100, 18));
        productos.add(new Producto(5L, "Detergente 3L", "Limpieza", 5200, 10));
    }

    public List<Producto> listarProductos() {
        return productos;
    }

    public Optional<Producto> buscarPorId(Long id) {
        return productos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public CompraResponse comprar(CompraRequest request) {
        Producto producto = buscarPorId(request.getProductoId())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        if (producto.getStock() < request.getCantidad()) {
            throw new IllegalArgumentException("No hay stock suficiente para la compra");
        }

        producto.setStock(producto.getStock() - request.getCantidad());
        int total = producto.getPrecio() * request.getCantidad();

        return new CompraResponse("Compra realizada correctamente", producto, request.getCantidad(), total);
    }
}
