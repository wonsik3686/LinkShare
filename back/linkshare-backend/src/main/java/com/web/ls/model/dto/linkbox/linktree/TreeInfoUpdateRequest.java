package com.web.ls.model.dto.linkbox.linktree;

import com.web.ls.model.entity.TreeInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import springfox.documentation.spring.web.json.Json;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
public class TreeInfoUpdateRequest implements Serializable {

    @ApiModelProperty(value = "링크트리 ID", required = true)
    @NotNull
    private Integer id;

    @ApiModelProperty(value = "링크트리 json", required = true)
    private Object json;

    public TreeInfo toEntity() {
        return TreeInfo.builder()
                .json(this.json.toString())
                .build();
    }
}
