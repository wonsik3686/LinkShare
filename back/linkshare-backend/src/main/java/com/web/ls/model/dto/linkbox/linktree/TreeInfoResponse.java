package com.web.ls.model.dto.linkbox.linktree;

import com.web.ls.model.entity.TreeInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import springfox.documentation.spring.web.json.Json;

import java.io.Serializable;

@Builder
@Data
public class TreeInfoResponse implements Serializable {

    @ApiModelProperty(value = "링크트리 ID", required = true)
    private final Integer id;

    @ApiModelProperty(value = "트리가 담긴 링크박스 ID", required = true)
    private final Integer boxid;

    @ApiModelProperty(value = "링크트리 Json", required = true)
    private final Object treeContents;

    public static TreeInfoResponse fromEntity(TreeInfo treeInfo) {
        return TreeInfoResponse.builder()
                .id(treeInfo.getId())
                .boxid(treeInfo.getBoxid())
                .treeContents(treeInfo.getJson())
                .build();
    }
}
