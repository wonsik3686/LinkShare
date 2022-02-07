package com.web.ls.model.dto.Follow;

import com.web.ls.model.entity.Follow;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FollowRequest {

	@ApiModelProperty(required = true)
    private Integer uid;
    
    @ApiModelProperty(required = true)
    private Integer followeeId;
    
    public Follow toEntity() {
    	return Follow.builder()
    			.uid(this.uid)
    			.followeeId(this.followeeId)
    			.build();
    }
}
