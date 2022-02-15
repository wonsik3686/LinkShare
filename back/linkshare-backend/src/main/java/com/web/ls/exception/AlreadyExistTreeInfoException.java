package com.web.ls.exception;

public class AlreadyExistTreeInfoException extends RuntimeException{
    private static final String MESSAGE = "링크박스에 이미 등록된 링크트리가 있습니다.";
    public AlreadyExistTreeInfoException () {
        super(MESSAGE);
    }
}
