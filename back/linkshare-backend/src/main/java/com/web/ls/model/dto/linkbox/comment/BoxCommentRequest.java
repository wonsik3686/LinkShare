package com.web.ls.model.dto.linkbox.comment;

import com.web.ls.model.entity.BoxComment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class BoxCommentRequest implements Serializable {
    @ApiModelProperty(value = "user id", position = 1)
    private final Integer uid;
    @ApiModelProperty(value = "box id", position = 2)
    private final Integer boxid;
    @ApiModelProperty(value = "comment content", position = 3)
    private final String content;

    public BoxComment toEntity() {
        return BoxComment.builder()
                .uid(this.uid)
                .boxid(this.boxid)
                .content(this.content)
                .build();
    }
}
