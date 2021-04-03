package br.com.wishlist;

import br.com.wishlist.domain.Product;
import br.com.wishlist.dto.ProductDto;
import br.com.wishlist.repository.WishlistRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith( SpringRunner.class )
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataMongo
@ActiveProfiles( "test" )
public class WishlistControllerTest {

    private static final String DEFAULT_PRODUCT_PATH = "/wishlist";
    private static final String DELETE_PRODUCT_PATH = "/wishlist/{id}";
    private static final String FIND_BY_NAME_PRODUCT_PATH = "/wishlist/{name}";

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public WishlistRepository wishlistRepository;

    @Before
    public void setUp() {
        wishlistRepository.save( Product.builder().userIdentification( "24720818048" ).id( "1a" )
                .name( "Playstation 1" ).value( new BigDecimal( 550.00 ) ).type( "Eletronico" )
                .description( "Primeira Geração de video Game" ).build() );
        wishlistRepository.save( Product.builder().userIdentification( "24720818048" ).id( "2a" )
                .name( "Playstation 2" ).value( new BigDecimal( 1050.00 ) ).type( "Eletronico" )
                .description( "Segunda Geração de video Game" ).build() );
        wishlistRepository.save( Product.builder().userIdentification( "24720818048" ).id( "3a" )
                .name( "Playstation 3" ).value( new BigDecimal( 1550.00 ) ).type( "Eletronico" )
                .description( "Terceira Geração de video Game" ).build() );
        wishlistRepository.save( Product.builder().userIdentification( "24720818048" ).id( "4a" )
                .name( "Playstation 4" ).value( new BigDecimal( 2550.00 ) ).type( "Eletronico" )
                .description( "Quarta Geração de video Game" ).build() );

        wishlistRepository.save( Product.builder().userIdentification( "28459085040" ).id( "1b" )
                .name( "Playstation 5" ).value( new BigDecimal( 5550.00 ) ).type( "Eletronico" )
                .description( "Ultima Geração de video Game" ).build() );

        for( int i = 1; i <= 20; i++ ) {
            wishlistRepository.save( Product.builder().userIdentification( "85825306099" ).id( String.valueOf( i ) )
                    .name( "Nintendo " + i ).value( new BigDecimal( i ) ).type( "Eletronico" )
                    .description( "video Game " + i ).build() );
        }
    }

    @After
    public void tearDown() {
        wishlistRepository.deleteAll();
    }


    private ProductDto createRequestMockDto() {
        return ProductDto.builder()
                .name( "Playstation 5" )
                .description( "Video Game de ultima Geração" )
                .type( "Eletronico" )
                .value( "5000.00" )
                .build();
    }

    @Test
    public void addProductSuccess() throws Exception {
        mockMvc.perform( post( DEFAULT_PRODUCT_PATH )
                .contentType( MediaType.APPLICATION_JSON )
                .header( "Identification", "17896340089" )
                .content( objectToJson( createRequestMockDto() ) ) )
                .andExpect( status().is2xxSuccessful() );
    }

    @Test
    public void addProductProductLimitError() throws Exception {
        mockMvc.perform( post( DEFAULT_PRODUCT_PATH )
                .contentType( MediaType.APPLICATION_JSON )
                .header( "Identification", "85825306099" )
                .content( objectToJson( createRequestMockDto() ) ) )
                .andExpect( status().isBadRequest() );
    }


    @Test
    public void deleteProductSuccess() throws Exception {
        mockMvc.perform( delete( DELETE_PRODUCT_PATH, "1a" )
                .contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( status().is2xxSuccessful() );
    }

    @Test
    public void deleteProductNoContentError() throws Exception {
        mockMvc.perform( delete( DELETE_PRODUCT_PATH, "5b" )
                .contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( status().isNoContent() );
    }

    @Test
    public void findAllProductsSuccess() throws Exception {
        mockMvc.perform( get( DEFAULT_PRODUCT_PATH )
                .contentType( MediaType.APPLICATION_JSON )
                .header( "Identification", "24720818048" ) )
                .andExpect( status().is2xxSuccessful() )
                .andExpect( jsonPath( "$" ).isArray() )
                .andExpect( jsonPath( "$", hasSize( 4 ) ) );
    }

    @Test
    public void findAllProductsNoContentError() throws Exception {
        mockMvc.perform( get( DEFAULT_PRODUCT_PATH )
                .contentType( MediaType.APPLICATION_JSON )
                .header( "Identification", "24720818011" ) )
                .andExpect( status().isNoContent() );
    }

    @Test
    public void findProductByNameSuccess() throws Exception {
        mockMvc.perform( get( FIND_BY_NAME_PRODUCT_PATH, "Playstation 1" )
                .contentType( MediaType.APPLICATION_JSON )
                .header( "Identification", "24720818048" ) )
                .andExpect( status().is2xxSuccessful() )
                .andExpect( jsonPath( "$" ).isArray() )
                .andExpect( jsonPath( "$", hasSize( 1 ) ) )
                .andExpect( jsonPath( "$.[0].id", is( "1a" ) ) )
                .andExpect( jsonPath( "$.[0].name", is( "Playstation 1" ) ) )
                .andExpect( jsonPath( "$.[0].type", is( "Eletronico" ) ) )
                .andExpect( jsonPath( "$.[0].description", is( "Primeira Geração de video Game" ) ) );
    }

    @Test
    public void findProductByNameNoCOntentError() throws Exception {
        mockMvc.perform( get( FIND_BY_NAME_PRODUCT_PATH, "Playstation 5" )
                .contentType( MediaType.APPLICATION_JSON )
                .header( "Identification", "24720818048" ) )
                .andExpect( status().isNoContent() );
    }

    protected String objectToJson( Object object ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString( object );
        } catch( JsonProcessingException var3 ) {
            return null;
        }
    }
}
