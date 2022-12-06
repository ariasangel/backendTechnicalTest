package mca.backendTechnicalTest.similarProducts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    String id;
    String name;
    Integer price;
    Boolean availability;
}
