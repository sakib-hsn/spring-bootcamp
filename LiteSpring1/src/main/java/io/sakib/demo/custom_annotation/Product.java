package io.sakib.demo.custom_annotation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String id;

    @CustomValue(value = 10)
    private int price;
}
