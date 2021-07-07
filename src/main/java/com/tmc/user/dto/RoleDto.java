package com.tmc.user.dto;

import com.tmc.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private String roleId;

    private String roleName;
    private UserDto user;

}
