package com.web.ls.controller.linkbox;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.ls.model.dto.BasicResponse;
import com.web.ls.model.dto.User.UserResponse;
import com.web.ls.model.dto.linkbox.LinkboxCreateRequest;
import com.web.ls.model.dto.linkbox.LinkboxInterestRequest;
import com.web.ls.model.dto.linkbox.LinkboxUpdateRequest;
import com.web.ls.model.dto.linkbox.comment.BoxCommentRequest;
import com.web.ls.model.dto.linkbox.comment.BoxCommentUpdateRequest;
import com.web.ls.model.dto.linkbox.like.LikesCreateRequest;
import com.web.ls.model.service.BoxCommentService;
import com.web.ls.model.service.BoxLikeService;
import com.web.ls.model.service.LinkboxService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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

    @GetMapping("/list/{userid}")
    @ApiOperation(value = "유저 ID로 링크박스 리스트 조회하기")
    public Object searchLinkboxListByUid(@PathVariable("userid") @ApiParam(value =
            "조회할 박스 리스트의 유저 ID") Integer userid) {
        final BasicResponse result = new BasicResponse();

        result.msg = "success";
        result.object = linkboxService.searchLinkboxListByUserId(userid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/one/{boxid}")
    @ApiOperation(value = "박스 ID로 링크박스 정보 조회하기")
    public Object searchLinkboxByBoxid(@PathVariable("boxid") @ApiParam(value =
            "조회할 박스의 박스ID") Integer boxid) {
        final BasicResponse result = new BasicResponse();
        result.object = linkboxService.searchLinkboxByBoxid(boxid);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/popular")
    @ApiOperation(value = "좋아요 기준 상위 6개 링크박스의 리스트 조회")
    public Object searchPopularLinkbox() {
        final BasicResponse result = new BasicResponse();
        result.object = linkboxService.searchLinkboxListByLikes();
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/interests/{uid}")
    @ApiOperation(value = "유저의 관심사와 관련된 링크박스의 리스트 조회")
    public Object searchLinkboxListByInterests(@PathVariable("uid") @ApiParam(value =
            "유저 ID") Integer uid) {
        final BasicResponse result = new BasicResponse();
        result.object = linkboxService.searchLinkboxListByUserInterests(uid);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/follow/{uid}")
    @ApiOperation(value = "팔로우 한 사람의 링크박스 리스트 조회")
    public Object searchLinkboxListByFollow(@PathVariable("uid") @ApiParam(value =
            "유저 ID") Integer uid) {
        final BasicResponse result = new BasicResponse();
        result.object = linkboxService.searchLinkboxListByFollow(uid);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    @ApiOperation(value = "팔로우 한 사람의 링크박스 리스트 조회")
    public Object searchLinkboxListByKeyword(@PathVariable("keyword") @ApiParam(value =
            "링크박스를 검색할 키워드") String keyword) {
        final BasicResponse result = new BasicResponse();
        result.object = linkboxService.searchLinkboxListByKeyword(keyword);
        result.msg = "success";
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

    @GetMapping("/interest/{boxid}")
    @ApiOperation(value = "링크박스 관심사 조회하기")
    public Object searchLinkboxInterest(@PathVariable("boxid") @ApiParam(value =
            "조회할 관심사의 링크박스 ID") Integer boxid) {
        final BasicResponse result = new BasicResponse();
        result.object = linkboxService.searchInterestsByBoxid(boxid);
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

    @DeleteMapping("/like")
    @ApiOperation(value = "링크 박스 좋아요 삭제하기")
    public Object deleteLikeboxLike(@RequestBody @Valid LikesCreateRequest request) {
        final BasicResponse result = new BasicResponse();
        boxLikeService.deleteLinkboxLike(request);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/like/{boxid}")
    @ApiOperation(value = "박스 ID로 좋아요 정보 조회하기")
    public Object searchLikeByBoxid(@PathVariable("boxid") @ApiParam(value =
            "조회할 박스의 박스ID") Integer boxid) {
        final BasicResponse result = new BasicResponse();
        List<UserResponse> list = new LinkedList<>();
        boxLikeService.searchLikeByBoxid(boxid).forEach(i -> {
        	list.add(i.toResponse());
        });
        result.object = list;
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/like")
    @ApiOperation(value = "유저가 박스에 좋아요 했는지 여부")
    public Object checkUserLike(@RequestParam("boxid") @ApiParam(value =
            "조회할 박스의 박스ID") Integer boxid, @RequestParam("uid") @ApiParam(value =
                    "검사할 유저의 ID") Integer uid) {
        final BasicResponse result = new BasicResponse();
        
        result.object = boxLikeService.checkLikeUser(boxid, uid);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
