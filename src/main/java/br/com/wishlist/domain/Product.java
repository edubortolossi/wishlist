package br.com.wishlist.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@Document
public class Product {

    @Id
    String id;
    String name;
    String description;
    BigDecimal value;
    String type;
    String userIdentification;
}
