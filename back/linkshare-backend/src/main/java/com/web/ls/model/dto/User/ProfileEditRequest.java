package com.web.ls.model.dto.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProfileEditRequest {
	
	@ApiModelProperty(required = true)
    private String email;	

	@ApiModelProperty(required = true)
    private String nickname;
    
    @ApiModelProperty(required = false)
    private String imagePath;
    
    @ApiModelProperty(required = false)
    private String introduce;
}
