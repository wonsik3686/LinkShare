package com.web.ls.controller.linkbox;

import com.web.ls.model.dto.BasicResponse;
import com.web.ls.model.dto.linkbox.linktree.TreeInfoCreateRequest;
import com.web.ls.model.dto.linkbox.linktree.TreeInfoUpdateRequest;
import com.web.ls.model.service.LinktreeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/linktree")
public class LinktreeController {

    @Autowired
    LinktreeService linktreeService;

    @PostMapping
    @ApiOperation(value = "링크트리 생성하기")
    public Object createLinktree(@RequestBody @Valid TreeInfoCreateRequest request) {
        final BasicResponse result = new BasicResponse();
        linktreeService.createLinktree(request);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{treeid}")
    @ApiOperation(value = "링크트리 삭제하기")
    public Object deleteLinktree(@PathVariable("treeid") @ApiParam(
            "삭제할 링크트리의 ID") Integer treeid) {
        final BasicResponse result = new BasicResponse();
        linktreeService.deleteLinktree(treeid);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "링크트리 수정하기")
    public Object createLinktree(@RequestBody @Valid TreeInfoUpdateRequest request) {
        final BasicResponse result = new BasicResponse();
        linktreeService.updateLinktree(request);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{boxid}")
    @ApiOperation(value = "링크트리 조회하기")
    public Object searchLinktreeList(@PathVariable("boxid") @ApiParam(
            "조회할 링크트리의 ID") Integer boxid) {
        final BasicResponse result = new BasicResponse();
        result.object = linktreeService.searchTreeListByBoxid(boxid);
        result.msg = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
