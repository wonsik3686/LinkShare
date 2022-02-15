package com.web.ls.model.dto.linkbox.comment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BoxCommentUpdateRequest implements Serializable {
    @ApiModelProperty(value = "box comment id", position = 1)
    private final Integer id;
    @ApiModelProperty(value = "box comment content", position = 2)
    private final String content;
}
