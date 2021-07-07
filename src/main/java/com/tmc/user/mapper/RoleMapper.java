package com.tmc.user.mapper;

import com.tmc.user.dto.RoleDto;
import com.tmc.user.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses={RoleMapper.class})
public interface RoleMapper {
    Role toRole(RoleDto roleDto);
    RoleDto toRoleDto(Role role);
    List<Role> toRoles(List<RoleDto> roleDtos);
    List<RoleDto> toRoleDtos(List<Role> roles);

}
