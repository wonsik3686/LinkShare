package com.web.ls.model.dto.linkbox;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LinkboxUpdateRequest implements Serializable {

    @ApiModelProperty(value = "linkbox boxid", position = 1)
    private final int boxid;
    @ApiModelProperty(value = "linkbox title", position = 2)
    private final String title;
    @ApiModelProperty(value = "linkbox description", position = 3)
    private final String desc;
}
