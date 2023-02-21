package com.jaimayal.tarvinshop.Product.mapper;

import com.jaimayal.tarvinshop.Product.dto.ProductDTO;
import com.jaimayal.tarvinshop.Product.entity.Product;
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

    ProductDTO mapToProductDTO(Product product);
    Product mapToProduct(ProductDTO productDTO);
    List<Product> mapToProductList(List<ProductDTO> productDTOList);
    List<ProductDTO> mapToProductDTOList(List<Product> productList);
}
