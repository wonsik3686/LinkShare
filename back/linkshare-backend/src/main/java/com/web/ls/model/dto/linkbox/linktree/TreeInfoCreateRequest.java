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
public class TreeInfoCreateRequest implements Serializable {

    @ApiModelProperty(value = "링크박스 ID", required = true)
    @NotNull
    private Integer boxid;

    @ApiModelProperty(value = "링크트리 json", required = true)
    private Object treeContents;

    public TreeInfo toEntity() {
        return TreeInfo.builder()
                .boxid(this.boxid)
                .json(this.treeContents.toString())
                .build();
    }
}
