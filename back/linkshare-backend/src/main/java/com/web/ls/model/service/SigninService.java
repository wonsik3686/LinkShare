package com.web.ls.model.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.ls.config.security.JwtTokenProvider;
import com.web.ls.model.entity.User;
import com.web.ls.model.repository.UserRepository;

@Service
public class SigninService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	public String signin(String email, String password){
		User member = userRepository.findByEmail(email);
		if(member == null) throw new IllegalArgumentException("가입되지 않은 E-MAIL 입니다.");
        if (!passwordEncoder.matches(password, member.getPassword())) throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        
        //db에가서 auth가 true인지 체크
        if(!member.getConfirm()) throw new AuthenticationServiceException("이메일 인증을 하지 않은 사용자 입니다.");
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }
	}

