package br.com.wishlist;

import br.com.wishlist.dto.ProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith( SpringRunner.class )
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataMongo
@ActiveProfiles( "test" )
public class WishlistControllerTest {

    private static final String ADD_PRODUCT_PATH = "/wishlist";

    @Autowired
    public MockMvc mockMvc;

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
        mockMvc.perform( post( ADD_PRODUCT_PATH )
                .contentType( MediaType.APPLICATION_JSON )
                .header( "Identification", "36768246879" )
                .content( objectToJson( createRequestMockDto() ) ) )
                .andExpect( status().is2xxSuccessful() );
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
