package com.duoc.tienda_productos.config;

import com.duoc.tienda_productos.model.Producto;
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

            // Producto 1
            Producto producto1 = new Producto();
            producto1.setNombre("Laptop Gaming Pro");
            producto1.setDescripcion("Laptop gaming con RTX 3070, 16GB RAM, 1TB SSD");
            producto1.setPrecio(new BigDecimal("1500000"));
            producto1.setStock(10);
            producto1.setCategoria("Computadores");
            producto1.setImagenUrl("laptop.jpg");
            producto1.setActivo(true);
            productoRepository.save(producto1);

            // Producto 2
            Producto producto2 = new Producto();
            producto2.setNombre("Smartphone XYZ");
            producto2.setDescripcion("Smartphone de última generación, 128GB");
            producto2.setPrecio(new BigDecimal("700000"));
            producto2.setStock(20);
            producto2.setCategoria("Celulares");
            producto2.setImagenUrl("smartphone.jpg");
            producto2.setActivo(true);
            productoRepository.save(producto2);

            // Producto 3
            Producto producto3 = new Producto();
            producto3.setNombre("Tablet Ultra");
            producto3.setDescripcion("Tablet 10 pulgadas, 64GB almacenamiento");
            producto3.setPrecio(new BigDecimal("350000"));
            producto3.setStock(15);
            producto3.setCategoria("Tablets");
            producto3.setImagenUrl("tablet.jpg");
            producto3.setActivo(true);
            productoRepository.save(producto3);

            System.out.println("Datos de productos inicializados!");
        } else {
            System.out.println("La base de datos ya contiene productos. No se requiere inicialización.");
        }
    }
}