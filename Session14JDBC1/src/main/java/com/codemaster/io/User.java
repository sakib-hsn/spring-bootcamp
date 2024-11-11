package com.codemaster.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;

    private String name;

    private String email;

    private Integer age;

    private String country;

//    @Override
//    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//        return User.builder()
//                .id(rs.getInt("id"))
//                .name(rs.getString("name"))
//                .email(rs.getString("email"))
//                .age(rs.getInt("age"))
//                .country(rs.getString("country"))
//                .build();
//    }
}
