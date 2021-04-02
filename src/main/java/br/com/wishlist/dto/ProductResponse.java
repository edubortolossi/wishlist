package br.com.wishlist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class ProductResponse {
    private String name;
    private String description;
    private Long value;
    private String type;
    private String userIdentification;
}
