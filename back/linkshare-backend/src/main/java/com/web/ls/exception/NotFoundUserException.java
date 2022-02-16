package com.web.ls.exception;

public class NotFoundUserException extends RuntimeException{
    private static final String MESSAGE = "유저를 찾지 못했습니다.";
    public NotFoundUserException() {super(MESSAGE);}
}
