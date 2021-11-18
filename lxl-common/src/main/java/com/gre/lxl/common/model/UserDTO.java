package com.gre.lxl.common.model;

import com.gre.lxl.common.core.domain.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserDTO extends BaseDTO {

    private String userId;

    private String jobNo;

    private String fullName;

    private String userName;

    private String userSex;

    private String userTel;

    private String userEmail;

    private String hasWeChat;

    public UserDTO(String userId, String jobNo, String fullName, String userName, String userSex, String userTel, String userEmail, String hasWeChat) {
        this.userId = userId;
        this.jobNo = jobNo;
        this.fullName = fullName;
        this.userName = userName;
        this.userSex = userSex;
        this.userTel = userTel;
        this.userEmail = userEmail;
        this.hasWeChat = hasWeChat;
    }
}