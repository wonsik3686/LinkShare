package com.web.ls.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.ls.model.dto.BasicResponse;
import com.web.ls.model.dto.User.ProfileEditRequest;
import com.web.ls.model.dto.User.SigninRequest;
import com.web.ls.model.dto.User.SignupRequest;
import com.web.ls.model.dto.User.UserInterestRequest;
import com.web.ls.model.entity.User;
import com.web.ls.model.service.FollowService;
import com.web.ls.model.service.ProfileService;
import com.web.ls.model.service.S3Service;
import com.web.ls.model.service.SigninService;
import com.web.ls.model.service.SignupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/user")
@Api("회원관리 컨트롤러")
public class UserController {

	@Autowired
	private SignupService signupService;

	@Autowired
	private SigninService signinService;
	
	@Autowired
	private ProfileService profileService;

	@Autowired
	private FollowService followService;

	@Autowired
	private S3Service s3Service;

	@PostMapping("/signup")
	@ApiOperation(value = "가입하기")
	public Object signup(@RequestBody @Valid SignupRequest request) {

		final BasicResponse result = new BasicResponse();
		signupService.signup(request);
		result.msg = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/signupConfirm")
	@ApiOperation(value = "이메일 인증 확인")
	public Object signupConfirm(@RequestParam String email, String authKey) {
		final BasicResponse result = new BasicResponse();
		signupService.signupConfirm(email, authKey);
		result.msg = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// 로그인
    @PostMapping("/signin")
    @ApiOperation(value = "로그인요청")
    public Object signin(@RequestBody SigninRequest request) {
    	Map<String, Object> resultMap = new HashMap<>();
    	resultMap.put("token", signinService.signin(request.getEmail(), request.getPassword()));
    	final BasicResponse result = new BasicResponse();
    	result.msg = "success";
    	result.object = resultMap;
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @ApiOperation(value = "회원 정보보기")
	@GetMapping("/{email}")
	public Object userInfo(@PathVariable("email") @ApiParam(value = "유저의 email.", required = true) String email) {
    	Map<String, Object> resultMap = new HashMap<>();
    	Map<String, Object> returnMap = new HashMap<>();
    	User user = profileService.userInfo(email);
    	
    	resultMap.put("id", user.getId());
    	resultMap.put("email", user.getEmail());
    	resultMap.put("nickname", user.getNickname());
    	resultMap.put("introduce", user.getIntroduce());
    	resultMap.put("imagePath", user.getImagePath());
    	resultMap.put("follower", followService.follower(user.getId()));
    	resultMap.put("following", followService.following(user.getId()));
    	returnMap.put("userInfo", resultMap);
    	final BasicResponse result = new BasicResponse();
    	result.msg = "success";
    	result.object = returnMap;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

    @ApiOperation(value = "회원 정보보기")
	@GetMapping
	public Object userInfoById(@RequestParam("uid") @ApiParam(value = "유저의 id.", required = true) int uid) {
    	Map<String, Object> resultMap = new HashMap<>();
    	Map<String, Object> returnMap = new HashMap<>();
    	User user = profileService.userInfo(uid);
    	final BasicResponse result = new BasicResponse();
    	
    	resultMap.put("id", user.getId());
    	resultMap.put("email", user.getEmail());
    	resultMap.put("nickname", user.getNickname());
    	resultMap.put("introduce", user.getIntroduce());
    	resultMap.put("imagePath", user.getImagePath());
    	resultMap.put("follower", followService.follower(user.getId()));
    	resultMap.put("following", followService.following(user.getId()));
    	returnMap.put("userInfo", resultMap);
    	result.msg = "success";
    	result.object = returnMap;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
    @DeleteMapping("/{email}")
    @ApiOperation(value = "회원탈퇴 요청")
    public Object userDelete(@PathVariable("email") @ApiParam(value = "유저의 email.", required = true) String email) {
    	final BasicResponse result = new BasicResponse();
    	result.msg = "success";
    	profileService.deleteUser(email);
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
	@PutMapping
	@ApiOperation(value = "회원정보 수정")
	public Object profileEdit(@RequestBody @ApiParam(value = "수정할 회원정보.", required = true) ProfileEditRequest request) {
		final BasicResponse result = new BasicResponse();
    	result.msg = "success";
    	profileService.profileEdit(request);
    	return new ResponseEntity<>(result, HttpStatus.OK);
	}

	//사진
	@PutMapping("/img")
	@ApiOperation(value = "회원 프로필 사진 수정")
	public Object updateUserImage(@RequestBody @ApiParam(value = "수정할 회원 사진 파일.", required = true) MultipartFile image,
								  @ApiParam(value = "수정할 회원 아이디.", required = true) Integer uid) {
		final BasicResponse result = new BasicResponse();
		Map<String, Object> resultMap = new HashMap<>();

		String path = s3Service.uploadToUserDir(image);
		profileService.updateUserImgPath(uid, path);

		resultMap.put("path", path);

		result.object = resultMap;
		result.msg = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/img/uid/{uid}")
	@ApiOperation(value = "회원 프로필 사진 조회 by uid")
	public Object searchUserImgPathByUid(@PathVariable("uid") @ApiParam(value = "유저의 uid.", required = true) Integer uid) {

		final BasicResponse result = new BasicResponse();
		Map<String, Object> resultMap = new HashMap<>();

		String path = profileService.searchUserImgPathById(uid);
		resultMap.put("path", path);
		result.object = resultMap;
		result.msg = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/img/email/{email}")
	@ApiOperation(value = "회원 프로필 사진 조회 by email")
	public Object searchUserImgPathByEmail(@PathVariable("email") @ApiParam(value = "유저의 email.", required = true) String email) {

		final BasicResponse result = new BasicResponse();
		Map<String, Object> resultMap = new HashMap<>();

		String path = profileService.searchUserImgPathByEmail(email);
		resultMap.put("path", path);
		result.object = resultMap;
		result.msg = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/img/nickname/{nickname}")
	@ApiOperation(value = "회원 프로필 사진 조회 by nickname")
	public Object searchUserImgPathByNickname(@PathVariable("nickname") @ApiParam(value = "유저의 nickname.", required = true) String nickname) {

		final BasicResponse result = new BasicResponse();
		Map<String, Object> resultMap = new HashMap<>();

		String path = profileService.searchUserImgPathByNickname(nickname);
		resultMap.put("path", path);
		result.object = resultMap;
		result.msg = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
    @PostMapping("/interest")
    @ApiOperation(value = "유저 관심사 추가하기")
    public Object createUserInterest(@RequestBody @Valid UserInterestRequest request) {
        final BasicResponse result = new BasicResponse();
        profileService.createUserInterest(request);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/interest")
    @ApiOperation(value = "유저 관심사 삭제하기")
    public Object deleteLinkboxInterest(@RequestBody @Valid UserInterestRequest request) {
        final BasicResponse result = new BasicResponse();
        profileService.deleteUserInterest(request);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    
    @GetMapping("/interest/{uid}")
    @ApiOperation(value = "유저 관심사 조회하기")
    public Object searchUserInterest(@PathVariable("uid") @ApiParam(value =
            "조회할 관심사의 유저 ID") Integer uid) {
        final BasicResponse result = new BasicResponse();
        result.object = profileService.searchInterestsByUid(uid);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
