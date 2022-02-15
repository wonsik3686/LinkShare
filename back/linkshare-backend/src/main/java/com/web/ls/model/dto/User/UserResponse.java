package com.web.ls.model.dto.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

    @ApiModelProperty(value = "user id", required = true)
    private Integer uid;

    @ApiModelProperty(value = "user email", required = true)
    private String email;
    
    @ApiModelProperty(value = "user nickname", required = true)
    private String nickname;
    
}
