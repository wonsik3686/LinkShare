package com.web.ls.model.dto.linkbox.comment;

import com.web.ls.model.entity.BoxComment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Builder
@Data
public class BoxCommentResponse implements Serializable {

    @ApiModelProperty(value = "comment id", position = 1)
    private Integer id;

    @ApiModelProperty(value = "user id", position = 2)
    private Integer uid;

    @ApiModelProperty(value = "box id", position = 3)
    private Integer boxid;

    @ApiModelProperty(value = "comment content", position = 4)
    private String content;

    @ApiModelProperty(value = "link id", position = 5)
    private Instant regtime;

    @ApiModelProperty(value = "link id", position = 6)
    private String nickName;

    public static BoxCommentResponse fromEntity(BoxComment comment) {
        return BoxCommentResponse.builder()
                .id(comment.getId())
                .uid(comment.getUid())
                .boxid(comment.getBoxid())
                .content(comment.getContent())
                .regtime(comment.getRegtime())
                .build();
    }
}
