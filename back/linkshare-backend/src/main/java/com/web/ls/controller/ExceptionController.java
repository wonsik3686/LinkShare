package com.web.ls.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.web.ls.exception.AlreadyExistEmailException;
import com.web.ls.exception.AlreadyExistNicknameException;
import com.web.ls.model.dto.BasicResponse;

//@Slf4j
@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public Object methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        BasicResponse result = new BasicResponse();
        result.msg = "Input Validation Error";
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler({AlreadyExistEmailException.class, AlreadyExistNicknameException.class, IllegalArgumentException.class, AuthenticationServiceException.class})
    public Object existEmailException(Exception e, HttpServletRequest request){
        BasicResponse result = new BasicResponse();
        result.msg = e.getMessage();
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
	

}
