package com.meusprojetos.catalogo.produtos.api.mapper;

import com.meusprojetos.catalogo.produtos.api.dto.ProductDTO;
import com.meusprojetos.catalogo.produtos.api.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct (ProductDTO productDTO);

    ProductDTO toProductDTO(Product product);

    List<ProductDTO> toListProductDTO(List<Product> products);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateProductFromDTO(ProductDTO dto, @MappingTarget Product product);
}
