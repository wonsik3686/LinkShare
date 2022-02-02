package com.web.ls.controller;

import com.web.ls.model.dto.BasicResponse;
import com.web.ls.model.dto.linkbox.LinkboxCreateRequest;
import com.web.ls.model.service.LinkboxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/linkbox")
@Api("링크박스 컨트롤러")
public class LinkboxController {

    @Autowired
    LinkboxService linkboxService;

    @PostMapping("/create")
    @ApiOperation(value = "링크박스 생성하기")
    public Object createLinkBox(@RequestBody LinkboxCreateRequest request) {
        final BasicResponse result = new BasicResponse();

        if(request.getTitle() == null) {
            result.msg = "제목을 입력하지 않았습니다.";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else if (request.getUid() == 0) {
            result.msg = "회원이 입력되지 않았습니다.";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            linkboxService.createLinkbox(request);
            result.msg = "success";
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
