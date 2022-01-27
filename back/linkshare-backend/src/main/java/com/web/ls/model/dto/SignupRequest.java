package com.web.ls.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.web.ls.model.entity.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupRequest {
    @ApiModelProperty(required = true)
    @NotBlank
    @Email
    private String email;
    
    @ApiModelProperty(required = true)
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$")
    private String password;
    
    @ApiModelProperty(required = true)
    @NotBlank
    private String nickname;
    
    public User toEntity() {
    	return User.builder()
    			.email(this.email)
    			.password(this.password)
    			.nickname(this.nickname)
    			.admin(false)
    			.build();
    }
}