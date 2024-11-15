package com.duoc.tienda_productos.config;

import com.duoc.tienda_productos.model.Producto;
import com.duoc.tienda_productos.model.builder.ProductoBuilder;
import com.duoc.tienda_productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) {
        if (productoRepository.count() == 0) {
            System.out.println("Inicializando datos de productos...");

            Producto laptop = new ProductoBuilder()
                .conNombre("Laptop Gaming Pro")
                .conDescripcion("Laptop gaming con RTX 3070, 16GB RAM, 1TB SSD")
                .conPrecio(new BigDecimal("1500000"))
                .conStock(10)
                .conCategoria("Computadores")
                .conImagenUrl("laptop.jpg")
                .activo(true)
                .build();
            productoRepository.save(laptop);

            Producto smartphone = new ProductoBuilder()
                .conNombre("Smartphone XYZ")
                .conDescripcion("Smartphone de última generación, 128GB")
                .conPrecio(new BigDecimal("700000"))
                .conStock(20)
                .conCategoria("Celulares")
                .conImagenUrl("smartphone.jpg")
                .activo(true)
                .build();
            productoRepository.save(smartphone);

            Producto tablet = new ProductoBuilder()
                .conNombre("Tablet Ultra")
                .conDescripcion("Tablet 10 pulgadas, 64GB almacenamiento")
                .conPrecio(new BigDecimal("350000"))
                .conStock(15)
                .conCategoria("Tablets")
                .conImagenUrl("tablet.jpg")
                .activo(true)
                .build();
            productoRepository.save(tablet);

            System.out.println("Datos de productos inicializados!");
        }
    }
}