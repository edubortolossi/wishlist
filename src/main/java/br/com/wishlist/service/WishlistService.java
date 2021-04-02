package br.com.wishlist.service;

import br.com.wishlist.domain.Product;
import br.com.wishlist.dto.ProductDto;
import br.com.wishlist.mapper.ProductMapper;
import br.com.wishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public Product save( ProductDto product, String userIdentification ) {
        return wishlistRepository.save( ProductMapper.createProductDTOToEntity( product, userIdentification ) );
    }
}
