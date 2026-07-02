package com.prueba.ejemplo.controller;

import com.prueba.ejemplo.model.Producto;
import com.prueba.ejemplo.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);
    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        log.info("Recibida petición GET para listar todos los productos");
        List<Producto> productos = productoService.obtenerTodos();
        log.info("Se encontraron {} productos en la base de datos", productos.size());
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        log.info("Recibida petición POST para crear un nuevo producto: {}", producto.getNombre());

        if (producto.getPrecio() == null || producto.getPrecio() < 0) {
            log.warn("Intento de creación de producto con precio inválido");
            return ResponseEntity.badRequest().build();
        }

        Producto nuevoProducto = productoService.guardar(producto);
        log.info("Producto creado exitosamente con ID: {}", nuevoProducto.getId());
        return ResponseEntity.ok(nuevoProducto);
    }
}
