package com.supermercado.app.controller;

import com.supermercado.app.model.CompraRequest;
import com.supermercado.app.model.CompraResponse;
import com.supermercado.app.model.Producto;
import com.supermercado.app.service.SupermercadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/supermercado")
@CrossOrigin(origins = "*")
public class SupermercadoController {

    private final SupermercadoService service;

    public SupermercadoController(SupermercadoService service) {
        this.service = service;
    }

    @GetMapping("/ping")
    public Map<String, Object> ping() {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Microservicio Supermercado OK");
        response.put("estado", "activo");
        response.put("fecha", LocalDateTime.now().toString());
        return response;
    }

    @GetMapping("/productos")
    public List<Producto> productos() {
        return service.listarProductos();
    }

    @PostMapping("/comprar")
    public ResponseEntity<CompraResponse> comprar(@Valid @RequestBody CompraRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.comprar(request));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Map<String, String>> manejarErrores(Exception ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidaciones(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(Map.of("error", mensaje));
    }
}
