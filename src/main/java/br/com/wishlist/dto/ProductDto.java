package br.com.wishlist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@ApiModel( value = "produto", description = "Data transfer from product" )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class ProductDto {

    @Min( value = 0 )
    @ApiModelProperty( required = true )
    @NotBlank( message = "{required.field}" )
    private String name;

    @ApiModelProperty( required = true )
    @NotBlank( message = "{required.field}" )
    private String description;

    @ApiModelProperty( required = true )
    @NotBlank( message = "{required.field}" )
    private String value;

    @ApiModelProperty( required = true )
    @NotBlank( message = "{required.field}" )
    private String type;

    @ApiModelProperty( required = true )
    @NotBlank( message = "{required.field}" )
    private String userIdentification;
}
