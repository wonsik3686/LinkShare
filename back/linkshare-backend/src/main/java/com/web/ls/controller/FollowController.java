package com.web.ls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.ls.model.dto.BasicResponse;
import com.web.ls.model.dto.Follow.FollowRequest;
import com.web.ls.model.service.FollowService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
//@RequestMapping("/follow")
@Api("팔로우 컨트롤러")
public class FollowController {

	@Autowired
	private FollowService followService;

	@PostMapping("/follow")
	@ApiOperation(value = "팔로우하기")
	public Object follow(@RequestBody FollowRequest request) {

		final BasicResponse result = new BasicResponse();
		followService.follow(request);
		result.msg = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/follow")
	@ApiOperation(value = "언팔로우하기")
	public Object unfollow(@RequestBody FollowRequest request) {

		final BasicResponse result = new BasicResponse();
		followService.unfollow(request);
		result.msg = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
