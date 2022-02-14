package com.web.ls.model.dto.linkbox;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LinkboxInterestRequest {

    @ApiModelProperty(value = "linkbox boxid", position = 1, required = true)
    @NotNull
    private Integer boxid;

    @ApiModelProperty(value = "linkbox interest name", position = 2, required = true)
    @NotBlank
    private String interest;
}
