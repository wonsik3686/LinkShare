package com.web.ls.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.ls.model.dto.BasicResponse;
import com.web.ls.model.dto.SignupRequest;
import com.web.ls.model.service.SignupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/member")
@Api("회원관리 컨트롤러")
public class UserController {

	@Autowired
	SignupService signupService;

	@PostMapping("/signup")
	@ApiOperation(value = "가입하기")
	public Object signup(@RequestBody @Valid SignupRequest request) {

		final BasicResponse result = new BasicResponse();
		signupService.signup(request);
		result.msg = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
