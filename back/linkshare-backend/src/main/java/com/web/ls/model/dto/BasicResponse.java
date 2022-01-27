package com.web.ls.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class BasicResponse {
	    @ApiModelProperty(value = "msg", position = 1)
	    public String msg;
	    @ApiModelProperty(value = "object", position = 2)
	    public Object object;
}
