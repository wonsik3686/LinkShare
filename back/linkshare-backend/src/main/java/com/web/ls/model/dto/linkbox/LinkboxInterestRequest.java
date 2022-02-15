package com.web.ls.model.dto.linkbox;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LinkboxInterestRequest {

    @ApiModelProperty(value = "linkbox boxid", position = 1, required = true)
    @NotNull
    private Integer boxid;

    @ApiModelProperty(value = "linkbox interest name", position = 2, required = true)
    @NotNull
    private List<String> interests;
}
