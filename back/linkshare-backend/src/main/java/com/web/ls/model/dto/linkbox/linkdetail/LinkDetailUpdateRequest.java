package com.web.ls.model.dto.linkbox.linkdetail;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
public class LinkDetailUpdateRequest implements Serializable {

    @ApiModelProperty(value = "link id", position = 1, required = true)
    @NotNull
    private final Integer id;

    @ApiModelProperty(value = "link title", position = 2)
    private final String title;

    @ApiModelProperty(value = "link description", position = 3)
    private final String desc;

    @ApiModelProperty(value = "link url", position = 4)
    private final String url;
}
