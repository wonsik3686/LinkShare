package com.web.ls.model.dto.User;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInterestRequest {

    @ApiModelProperty(value = "user id", position = 1, required = true)
    @NotNull
    private Integer uid;

    @ApiModelProperty(value = "interest name", position = 2, required = true)
    @NotNull
    private List<String> interests;
}
