package com.web.ls.model.dto.linkbox;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
public class LinkboxInfoResponse implements Serializable {

    @ApiModelProperty(value = "linkbox id", position = 1)
    public Integer id;
    @ApiModelProperty(value = "linkbox title", position = 2)
    public String title;
    @ApiModelProperty(value = "linkbox view count", position = 3)
    public Integer viewCount;
    @ApiModelProperty(value = "linkbox description", position = 4)
    public String desc;
    @ApiModelProperty(value = "linkbox registered time", position = 5)
    public Instant regtime;
    @ApiModelProperty(value = "list of interests related to linkbox", position = 6)
    public List<String> interests;
    @ApiModelProperty(value = "linkbox likes count", position = 7)
    public int likeCount;
    @ApiModelProperty(value = "linkbox comment count", position = 8)
    public int commentCount;
    @ApiModelProperty(value = "linkbox scrap count", position = 9)
    public int scrapCount;
    @ApiModelProperty(value = "linkbox user id", position = 9)
    public int uid;

}
