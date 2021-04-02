package br.com.wishlist.mapper;

import br.com.wishlist.domain.Product;
import br.com.wishlist.dto.ProductDto;

import java.math.BigDecimal;

public class ProductMapper {

    public static Product createProductDTOToEntity( ProductDto productDto, String userIdentification ) {

        return Product.builder()
                .description( productDto.getDescription() )
                .name( productDto.getName() )
                .value( new BigDecimal( productDto.getValue() ) )
                .type( productDto.getType() )
                .userIdentification( userIdentification ).build();
    }
}
