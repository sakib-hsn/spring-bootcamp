package com.codemaster.io.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    private String email;

    private String name;

    private Integer age;

}
