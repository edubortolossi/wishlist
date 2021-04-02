package br.com.wishlist.repository;

import br.com.wishlist.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends MongoRepository< Product, String > {



}
