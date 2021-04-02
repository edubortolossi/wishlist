package br.com.wishlist.controller;

import br.com.wishlist.domain.Product;
import br.com.wishlist.dto.ProductDto;
import br.com.wishlist.dto.ProductResponse;
import br.com.wishlist.service.WishlistService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping( value = "/wishlist", produces = MediaType.APPLICATION_JSON_VALUE )
public class WishlistController {

    @Autowired
    WishlistService wishlistService;

    @PostMapping
    @ApiImplicitParam( name = "Identification", value = "Identification", paramType = "header", required = true)
    public ResponseEntity< Product > saveProduct( @RequestHeader("Identification") String userIdentification,
                                                  @RequestBody ProductDto product ) {
        return ResponseEntity.status( HttpStatus.CREATED ).body( wishlistService.save( product, userIdentification ) );
    }

    @DeleteMapping( value = "/{id}" )
    public ResponseEntity< String > deleteProduct( @RequestHeader("Identification") String userIdentification,
                                                   @PathVariable( value = "id" ) Long idProduct ) {

//        campanhaService.deletar( id );

        return ResponseEntity.ok( "Producto retirado da Lista de Desejos com Sucesso" );
    }

    @GetMapping
    public ResponseEntity< Set< ProductResponse > > findAllProducts( @RequestHeader("Identification") String userIdentification ) {

//        return ResponseEntity.ok( campanhaService.listar() );
        return null;
    }

    @GetMapping( value = "/{name}" )
    public ResponseEntity< Set< ProductResponse > > findProductByName( @RequestHeader("Identification") String userIdentification,
                                                                        @PathVariable( value = "name" ) Long nameProduct  ) {
//        return ResponseEntity.ok( campanhaService.listar() );
        return null;
    }


}
