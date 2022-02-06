package com.web.ls.model.dto.linkdetail;

import com.web.ls.model.entity.LinkDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Builder
@Data
public class LinkdetailResponse implements Serializable {

    @ApiModelProperty(value = "link id", position = 1)
    private final Integer id;

    @ApiModelProperty(value = "link box id", position = 2)
    private final Integer boxid;

    @ApiModelProperty(value = "link title", position = 3)
    private final String title;

    @ApiModelProperty(value = "link description", position = 4)
    private final String desc;

    @ApiModelProperty(value = "link url", position = 5)
    private final String url;

    public static LinkdetailResponse fromEntity(LinkDetail link) {
        return LinkdetailResponse.builder()
                .id(link.getId())
                .boxid(link.getBoxid())
                .title(link.getTitle())
                .desc(link.getDesc())
                .url(link.getUrl())
                .build();
    }
}
