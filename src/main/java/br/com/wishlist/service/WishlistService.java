package br.com.wishlist.service;

import br.com.wishlist.domain.Product;
import br.com.wishlist.dto.ProductDto;
import br.com.wishlist.mapper.ProductMapper;
import br.com.wishlist.repository.WishlistRepository;
import br.com.wishlist.utils.BadRequestException;
import br.com.wishlist.utils.NoContentException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @SneakyThrows
    public Product save( ProductDto product, String userIdentification ) {
        final List< Product > products = wishlistRepository.findByUserIdentification( userIdentification );
        if ( products.size() < 20 ) {
            return wishlistRepository.save( ProductMapper.dtoToEntity( product, userIdentification ) );
        } else {
            throw new BadRequestException( "Limite de Produtos na Lista Atingida.", null );
        }
    }

    @SneakyThrows
    public Boolean delete( String id ) {
        final Optional< Product > productOptional = wishlistRepository.findById( id );

        if( productOptional.isPresent() ) {
            wishlistRepository.delete( productOptional.get() );
        } else {
            throw new NoContentException( "Nenhum produto encontrado para exclusão", null );
        }
        return true;
    }

    @SneakyThrows
    public List< Product > listAllProductsByUser( String userIdentification ) {
        final List< Product > products = wishlistRepository.findByUserIdentification( userIdentification );
        if ( products.size() > 0 ) {
            return products;
        } else {
            throw new NoContentException( "Nenhum produto encontrado", null );
        }
    }

    @SneakyThrows
    public List< Product > listProductByUser( String userIdentification, String productName ) {
        final List< Product > products = wishlistRepository.findByUserIdentificationAndName( userIdentification, productName );
        if ( products.size() > 0 ) {
            return products;
        } else {
            throw new NoContentException( "Nenhum produto encontrado", null );
        }
    }
}
