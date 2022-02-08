package com.web.ls.controller;

import com.web.ls.model.dto.BasicResponse;
import com.web.ls.model.dto.linkbox.LinkboxCreateRequest;
import com.web.ls.model.dto.linkbox.LinkboxInterestRequest;
import com.web.ls.model.dto.linkbox.LinkboxUpdateRequest;
import com.web.ls.model.dto.linkbox.comment.BoxCommentRequest;
import com.web.ls.model.dto.linkbox.comment.BoxCommentUpdateRequest;
import com.web.ls.model.dto.linkbox.like.LikesCreateRequest;
import com.web.ls.model.entity.BoxComment;
import com.web.ls.model.service.BoxCommentService;
import com.web.ls.model.service.BoxLikeService;
import com.web.ls.model.service.LinkboxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/linkbox")
@Api("링크박스 컨트롤러")
public class LinkboxController {

    @Autowired
    LinkboxService linkboxService;

    @Autowired
    BoxCommentService boxCommentService;

    @Autowired
    BoxLikeService boxLikeService;

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
    public Object deleteBox(@PathVariable("boxid") @ApiParam(value = "삭제할 박스의 아이디",
            required = true) int boxid) {
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
    public Object updateLinkboxViewcount(@PathVariable("boxid") @ApiParam(value =
            "조회수 증가시킬 박스의 아이디") int boxid) {
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

    // 링크 박스 관심사
    
    @PostMapping("/interest")
    @ApiOperation(value = "링크박스 관심사 추가하기")
    public Object createLinkboxInterest(@RequestBody @Valid LinkboxInterestRequest request) {
        final BasicResponse result = new BasicResponse();
        linkboxService.createLinkboxInterest(request);

        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/interest")
    @ApiOperation(value = "링크박스 관심사 삭제하기")
    public Object deleteLinkboxInterest(@RequestBody @Valid LinkboxInterestRequest request) {
        final BasicResponse result = new BasicResponse();
        linkboxService.deleteLinkboxInterest(request);

        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 링크 박스 댓글
    
    @PostMapping("/comment")
    @ApiOperation(value = "링크박스 댓글 생성하기")
    public Object createLinkboxComment(@RequestBody @Valid BoxCommentRequest request) {
        final BasicResponse result = new BasicResponse();
        boxCommentService.createBoxComment(request);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/comment/{commentid}")
    @ApiOperation(value = "링크박스 댓글 삭제하기")
    public Object deleteLinkboxComment(@PathVariable("commentid") @ApiParam(value =
            "삭제할 댓글의 ID") Integer commentid) {
        final BasicResponse result = new BasicResponse();
        boxCommentService.deleteBoxComment(commentid);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/comment")
    @ApiOperation(value = "링크박스 댓글 수정하기")
    public Object updateLinkboxComment(@RequestBody @Valid BoxCommentUpdateRequest request) {
        final BasicResponse result = new BasicResponse();
        boxCommentService.updateBoxComment(request);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/comment/{boxid}")
    @ApiOperation(value = "링크박스 댓글 리스트 조회하기 by 박스 ID")
    public Object searchLinkBoxCommentByBoxId(@PathVariable("boxid") @ApiParam(value =
            "조회할 댓글 리스트의 박스 ID") Integer boxid) {
        final BasicResponse result = new BasicResponse();
        result.msg = "success";
        result.object = boxCommentService.searchBoxCommentByBoxid(boxid);;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 링크 박스 좋아요
    @PostMapping("/like")
    @ApiOperation(value = "링크 박스 좋아요 생성하기")
    public Object createLinkboxLike(@RequestBody @Valid LikesCreateRequest request) {
        final BasicResponse result = new BasicResponse();
        boxLikeService.createLinkboxLike(request);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/like/{likeid}")
    @ApiOperation(value = "링크 박스 좋아요 삭제하기")
    public Object deleteLikeboxLike(@PathVariable("linkid") @ApiParam(value =
            "삭제할 좋아요 ID") Integer linkid) {
        final BasicResponse result = new BasicResponse();
        boxLikeService.deleteLinkboxLike(linkid);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
