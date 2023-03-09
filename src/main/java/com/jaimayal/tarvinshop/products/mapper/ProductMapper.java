package com.jaimayal.tarvinshop.products.mapper;

import com.jaimayal.tarvinshop.products.dto.ProductDTO;
import com.jaimayal.tarvinshop.products.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring", 
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {
    ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    List<ProductDTO> mapToProductDTOList(List<Product> productList);
    ProductDTO mapToProductDTO(Product product);
    List<Product> mapToProductList(List<ProductDTO> productDTOList);
    Product mapToProduct(ProductDTO productDTO);
}
