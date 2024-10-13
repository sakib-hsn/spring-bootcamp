package io.sakib.demo.custom_annotation;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String name;

    @CustomValue(value = 500)
    private int balance;
}
