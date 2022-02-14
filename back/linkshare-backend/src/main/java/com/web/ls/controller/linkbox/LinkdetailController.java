package com.web.ls.controller.linkbox;

import com.web.ls.model.dto.BasicResponse;
import com.web.ls.model.dto.linkbox.linkdetail.LinkDetailUpdateRequest;
import com.web.ls.model.dto.linkbox.linkdetail.LinkdetailRequest;
import com.web.ls.model.service.LinkdetailService;
import com.web.ls.model.service.S3Service;
import com.web.ls.model.service.S3Uploader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/linkdetail")
@Api("링크 정보 컨트롤러")
public class LinkdetailController {

    @Autowired
    LinkdetailService linkdetailService;

    @Autowired
    S3Uploader s3Uploader;

    @Autowired
    S3Service s3Service;

    @PostMapping
    @ApiOperation(value = "링크 생성하기")
    public Object createLink(@RequestBody @Valid LinkdetailRequest request) {
        final BasicResponse result = new BasicResponse();
        linkdetailService.createLink(request);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{linkid}")
    @ApiOperation(value = "링크 삭제하기")
    public Object deleteLink(@PathVariable("linkid") @ApiParam(value = "삭제할 링크의 아이디",
            required = true) Integer linkid) {
        final BasicResponse result = new BasicResponse();
        linkdetailService.deleteLink(linkid);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{boxid}")
    @ApiOperation(value = "링크 정보 전체 조회하기")
    public Object searchAllLinks(@PathVariable("boxid") @ApiParam(value = "링크 정보 조회할 박스 아이디",
        required = true) Integer boxid) {
        final BasicResponse result = new BasicResponse();
        result.object = linkdetailService.searchLinkList(boxid);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "링크 정보 수정하기")
    public Object updateLink(@RequestBody @Valid LinkDetailUpdateRequest request) {
        final BasicResponse result = new BasicResponse();
        linkdetailService.updateLink(request);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @PostMapping("/images")
//    @ApiOperation(value = "s3 사진 업로드 테스트")
//    public Object testPic(@RequestParam("images") @NotNull MultipartFile multipartFile) throws IOException {
//        final BasicResponse result = new BasicResponse();
//        result.object = s3Uploader.upload(multipartFile, "linkdetail");
//        result.msg = "success";
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    @PostMapping("/upload")
//    public Object fileUpload(@RequestParam("image") @NotNull MultipartFile multipartFile) {
//        final BasicResponse result = new BasicResponse();
//        result.object = s3Service.uploadToLinkdetailDir(multipartFile);
//        result.msg = "success";
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    @GetMapping("/download")
//    public Object fileDownload() throws IOException {
//        return s3Service.downloadFromLinkdetailDir("linkdetail/70dd8786-4696-4c06-97d4-2179f9fc73db-필수6.jpeg");
//    }
}
