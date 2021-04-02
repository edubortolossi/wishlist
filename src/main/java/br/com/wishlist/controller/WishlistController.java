package br.com.wishlist.controller;

import br.com.wishlist.dto.ProductDto;
import br.com.wishlist.dto.ProductResponse;
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

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping( value = "/wishlist", produces = MediaType.APPLICATION_JSON_VALUE )
public class WishlistController {

    @PostMapping
    public ResponseEntity< Long > saveProduct( @Valid @RequestBody ProductDto product ) {
//        return ResponseEntity.status( HttpStatus.CREATED ).body(  );
        return null;
    }

    @DeleteMapping( value = "/{id}" )
    public ResponseEntity< String > deleteProduct( @RequestHeader("Identification") Long userIdentification,
                                                   @PathVariable( value = "id" ) Long idProduct ) {

//        campanhaService.deletar( id );

        return ResponseEntity.ok( "Producto retirado da Lista de Desejos com Sucesso" );
    }

    @GetMapping
    public ResponseEntity< Set< ProductResponse > > findAllProducts( @RequestHeader("Identification") Long userIdentification ) {

//        return ResponseEntity.ok( campanhaService.listar() );
        return null;
    }

    @GetMapping( value = "/{name}" )
    public ResponseEntity< Set< ProductResponse > > findProductByName( @RequestHeader("Identification") Long userIdentification,
                                                                        @PathVariable( value = "name" ) Long nameProduct  ) {
//        return ResponseEntity.ok( campanhaService.listar() );
        return null;
    }


}
