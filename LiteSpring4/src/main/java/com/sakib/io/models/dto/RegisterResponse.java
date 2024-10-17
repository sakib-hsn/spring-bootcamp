package com.sakib.io.models.dto;

import com.sakib.io.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {
    private User user;
}
