package br.com.wishlist.service;

import br.com.wishlist.domain.Product;
import br.com.wishlist.dto.ProductDto;
import br.com.wishlist.mapper.ProductMapper;
import br.com.wishlist.repository.WishlistRepository;
import br.com.wishlist.utils.BadRequestException;
import br.com.wishlist.utils.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public Product save( ProductDto product, String userIdentification ) throws BadRequestException {
        final List< Product > products = wishlistRepository.findByUserIdentification( userIdentification );
        if ( products.size() < 20 ) {
            return wishlistRepository.save( ProductMapper.dtoToEntity( product, userIdentification ) );
        } else {
            throw new BadRequestException( "Limite de Produtos na Lista Atingida", null );
        }
    }

    public Boolean delete( String id ) throws NoContentException {
        final Optional< Product > productOptional = wishlistRepository.findById( id );

        if( productOptional.isPresent() ) {
            wishlistRepository.delete( productOptional.get() );
        } else {
            throw new NoContentException( "Nenhum produto encontrado para exclusão", null );
        }
        return true;
    }

    @Cacheable
    public List< Product > listAllProductsByUser( String userIdentification ) throws NoContentException {
        final List< Product > products = wishlistRepository.findByUserIdentification( userIdentification );
        if ( products.size() > 0 ) {
            return products;
        } else {
            throw new NoContentException( "Nenhum produto encontrado", null );
        }
    }

    public List< Product > listProductByUser( String userIdentification, String productName ) throws NoContentException {
        final List< Product > products = wishlistRepository.findByUserIdentificationAndName( userIdentification, productName );
        if ( products.size() > 0 ) {
            return products;
        } else {
            throw new NoContentException( "Nenhum produto encontrado", null );
        }
    }
}
