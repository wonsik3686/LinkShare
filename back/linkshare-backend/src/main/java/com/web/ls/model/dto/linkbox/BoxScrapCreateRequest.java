package com.web.ls.model.dto.linkbox;

import com.web.ls.model.entity.BoxScrap;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
public class BoxScrapCreateRequest implements Serializable {

    @ApiModelProperty(value = "user id", position = 1, required = true)
    @NotNull
    private Integer uid;
    @ApiModelProperty(value = "link box id", position = 2, required = true)
    @NotNull
    private Integer boxid;

    public BoxScrap toEntity() {
        return BoxScrap.builder()
                .uid(this.uid)
                .boxid(this.boxid)
                .build();
    }
}
