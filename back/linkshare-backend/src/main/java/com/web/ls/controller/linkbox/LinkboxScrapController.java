package com.web.ls.controller.linkbox;

import com.web.ls.model.dto.BasicResponse;
import com.web.ls.model.dto.linkbox.BoxScrapCreateRequest;
import com.web.ls.model.dto.linkbox.LinkboxCreateRequest;
import com.web.ls.model.service.LinkboxScrapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/linkbox/scrap")
@Api("링크박스 스크랩 컨트롤러")
public class LinkboxScrapController {

    @Autowired
    LinkboxScrapService linkboxScrapService;

    @PostMapping
    @ApiOperation(value = "링크박스 스크랩 생성하기")
    public Object createLinkBox(@RequestBody BoxScrapCreateRequest request) {
        final BasicResponse result = new BasicResponse();
        linkboxScrapService.createBoxScrap(request);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation(value = "링크박스 스크랩 삭제")
    public Object deleteLinkboxScrap(@RequestBody BoxScrapCreateRequest request) {
        final BasicResponse result = new BasicResponse();
        linkboxScrapService.deleteBoxScrap(request);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/box/{boxid}")
    @ApiOperation(value = "링크박스 스크랩 리스트 조회 BY boxId")
    public Object searchLinkboxScrapListByBoxid(@PathVariable("boxid") @ApiParam(value =
            "스크랩 리스트를 조회할 링크박스의 ID") Integer boxid) {
        final BasicResponse result = new BasicResponse();
        result.object = linkboxScrapService.searchBoxScrapListByBoxid(boxid);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/user/{uid}")
    @ApiOperation(value = "링크박스 스크랩 리스트 조회 BY uId")
    public Object searchLinkboxScrapListByUid(@PathVariable("uid") @ApiParam(value =
            "스크랩 리스트를 조회할 유저의 ID") Integer uid) {
        final BasicResponse result = new BasicResponse();
        result.object = linkboxScrapService.searchBoxScrapListByUid(uid);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
