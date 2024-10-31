package com.codemaster.io.models.dto;

import com.codemaster.io.models.Permission;
import com.codemaster.io.models.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class AddUserRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
    private List<Permission> permissions;
}