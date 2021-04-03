package br.com.wishlist.repository;

import br.com.wishlist.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends MongoRepository< Product, String > {

    List< Product > findByUserIdentification( String userIdentification );

    List< Product > findByUserIdentificationAndName( String userIdentification, String productName );

}
