package com.web.ls.model.dto.linkbox.linkdetail;

import com.web.ls.model.entity.LinkDetail;
import com.web.ls.model.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LinkdetailRequest implements Serializable {

    @ApiModelProperty(value = "link box id", position = 1, required = true)
    @NotNull
    private Integer boxid;

    @ApiModelProperty(value = "link title", position = 2, required = true)
    @NotBlank
    private String title;

    @ApiModelProperty(value = "link description", position = 3, required = false)
    private String desc;

    @ApiModelProperty(value = "link url", position = 4, required = true)
    @NotBlank
    private String url;

    public LinkDetail toEntity() {
        return LinkDetail.builder()
                .boxid(this.boxid)
                .title(this.title)
                .desc(this.desc)
                .url(this.url)
                .build();
    }
}
