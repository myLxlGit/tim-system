package com.gre.lxl.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserCachedVO extends UserDTO {

    @JsonIgnore
    private String pwd;

    private String delFlag;

    private String userStatus;

    private String avatar;

//    @JsonIgnore
//    private OrganVO organ;

//    @JsonIgnore
//    private List<RoleVO> roles;

    //todo 补充爱好
}
