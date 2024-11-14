package com.duoc.tienda_productos.service;

import com.duoc.tienda_productos.model.Producto;
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
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setCategoria(productoDTO.getCategoria());
        producto.setImagenUrl(productoDTO.getImagenUrl());
        producto.setActivo(true);
        return productoRepository.save(producto);
    }

    public Optional<Producto> actualizarProducto(Long id, ProductoDTO productoDTO) {
        return productoRepository.findById(id)
                .map(producto -> {
                    if (productoDTO.getNombre() != null) {
                        producto.setNombre(productoDTO.getNombre());
                    }
                    if (productoDTO.getDescripcion() != null) {
                        producto.setDescripcion(productoDTO.getDescripcion());
                    }
                    if (productoDTO.getPrecio() != null) {
                        producto.setPrecio(productoDTO.getPrecio());
                    }
                    if (productoDTO.getStock() != null) {
                        producto.setStock(productoDTO.getStock());
                    }
                    if (productoDTO.getCategoria() != null) {
                        producto.setCategoria(productoDTO.getCategoria());
                    }
                    if (productoDTO.getImagenUrl() != null) {
                        producto.setImagenUrl(productoDTO.getImagenUrl());
                    }
                    return productoRepository.save(producto);
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