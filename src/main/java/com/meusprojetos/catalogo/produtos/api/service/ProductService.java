package com.meusprojetos.catalogo.produtos.api.service;

import com.meusprojetos.catalogo.produtos.api.dto.ProductDTO;
import com.meusprojetos.catalogo.produtos.api.entity.Product;
import com.meusprojetos.catalogo.produtos.api.entity.User;
import com.meusprojetos.catalogo.produtos.api.exception.ProductNotFoundException;
import com.meusprojetos.catalogo.produtos.api.mapper.ProductMapper;
import com.meusprojetos.catalogo.produtos.api.repository.ProductRepository;
import com.meusprojetos.catalogo.produtos.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);

        User authenticatedUser = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        product.setUser(authenticatedUser);
        product.setCreatedAt(LocalDateTime.now());

        Product saved = productRepository.save(product);

        return productMapper.toProductDTO(saved);
    }

    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();

        return productMapper.toListProductDTO(products);
    }

    public ProductDTO getProductById(Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado com id: " + id));

        return productMapper.toProductDTO(existingProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado com id: " + id));

        productMapper.updateProductFromDTO(productDTO, existingProduct);

        Product updatedProduct = productRepository.save(existingProduct);

        return productMapper.toProductDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado com id " + id));

        productRepository.deleteById(id);
    }
}
