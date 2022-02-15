package com.web.ls.model.dto.linkbox;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LinkboxCreateRequest implements Serializable {
    @ApiModelProperty(value = "user id", position = 1)
    private final int uid;
    @ApiModelProperty(value = "linkbox title", position = 2)
    private final String title;
    @ApiModelProperty(value = "linkbox description", position = 3)
    private final String desc;
    @ApiModelProperty(value = "interest of interests. String array", position = 4)
    private final List<String> interests;
}
