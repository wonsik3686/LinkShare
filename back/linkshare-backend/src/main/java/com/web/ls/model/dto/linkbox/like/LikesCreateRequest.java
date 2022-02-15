package com.web.ls.model.dto.linkbox.like;

import com.web.ls.model.entity.Likes;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class LikesCreateRequest {

    @ApiModelProperty(value = "user id", required = true)
    @NotNull
    private Integer uid;
    @ApiModelProperty(value = "link box id", required = true)
    @NotNull
    private Integer boxid;

    public Likes toEntity() {
        return Likes.builder()
                .uid(this.uid)
                .boxid(this.boxid)
                .build();
    }
}
