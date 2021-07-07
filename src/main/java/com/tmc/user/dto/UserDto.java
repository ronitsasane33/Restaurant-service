package com.tmc.user.dto;

import com.tmc.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userId;
    private String username;
    private String password;
    private boolean active;
    private String role;
}
