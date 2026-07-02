package com.prueba.ejemplo.service;

import com.prueba.ejemplo.model.Producto;
import com.prueba.ejemplo.repository.ProductoRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private static final Logger log = LoggerFactory.getLogger(ProductoService.class);
    private final ProductoRepository productoRepository;
    private final Counter contadorProductosCreados;

    @Autowired
    public ProductoService(ProductoRepository productoRepository, MeterRegistry meterRegistry) {
        this.productoRepository = productoRepository;
        this.contadorProductosCreados = Counter.builder("productos_creados_total")
                .description("Número total de productos creados en la simulación")
                .tag("tipo", "inventario")
                .register(meterRegistry);
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Producto guardar(Producto producto) {
        try {
            Producto guardado = productoRepository.save(producto);
            contadorProductosCreados.increment();

            return guardado;
        } catch (Exception e) {
            log.error("Error crítico al persistir el producto en la base de datos: {}", e.getMessage());
            throw e;
        }
    }
}