package com.web.ls.model.dto.linkbox.like;

import com.web.ls.model.entity.Likes;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Builder
@Data
public class LikesCreateRequest implements Serializable {

    @ApiModelProperty(value = "user id", position = 1, required = true)
    @NotBlank
    private final Integer uid;
    @ApiModelProperty(value = "link box id", position = 2, required = true)
    @NotBlank
    private final Integer boxid;

    public Likes toEntity() {
        return Likes.builder()
                .uid(this.uid)
                .boxid(this.boxid)
                .build();
    }
}
