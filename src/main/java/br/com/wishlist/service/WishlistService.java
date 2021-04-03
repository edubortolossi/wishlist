package br.com.wishlist.service;

import br.com.wishlist.domain.Product;
import br.com.wishlist.dto.ProductDto;
import br.com.wishlist.mapper.ProductMapper;
import br.com.wishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public Product save( ProductDto product, String userIdentification ) {
        return wishlistRepository.save( ProductMapper.createProductDTOToEntity( product, userIdentification ) );
    }

    public ResponseEntity delete( String id ) {
        final Optional< Product > productOptional = wishlistRepository.findById( id );

        if( productOptional.isPresent() ) {
            wishlistRepository.delete( productOptional.get() );
        } else {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).build();
        }
        return ResponseEntity.ok( "Produto retirado da Lista de Desejos com Sucesso" );
    }
}
