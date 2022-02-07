package com.web.ls.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.ls.model.dto.BasicResponse;
import com.web.ls.model.dto.SigninRequest;
import com.web.ls.model.dto.SignupRequest;
import com.web.ls.model.service.SigninService;
import com.web.ls.model.service.SignupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/user")
@Api("회원관리 컨트롤러")
public class UserController {

	@Autowired
	private SignupService signupService;

	@Autowired
	private SigninService signinService;

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
    	final BasicResponse result = new BasicResponse();
    	result.msg = "success";
    	result.object = signinService.signin(request.getEmail(), request.getPassword());
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
