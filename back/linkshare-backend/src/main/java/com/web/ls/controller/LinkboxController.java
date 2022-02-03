package com.web.ls.controller;

import com.web.ls.model.dto.BasicResponse;
import com.web.ls.model.dto.linkbox.LinkboxCreateRequest;
import com.web.ls.model.dto.linkbox.LinkboxUpdateRequest;
import com.web.ls.model.service.LinkboxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        if(request.getTitle().equals("")) {
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

    @DeleteMapping("/{boxid}")
    @ApiOperation(value = "링크박스 삭제하기")
    public Object deleteBox(@PathVariable("boxid") @ApiParam(value = "삭제할 박스의 아이디", required = true) int boxid) {
        final BasicResponse result = new BasicResponse();

        if(linkboxService.existsLinkboxById(boxid)) {
            linkboxService.deleteLinkbox(boxid);
            result.msg = "success";
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        result.msg = "해당 링크박스가 존재하지 않습니다.";
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/viewcount/{boxid}")
    @ApiOperation(value = "링크박스 조회수 증가")
    public Object updateLinkboxViewcount(@PathVariable("boxid") @ApiParam(value = "조회수 증가시킬 박스의 아이디") int boxid) {
        final BasicResponse result = new BasicResponse();

        if(linkboxService.existsLinkboxById(boxid)) {
            linkboxService.updateLinkboxViewCountById(boxid);
            result.msg = "success";
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        result.msg = "해당 링크박스가 존재하지 않습니다.";
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @PutMapping()
    @ApiOperation(value = "링크박스 제목 및 설명 수정하기")
    public Object updateLinkboxTitleAndDesc(@RequestBody LinkboxUpdateRequest request) {
        final BasicResponse result = new BasicResponse();

        if(linkboxService.existsLinkboxById(request.getBoxid())) {
            linkboxService.updateLinkboxTitle(request.getTitle(), request.getBoxid());
            linkboxService.updateLinkboxDesc(request.getDesc(), request.getBoxid());
            result.msg = "success";
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        result.msg = "해당 링크박스가 존재하지 않습니다.";
        return  new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/list")
    @ApiOperation(value = "링크박스 전체 리스트 조회하기")
    public Object searchAllLinkboxList() {
        final BasicResponse result = new BasicResponse();

        result.msg = "success";
        result.object = linkboxService.searchAllLinkboxList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
