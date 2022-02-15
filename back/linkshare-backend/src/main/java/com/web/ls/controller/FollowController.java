package com.web.ls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.ls.model.dto.BasicResponse;
import com.web.ls.model.dto.Follow.FollowRequest;
import com.web.ls.model.service.FollowService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
	
	@GetMapping("/following/{userid}")
    @ApiOperation(value = "유저 ID로 팔로잉 리스트 조회하기")
    public Object searchFollowingListByUid(@PathVariable("userid") @ApiParam(value =
            "팔로잉한 목록을 조회할 유저의 ID") Integer userid) {
        final BasicResponse result = new BasicResponse();

        result.msg = "success";
        result.object = followService.searchFollowingListByUserId(userid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@GetMapping("/follower/{userid}")
	@ApiOperation(value = "유저 ID로 팔로워 리스트 조회하기")
	public Object searchFollowerListByUid(@PathVariable("userid") @ApiParam(value =
			"팔로워 목록을 조회할 유저의 ID") Integer userid) {
		final BasicResponse result = new BasicResponse();
		
		result.msg = "success";
		result.object = followService.searchFollowerListByUserId(userid);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
