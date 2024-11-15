package com.duoc.tienda_productos.service;

import com.duoc.tienda_productos.model.Producto;
import com.duoc.tienda_productos.model.builder.ProductoBuilder;
import com.duoc.tienda_productos.repository.ProductoRepository;
import com.duoc.tienda_productos.dto.ProductoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findByActivoTrue();
    }

    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .filter(Producto::getActivo);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public Producto crearProducto(ProductoDTO productoDTO) {
        Producto producto = new ProductoBuilder()
            .conNombre(productoDTO.getNombre())
            .conDescripcion(productoDTO.getDescripcion())
            .conPrecio(productoDTO.getPrecio())
            .conStock(productoDTO.getStock())
            .conCategoria(productoDTO.getCategoria())
            .conImagenUrl(productoDTO.getImagenUrl())
            .activo(true)
            .build();
        return productoRepository.save(producto);
    }

    public Optional<Producto> actualizarProducto(Long id, ProductoDTO productoDTO) {
        return productoRepository.findById(id)
            .map(productoExistente -> {
                Producto productoActualizado = new ProductoBuilder()
                    .conNombre(productoDTO.getNombre() != null ? productoDTO.getNombre() : productoExistente.getNombre())
                    .conDescripcion(productoDTO.getDescripcion() != null ? productoDTO.getDescripcion() : productoExistente.getDescripcion())
                    .conPrecio(productoDTO.getPrecio() != null ? productoDTO.getPrecio() : productoExistente.getPrecio())
                    .conStock(productoDTO.getStock() != null ? productoDTO.getStock() : productoExistente.getStock())
                    .conCategoria(productoDTO.getCategoria() != null ? productoDTO.getCategoria() : productoExistente.getCategoria())
                    .conImagenUrl(productoDTO.getImagenUrl() != null ? productoDTO.getImagenUrl() : productoExistente.getImagenUrl())
                    .activo(productoExistente.getActivo())
                    .build();
                productoActualizado.setId(id);
                return productoRepository.save(productoActualizado);
            });
    }

    public Optional<Producto> actualizarStock(Long id, Integer cantidad) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setStock(cantidad);
                    return productoRepository.save(producto);
                });
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}