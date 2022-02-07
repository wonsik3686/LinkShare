package com.web.ls.model.dto.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SigninRequest {

	@ApiModelProperty(required = true)
    private String email;
    
    @ApiModelProperty(required = true)
    private String password;
}
