package com.web.ls.model.dto.User;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInterestRequest {

    @ApiModelProperty(value = "user id", required = true)
    @NotNull
    private Integer uid;

    @ApiModelProperty(value = "interest name", required = true)
    @NotNull
    private List<String> interests;
}
