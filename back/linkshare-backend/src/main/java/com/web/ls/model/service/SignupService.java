package com.web.ls.model.service;


import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.ls.exception.AlreadyExistEmailException;
import com.web.ls.exception.AlreadyExistNicknameException;
import com.web.ls.model.dto.User.SignupRequest;
import com.web.ls.model.entity.User;
import com.web.ls.model.repository.UserRepository;
import com.web.ls.util.MailUtils;

@Service
public class SignupService {
	
	private int size;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JavaMailSenderImpl mailSender;

	public void signup(SignupRequest request){
		if (userRepository.existsByEmail(request.getEmail())) {
			throw new AlreadyExistEmailException();
		} else if(userRepository.existsByNickname(request.getNickname())) {
			throw new AlreadyExistNicknameException();
		} else {
			request.setPassword(passwordEncoder.encode(request.getPassword()));
			User user = request.toEntity();
			user.setAuthKey(sendAuthMail(request.getEmail()));
			userRepository.save(user);
		}
	}
	

    //인증키 생성
    private String getKey(int size) {
        this.size = size;
        return getAuthCode();
    }

    //인증코드 난수 발생
    private String getAuthCode() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while(buffer.length() < size) {
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }

    //인증메일 보내기
    public String sendAuthMail(String email) {
        //6자리 난수 인증번호 생성
        String authKey = getKey(6);
        
        //인증메일 보내기
        try {
            MailUtils sendMail = new MailUtils(mailSender);
            sendMail.setSubject("LinkShare 회원가입 이메일 인증");
            sendMail.setText(new StringBuffer().append("<h1>LinkShare</h1><br>")
            .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
            .append("<a href='http://3.38.246.117/user/signupConfirm?email=")
            .append(email)
            .append("&authKey=")
            .append(authKey)
            .append("' target='_blenk'>이메일 인증 확인</a>")
            .toString());
            sendMail.setFrom("ssafyLS22@gmail.com", "LinkShare");
            sendMail.setTo(email);
            //sendMail.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
          return authKey;
    }
    
    public void signupConfirm(String email, String authKey) {
    	//해당 email의 authKey가 동일하다면
    	User user = userRepository.findByEmail(email);
    	if(user.getAuthKey().equals(authKey)) {
    		user.setConfirm(true);
    		userRepository.save(user);
    		return ;
    	}else {
			throw new RuntimeException();
    	}
    }
}
